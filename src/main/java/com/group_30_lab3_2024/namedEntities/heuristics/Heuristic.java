package com.group_30_lab3_2024.namedEntities.heuristics;

import java.io.Serializable;
import java.util.List;

public interface Heuristic extends Serializable {
    List<String> extractCandidates(String input);
}