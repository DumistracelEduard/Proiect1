package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.*;
import fileio.DataStore.InputData;
import fileio.DataStore.InputLoader;
import fileio.InputClass.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SingleRun {
    private static SingleRun instance = null;

    private SingleRun() {

    }

    public static SingleRun getInstance() {
        if (instance == null) {
            instance = new SingleRun();
        }
        return instance;
    }

    public void runProgram() throws IOException {
        Path path = Paths.get(Constants.OUTDIRECTORY);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        for (int i = 1; i < 26; ++i) {
            String output = Constants.OUTDIRECTORY +"/"+ Constants.OUTPATH + i + ".json";
            String input = Constants.PATHINPUT + "/test" + i + ".json";
            run(input, output);
        }
    }

    public static void calculateData(Children children, double santaBudget, HashMap<String, ArrayList<Gift>> listGift) {
        double sum = 0;
        double budgetUnit;
        for (Child child : children.getChildren()) {
            child.calculateAverage();
            sum += child.getAverageScore();
        }
        budgetUnit = santaBudget / sum;
        Utils.distributionGift(budgetUnit, children, listGift);
    }

    public static void run(final String input, final String output) throws IOException {
        InputLoader inputLoader = new InputLoader(input);
        InputData inputData = inputLoader.readData();

        Path path = Paths.get(output);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        List<Object> listGift = (List<Object>) inputData.getDataStore().get(1);
        HashMap<String, ArrayList<Gift>> gifts = Utils.convertObjectGift(listGift);
        Children children = new Children(inputData.getDataStore());
        calculateData(children, inputData.getSantaBudget(), gifts);
        AnnualChildren annualChildren = new AnnualChildren(inputData.getNumberOfYears(), children);
        annualChildren.annualUpdate(inputData.getAnnualChanges(), annualChildren, gifts, inputData.getNumberOfYears());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(output), annualChildren);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
