package fileio.InputClass;

import fileio.Average.Average;
import fileio.Average.averageBaby;
import fileio.Average.averageKid;
import fileio.Average.averageTeen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Child {
    private final Integer id;
    private final String lastName;
    private final String firstName;
    private final String city;
    private Integer age;
    private ArrayList<String> giftsPreferences;
    private double averageScore;
    private final List<Double> niceScoreHistory;
    private double assignedBudget;
    private List<Gift> receivedGifts;

    public Child(Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.age = child.getAge() + 1;
        this.city = child.getCity();
        this.giftsPreferences = new ArrayList<>(child.getGiftsPreferences());
        this.niceScoreHistory = new ArrayList<>(child.getNiceScoreHistory());
        this.receivedGifts = new ArrayList<>();
    }
    public Child(Integer id, String lastName, String firstName, Integer age,
                 String city,ArrayList<String> giftsPreferences, double averageScore) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.averageScore = averageScore;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.add(averageScore);
        this.receivedGifts = new ArrayList<>();
    }
    
    public void updateGiftPreferences(List<String> newPreferences) {
        int ok = 0;
        for (String oldPreferences: giftsPreferences) {
            ok = 0;
            for (String newPreference: newPreferences) {
                if(oldPreferences.equals(newPreference)) {
                    ok = 1;
                }
            }
            if (ok == 0) {
                newPreferences.add(oldPreferences);
            }
        }

        HashMap<String, String> duplicate = new HashMap<>();
        ArrayList<String> finalPreferences = new ArrayList<>();
        for(int i = 0; i < newPreferences.size(); ++i) {
            if(!duplicate.containsKey(newPreferences.get(i))) {
                finalPreferences.add(newPreferences.get(i));
                duplicate.put(newPreferences.get(i), newPreferences.get(i));
            }
        }
        this.giftsPreferences = finalPreferences;
    }

    public Double calculateBudget(double budgetUnit){
        this.assignedBudget = averageScore * budgetUnit;
        return assignedBudget;
    }

    public void calculateAverage() {
        if (age < 5) {
            Average average = new Average(new averageBaby());
            this.averageScore = average.executeAverage(niceScoreHistory);
        } else if( age < 12) {
            Average average = new Average(new averageKid());
            this.averageScore = average.executeAverage(niceScoreHistory);
        } else {
            Average average = new Average(new averageTeen());
            this.averageScore = average.executeAverage(niceScoreHistory);
        }
    }

    public void addNiceScore(double newScore) {
        if (newScore != -1 && age <= 18) {
            this.niceScoreHistory.add(newScore);
            calculateAverage();
        }
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", giftsPreferences=" + giftsPreferences +
                ", averageScore=" + averageScore +
                ", niceScoreHistory=" + niceScoreHistory +
                ", assignedBudget=" + assignedBudget +
                ", receivedGifts=" + receivedGifts +
                "}\n";
    }
}
