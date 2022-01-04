package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class SingleRun {
    private static SingleRun instance = null;

    private SingleRun() {

    }

    public static SingleRun getInstance() {
        if  (instance == null) {
            instance = new SingleRun();
        }
        return instance;
    }

    public void runProgram() throws IOException {
        File directory = new File(Constants.PATHINPUT);
        Path path = Paths.get(Constants.OUTDIRECTORY);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String output = Constants.OUTDIRECTORY +"/"+ Constants.OUTPATH + file.getName();
            run(file.getAbsolutePath(), output);
        }
    }

    public static void run(final String input, final String output) throws IOException {
        InputLoader inputLoader = new InputLoader(input);
        InputData inputData = inputLoader.readData();

        Path path = Paths.get(output);
        if(!Files.exists(path)) {
            Files.createFile(path);
        }

        Children children = new Children(inputData.getDataStore());
        AnnualChildren annualChildren = new AnnualChildren(inputData.getNumberOfYears(), children);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(output), annualChildren);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
