package fileio;

import java.util.ArrayList;

public class ChildUpdate {
    private final Integer id;
    private final double niceScore;
    private final ArrayList<String> giftsPreference;


    public ChildUpdate(Integer id, double niceScore, ArrayList<String> giftsPreference) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreference = giftsPreference;
    }

    public Integer getId() {
        return id;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getGiftsPreference() {
        return giftsPreference;
    }
}
