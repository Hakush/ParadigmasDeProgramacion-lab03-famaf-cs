package com.group_30_lab3_2024.namedEntities.heuristics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.group_30_lab3_2024.App;

// Esta heuristica utiliza las palabras guardadas en el archivo "evolutionHeu.txt" para generar una lista de posibles palabras candidatas.
// El archivo esta compuesto por todas las palabras que fueron en algun momento candidatas con la heuristica "SecureCapitalizedWordHeuristic".
// Luego tomando esta lista, se busca en el texto las palabras que coincidan con las palabras del archivo.
// Tip: Si hay una palabra incorrecta en los candidatos antiguos, se puede entrar al archivo y editar manualmente.

public class EvolvingSecureCapWordHeu implements Heuristic {
    String filepath = (App.PathFile + "/src/main/java/com/group_30_lab3_2024/data/");

    @Override
    public List<String> extractCandidates(String text) { // Tomo la lista de candidatos posibles adquirida desde
                                                         // SecureCapitalizedWordHeuristics.java

        List<String> candidates = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filepath);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            text = text.replaceAll("[-+.^:,\"]", "");
            text = Normalizer.normalize(text, Normalizer.Form.NFD);
            text = text.replaceAll("\\p{M}", "");

            List<String> txt = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                txt.add(line);
                line = bufferedReader.readLine();
            }

            String regex = "\\b(" + String.join("|", txt) + ")\\b";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                candidates.add(matcher.group());
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer en el archivo.");
            System.out.println("Exception: " + e.getMessage());
        }

        return candidates;
    }
}