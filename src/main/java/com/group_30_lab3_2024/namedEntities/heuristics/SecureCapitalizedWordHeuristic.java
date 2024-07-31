package com.group_30_lab3_2024.namedEntities.heuristics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.group_30_lab3_2024.App;

// Esta heuristica utiliza la heuristica original de las mayusculas y unicamente guarda las que aparezcan dos o mas veces
// Esto es para evitar algunas palabras que comiencen una oracion y no sean nombres propios.

// A parte, guarda las palabras en un archivo txt, para luego poder tomarlas en cuenta en la heuristica "EvolvingSecureCapWordHeu.java"

public class SecureCapitalizedWordHeuristic implements Heuristic {
    String filepath = (App.PathFile + "/src/main/java/com/group_30_lab3_2024/data/evolutionHeu.txt");

    @Override
    public List<String> extractCandidates(String text) { // Tomo la lista de candidatos posibles adquirida desde
                                                         // CapitalizedWordHeuristics.java
        CapitalizedWordHeuristic capitalizedWordHeuristic = new CapitalizedWordHeuristic();
        List<String> posCandidates = capitalizedWordHeuristic.extractCandidates(text);

        List<String> auxList = new ArrayList<>();

        // añado a auxList cada elemento de posCandidates una sola vez
        for (String cand : posCandidates){
            if (!auxList.contains(cand)){
                auxList.add(cand);
            }
        }

        // elimino de posCandidates los elementos que estan en auxList
        for (String rem : auxList){
            posCandidates.remove(rem);
        }

        // limpio auxList y añado nuevamente una vez los elementos de posCandidates 
        auxList.clear();
        for (String cand : posCandidates){
            if (!auxList.contains(cand)){
                auxList.add(cand);
            }
        }

        
        List<String> candidates = posCandidates;
        candidates.addAll(auxList);


        try (FileReader fileReader = new FileReader(filepath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                FileWriter fileWriter = new FileWriter(filepath, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            List<String> txt = new ArrayList<>();

            String line = bufferedReader.readLine();
            while (line != null) {
                txt.add(line);
                line = bufferedReader.readLine();
            }

            for (String candidate : candidates) {
                if (!txt.contains(candidate)) {
                    bufferedWriter.write(candidate + "\n");
                    txt.add(candidate);
                }
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            System.out.println("Exception: " + e.getMessage());
        }
        return candidates;
    }
}
