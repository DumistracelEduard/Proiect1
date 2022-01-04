package writeToFile;

import java.io.FileWriter;
import java.nio.file.Path;

public final class WriteToFile {
    private final FileWriter file;

    public WriteToFile(final String path) throws Exception {
        this.file = new FileWriter(path);
    }

    public void WriteFile() {

    }
}
