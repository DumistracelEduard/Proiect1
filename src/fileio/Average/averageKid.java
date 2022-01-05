package fileio.Average;

import java.util.List;

public class averageKid implements AverageScoreCalculator {
    @Override
    public double doAverage(List<Double> niceScoreHistory) {
        if(niceScoreHistory.size() != 1) {
            double sum = 0;
            for (double number : niceScoreHistory) {
                sum += number;
            }
            return sum/niceScoreHistory.size();
        }
        return niceScoreHistory.get(0);
    }
}
