package com.leviatan.backend.factory;

import com.leviatan.backend.dto.manual_analysis.*;
import com.leviatan.backend.model.league.Team;
import com.leviatan.backend.model.manual_analysis.ManualMatchAnalysis;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ManualMatchAnalysisResultFactory {

    //TODO implement multiple analyses merge
    public ManualMatchAnalysisResult convert(List<ManualMatchAnalysis> analyses, List<Team> sides) {
        return new ManualMatchAnalysisResult();
    }


}
