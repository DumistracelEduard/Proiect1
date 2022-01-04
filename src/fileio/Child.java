package fileio;

import java.util.ArrayList;
import java.util.List;

public class Child {
    private final Integer id;
    private final String lastName;
    private final String firstName;
    private final String city;
    private Integer age;
    private final ArrayList<String> giftsPreferences;
    private double averageScore;
    private final List<Double> niceScoreHistory;
    private double assignedBudget;
    private final List<gift> receivedGifts;

    public Child(Integer id, String lastName, String firstName, Integer age,
                 String city, double averageScore,
                 ArrayList<String> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.averageScore = averageScore;
        this.giftsPreferences = giftsPreferences;
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.add(averageScore);
        this.assignedBudget = 0;
        this.receivedGifts = new ArrayList<>();
    }

    public Child(Child child) {
        this.id = child.id;
        this.lastName = child.lastName;
        this.firstName = child.firstName;
        this.age = child.age;
        this.city = child.city;
        this.averageScore = child.averageScore;
        this.giftsPreferences = child.giftsPreferences;
        this.niceScoreHistory = child.niceScoreHistory;
        this.assignedBudget = child.assignedBudget;
        this.receivedGifts = child.receivedGifts;
    }

    public void addNiceScore(double newScore) {
        if (newScore != -1) {
            this.niceScoreHistory.add(newScore);
            double sum = 0;
            int counter = 0;
            for(Double niceScore : niceScoreHistory) {
                sum += niceScore;
                ++counter;
            }
            this.averageScore = sum / counter;
        }
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public List<gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
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

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public ArrayList<String> getGiftsPreference() {
        return giftsPreferences;
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
                '}';
    }
}
