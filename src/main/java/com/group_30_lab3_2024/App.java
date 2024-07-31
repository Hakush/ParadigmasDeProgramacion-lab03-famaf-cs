package com.group_30_lab3_2024;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.SparkSession;

import com.group_30_lab3_2024.feed.Article;
import com.group_30_lab3_2024.feed.FeedParser;
import com.group_30_lab3_2024.namedEntities.NamedEntity;
import com.group_30_lab3_2024.namedEntities.heuristics.Heuristic;
import com.group_30_lab3_2024.namedEntities.heuristics.HeuristicFactory;
import com.group_30_lab3_2024.utils.Config;
import com.group_30_lab3_2024.utils.DatabaseData;
import com.group_30_lab3_2024.utils.EntityParser;
import com.group_30_lab3_2024.utils.FeedsData;
import com.group_30_lab3_2024.utils.JSONParser;
import com.group_30_lab3_2024.utils.StadisticsPrinter;
import com.group_30_lab3_2024.utils.UserInterface;

import scala.Tuple2;

public class App {

    private static final Pattern SPACE = Pattern.compile(" ");

    public static String PathFile = "";

    public static void main(String[] args) {

        List<FeedsData> feedsDataArray = new ArrayList<>();
        try {
            String classPath = App.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            Path path = Paths.get(classPath).toAbsolutePath();

            // Si estás ejecutando desde un JAR, el path incluirá el nombre del JAR. Si estás en un entorno de desarrollo, será el directorio /target/classes o similar.
            // Ajusta el path según sea necesario para subir al directorio del proyecto.
            // Este ejemplo asume que la estructura del proyecto incluye un directorio /target o /build en la raíz del proyecto.
            Path projectDirectory = path.getParent().getParent(); // Subir dos niveles en la jerarquía de directorios

            // Convertir el path del proyecto a String si es necesario
            String projectDirectoryPath = projectDirectory.toString() ;
            App.PathFile = projectDirectoryPath;
            System.out.println("Project Directory: " + projectDirectoryPath);

            feedsDataArray = JSONParser.parseJsonFeedsData(App.PathFile + "/src/main/java/com/group_30_lab3_2024/data/feeds.json");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        

        UserInterface ui = new UserInterface();
        String[] options = args[0].split(" ");
        Config config = ui.handleInput(options); // "-ne Capitalized -sf"
        run(config, feedsDataArray);

    }

    private static void run(Config config, List<FeedsData> feedsDataArray) {

        if (feedsDataArray == null || feedsDataArray.size() == 0) {
            System.out.println("No feeds data found");
            return;
        }

        // Filter feedsDataArray with the selected feed key
        if (config.getFeedKey() != null) {
            List<FeedsData> feedsDataArrayAux = new ArrayList<>();
            for (FeedsData feedData : feedsDataArray) {
                if (feedData.getLabel().equals(config.getFeedKey())) {
                    feedsDataArrayAux.add(feedData);
                }
            }
            feedsDataArray = feedsDataArrayAux;
        }

        // Populate feedsDataArray with articles from corresponding feeds
        for (FeedsData feedData : feedsDataArray) {
            try {
                String feedXML = FeedParser.fetchFeed(feedData.getUrl());
                List<Article> articles = FeedParser.parseXML(feedXML);
                feedData.addAll(articles);
            } catch (Exception e) {
                System.out.println("Error fetching feed: " + feedData.getLabel());
                e.printStackTrace();
            }
        }

        if (config.getPrintFeed()) {
            System.out.println("Printing feed(s) ");
            // Prints the fetched feed
            for (FeedsData feedData : feedsDataArray) {
                System.out.println("\n\nFeed: " + feedData.getLabel());
                System.out.println("URL: " + feedData.getUrl());
                System.out.println("Articles: ");
                for (Article article : feedData.getArticles()) {
                    article.prettyPrint();
                }
            }
        }

        if (config.getComputeNamedEntities()) {
            try {

                String heuristicName = config.getHeuristic(); // Esto vendría del input del usuario
                // Mapear config.getHeuristic() con su nombre de clase
                String heuristicClassName = HeuristicFactory.getHeuristicClassName(heuristicName);

                if (heuristicClassName == null) {
                    System.out.println("Heuristic not found: " + heuristicName);
                    System.exit(1);
                    return;
                }

                System.out.println("Computing named entities using " + config.getHeuristic() + " heuristic");
                // Cargar la clase
                Class<?> heuristicClass = Class.forName(heuristicClassName);
                // Crear una instancia de la clase
                Heuristic heuristic = (Heuristic) heuristicClass.getDeclaredConstructor().newInstance();
                try {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.PathFile + "/BigData.txt"))) {
                        for (FeedsData feedData : feedsDataArray) {
                            for (Article article : feedData.getArticles()) {
                                try {
                                    String title = article.getTitle() != null ? article.getTitle() : "" ;
                                    String description = article.getDescription() != null ? article.getDescription() : "";
                                    String text = title + " \n" + description + " \n\n";
                                    // Print into file
                                    writer.write(text);

                                } catch (Exception e) {
                                    System.out.println("Error writing article: " + article.getTitle());
                                    e.printStackTrace();
                                }
                            }   
                        }
                    } catch (IOException e) {
                        System.out.println("Error writing to file");
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    System.out.println("Error loading heuristic");
                    e.printStackTrace();
                }
                
                final List<DatabaseData> databaseList;
                databaseList = JSONParser.parseJsonDatabaseData(App.PathFile + "/src/main/java/com/group_30_lab3_2024/data/dictionary.json");

                // Aca se aplica el calculo distribuido para obtener entidades nombradas candidatas, entidades nombradas y sus ocurrencias
                List<NamedEntity> namedEntities = getNamedEntities(heuristic, databaseList);
                // Compute counter for all named entities 

                // Ahora firstElements contiene solo el primer elemento de cada tupla
                StadisticsPrinter sp = new StadisticsPrinter(namedEntities);

                System.out.println("\nStats ");

                if (config.getStatsFormat().equals("cat")) {
                    System.out.println("By Category:\n");
                    // Estadisticas por categoria
                    sp.printStatsCat();
                } else if (config.getStatsFormat().equals("topic")) {
                    System.out.println("By Topic:\n");
                    // Estadisticas por topico
                    sp.printStatsTopic();
                }

            } catch (Exception e) {
                System.out.println("Error running application");
                e.printStackTrace();
            }
        }
        System.out.println("");
    }

    private static List<NamedEntity> getNamedEntities(Heuristic heuristic, List<DatabaseData> databaseList) {
        SparkSession spark = SparkSession
        .builder()
        .appName("FeedsReader")
        .master("spark://Ubuntu.myguest.virtualbox.org:7077") // Aca va la URL de tu Master
        .getOrCreate();
        
        JavaRDD<String> lines = spark.read().textFile(App.PathFile + "/BigData.txt").javaRDD();

        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());

        // ** 1er Uso de calculo distribuido: Extraer candidatos a entidades nombradas aplicando heuristicas
        JavaRDD<String> NECandidates = words.flatMap((FlatMapFunction<String, String>) word -> heuristic.extractCandidates(word).iterator());

        // ** 2do Uso de calculo distribuido: Parsear candidatos a entidades nombradas

        JavaRDD<NamedEntity> entitiesRDD = NECandidates.flatMap(candidate -> {
            EntityParser entityParser = new EntityParser(Arrays.asList(candidate)); 
            return entityParser.parseEntities(databaseList).iterator();
        });

        JavaPairRDD<NamedEntity, Integer> ones = entitiesRDD.mapToPair(s -> new Tuple2<>(s, 1)); 
        JavaPairRDD<NamedEntity, Integer> counts = ones.reduceByKey(Integer::sum); 
        List<Tuple2<NamedEntity, Integer>> output = counts.collect();
        
                
        for(Tuple2<NamedEntity, Integer> tuple : output){
            tuple._1().setCounter(tuple._2());
        }
        // Suponiendo que output es List<Tuple2<NamedEntity, Integer>>
        List<NamedEntity> firstElements = output.stream()
                                                .map(tuple -> tuple._1())
                                                .collect(Collectors.toList());

        return firstElements;
    }
}

