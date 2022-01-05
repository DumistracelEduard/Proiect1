package fileio.DataStore;

import fileio.InputClass.AnnualChanges;
import fileio.InputClass.Child;
import fileio.InputClass.Gift;

import java.util.ArrayList;
import java.util.List;

public class InputData {
    private final Integer numberOfYears;
    private double santaBudget;
    private List<Object> dataStore = new ArrayList<>();
    private final List<AnnualChanges> annualChanges;

    public InputData(final Integer numberOfYears, final double santaBudget, final List<Child> childList,
                     final List<Gift> giftList, final List<String> cityList, final List<AnnualChanges> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.dataStore.add(childList);
        this.dataStore.add(giftList);
        this.dataStore.add(cityList);
        this.annualChanges = annualChanges;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public List<Object> getDataStore() {
        return dataStore;
    }

    public List<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }
}
