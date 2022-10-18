package com.leviatan.backend.model.analysis.metadata;

import com.leviatan.backend.model.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreInfo implements Serializable {
    private Integer assists;
    private Integer creepScore;
    private Integer deaths;
    private Integer kills;
    private Double wardScore;

    public static ScoreInfo from(Score score, Integer creepScore, Double wardScore) {
        return ScoreInfo.builder()
                .assists(score.getAssists())
                .creepScore(creepScore)
                .deaths(score.getDeaths())
                .kills(score.getKills())
                .wardScore(wardScore)
                .build();
    }
}
