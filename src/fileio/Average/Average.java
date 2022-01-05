package fileio.Average;

import java.util.List;

public class Average {
    private AverageScoreCalculator averageScoreCalculator;

    public Average(AverageScoreCalculator averageScoreCalculator) {
        this.averageScoreCalculator = averageScoreCalculator;
    }

    public double executeAverage(List<Double> niceScoreHistory) {
        return averageScoreCalculator.doAverage(niceScoreHistory);
    }
}
