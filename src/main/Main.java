package main;

import checker.Checker;
import fileio.Constants;
import fileio.InputData;
import fileio.InputLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.PATHINPUT);
        Path path = Paths.get(Constants.OUTDIRECTORY);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String output = Constants.OUTDIRECTORY +"/"+ Constants.OUTPATH + file.getName();
            run(file.getAbsolutePath(), output);
        }
        Checker.calculateScore();
    }

    public static void run(final String input, final String output) throws IOException {
        InputLoader inputLoader = new InputLoader(input);
        InputData inputData = inputLoader.readData();

        Path path = Paths.get(output);
        if(!Files.exists(path)) {
            Files.createFile(path);
        }
        //FileWriter writerFile = new FileWriter(output);

        //writerFile.close();
    }
}
