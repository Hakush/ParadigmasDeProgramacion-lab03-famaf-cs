package com.group_30_lab3_2024.namedEntities.heuristics;

import java.util.HashMap;
import java.util.Map;

public class HeuristicFactory {
    private static final Map<String, String> heuristicMap = new HashMap<>();
    private static final Map<String, String> heuristicDescriptions = new HashMap<>();

    static {
        // -- CLass names --
        heuristicMap.put("Capitalized", "com.group_30_lab3_2024.namedEntities.heuristics.CapitalizedWordHeuristic");
        heuristicMap.put("Locations", "com.group_30_lab3_2024.namedEntities.heuristics.LocationsHeuristic");
        heuristicMap.put("Persons", "com.group_30_lab3_2024.namedEntities.heuristics.PersonsHeuristics");
        heuristicMap.put("Organizations", "com.group_30_lab3_2024.namedEntities.heuristics.OrganizationsHeuristics");
        heuristicMap.put("Secure", "com.group_30_lab3_2024.namedEntities.heuristics.SecureCapitalizedWordHeuristic");
        heuristicMap.put("Dict", "com.group_30_lab3_2024.namedEntities.heuristics.DictionaryHeuristic");
        heuristicMap.put("Evolve", "com.group_30_lab3_2024.namedEntities.heuristics.EvolvingSecureCapWordHeu");
        heuristicMap.put("LongWords", "com.group_30_lab3_2024.namedEntities.heuristics.LongWordsHeuristic");

        // -- Descriptions ---

        heuristicDescriptions.put("Capitalized", "Heuristic for words starting with capital letters");
        heuristicDescriptions.put("Locations", "Heuristic for only Locations");
        heuristicDescriptions.put("Persons", "Heuristic for only Persons");
        heuristicDescriptions.put("Organizations", "Heuristic for only Organizations");
        heuristicDescriptions.put("Secure", "Heuristic for capitalized words that appears more than once on an article");
        heuristicDescriptions.put("Dict", "Heuristic for words that are in the dictionary");
        heuristicDescriptions.put("Evolve",
                "Heuristic for words that were candidates in the past with the Secure heuristic");
        heuristicDescriptions.put("LongWords", "Heuristic for words with 7 or more characters");
    }

    public static String getHeuristicClassName(String heuristicName) {
        return heuristicMap.get(heuristicName);
    }

    public static Map<String, String> getHeuristicDescriptions() {
        return heuristicDescriptions;
    }
}
