package fileio.Average;

import java.util.List;

public class averageTeen implements AverageScoreCalculator {
    @Override
    public double doAverage(List<Double> niceScoreHistory) {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < niceScoreHistory.size(); ++i) {
            sum += ((i + 1) * niceScoreHistory.get(i));
            count += (i + 1);
        }
        return sum/count;
    }
}
