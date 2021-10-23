package com.leviatan.backend.utils;

import com.leviatan.backend.model.pagination.AnalysisSort;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisUtils {
    public final static Map<AnalysisSort, List<String>> ANALYSIS_SORT_PROPERTIES = new HashMap<>() {{
        put(AnalysisSort.ID, Collections.singletonList("id"));
        put(AnalysisSort.ANALYSIS_DATE, Collections.singletonList("analysis_date"));
        put(AnalysisSort.MATCH_DATE, Collections.singletonList("match_date"));
    }};
}
