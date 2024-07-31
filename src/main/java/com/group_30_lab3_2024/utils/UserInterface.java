package com.group_30_lab3_2024.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.group_30_lab3_2024.namedEntities.heuristics.HeuristicFactory;

public class UserInterface {

    private HashMap<String, String> optionDict;

    private List<Option> options;

    public UserInterface() {
        options = new ArrayList<Option>();
        options.add(new Option("-h", "--help", 0));
        options.add(new Option("-f", "--feed", 1));
        options.add(new Option("-ne", "--named-entity", 1));
        options.add(new Option("-pf", "--print-feed", 0));
        options.add(new Option("-sf", "--stats-format", 1));

        optionDict = new HashMap<String, String>();
    }

    public Config handleInput(String[] args) {
        if (args.length == 0 || args == null) {
            System.out.println("Invalid inputs");
            UserInterface.printHelp();
            System.exit(1);
        } else {
            for (Integer i = 0; i < args.length; i++) {
                for (Option option : options) {
                    if (option.getName().equals(args[i]) || option.getLongName().equals(args[i])) {
                        if (option.getnumValues() == 0) {
                            optionDict.put(option.getName(), null);
                        } else {
                            if (option.getName().equals("-ne") && ((i + 1 < args.length && args[i + 1].startsWith("-"))
                                    || (i == args.length - 1))) {
                                // si es -ne y el siguiente comienza con "-"
                                // o si es el ultimo argumento
                                optionDict.put("-pf", null);
                            } else if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                                optionDict.put(option.getName(), args[i + 1]);
                                i++;
                            } else {
                                // Si -ne no tiene un valor asociado, se considera activado el flag -pf
                                if (option.getName().equals("-sf")) { // Si -sf no tiene un valor asociado, se considera
                                                                      // cat
                                    optionDict.put("-sf", "cat");
                                } else {
                                    System.out.println("Invalid inputs");
                                    UserInterface.printHelp();
                                    System.exit(1);
                                }
                            }
                        }
                    }
                }
            }
        }

        Boolean printFeed = optionDict.containsKey("-pf");
        Boolean computeNamedEntities = optionDict.containsKey("-ne");
        String heuristic = optionDict.get("-ne");
        String feedKey = optionDict.get("-f");
        String statsFormat = "missingCategory";

        if (computeNamedEntities) {
            if (optionDict.containsKey("-sf")) {
                if (!optionDict.get("-sf").equals("cat") && !optionDict.get("-sf").equals("topic")) {
                    System.out.println("Invalid inputs");
                    UserInterface.printHelp();
                    System.exit(1);
                } else {
                    statsFormat = optionDict.get("-sf");
                }
            } else {
                optionDict.put("-sf", "missingCategory");
                System.out.println("Invalid inputs");
                UserInterface.printHelp();
                System.exit(1);
            }
        } else { // si -ne no esta en el diccionario de opciones, entonces considerar activada
                 // -pf
            printFeed = true;
        }

        if (optionDict.containsKey("-h")) {
            UserInterface.printHelp();
            System.exit(0);
        } else if (optionDict.isEmpty()) {
            System.out.println("Invalid inputs");
            UserInterface.printHelp();
            System.exit(1);
        }

        return new Config(printFeed, computeNamedEntities, feedKey, heuristic, statsFormat);
    }

    public static void printHelp() {

        List<FeedsData> feedsDataArray = new ArrayList<>();
        try {
            feedsDataArray = JSONParser.parseJsonFeedsData("src/main/java/com/group_30_lab3_2024/data/feeds.json");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Usage: make run ARGS=\"[OPTION]\"");
        System.out.println("Options:");
        System.out.println("  -h, --help: Show this help message and exit");
        System.out.println("  -f, --feed <feedKey>:                Fetch and process the feed with");
        System.out.println("                                       the specified key");
        System.out.println("                                       Available feed keys are: ");
        for (FeedsData feedData : feedsDataArray) {
            System.out.println("                                       " + feedData.getLabel());
        }
        System.out.println("  -ne, --named-entity <heuristicName>: Use the specified heuristic to extract");
        System.out.println("                                       named entities");
        System.out.println("                                       Available heuristic names are: ");
        // Print the available heuristics
        Map<String, String> heuristicDescriptions = HeuristicFactory.getHeuristicDescriptions();
        for (Map.Entry<String, String> entry : heuristicDescriptions.entrySet()) {
            System.out.println("                                       " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("  -pf, --print-feed:                   Print the fetched feed");
        System.out.println("  -sf, --stats-format <format>:        Print the stats in the specified format");
        System.out.println("                                       Available formats are: ");
        System.out.println("                                       cat: Category-wise stats");
        System.out.println("                                       topic: Topic-wise stats");
    }
}
