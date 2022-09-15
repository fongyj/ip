package chick;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that handles loading and saving of tasks for Duke Bot.
 */
public class Storage {

    private FileWriter fileWriter;
    private String previousText;

    /**
     * Class constructor for Storage.
     *
     * Calling the constructor will:
     * 1. Create a data folder if it doesn't already exist.
     * 2. Load text at data/duke.txt if it exists.
     * 3. Erases file at data/duke.txt if it exists to write next save file.
     */
    public Storage() {
        File dataFolder = new File("data");
        if (!dataFolder.exists()) {
            // make data directory if it doesnt exist
            dataFolder.mkdir();
            assert dataFolder.exists();
        } else if (new File("data/chick.txt").exists()) {
            // else if save file exists, load it into memory
            try {
                previousText = Files.readString(Path.of("data/chick.txt"));
            } catch (java.io.IOException e) {
                System.out.println("Unable to read storage file: " + e);
            }
        }
        try {
            fileWriter = new FileWriter("data/chick.txt");
        } catch (java.io.IOException e) {
            System.out.println("Unable to write storage file: " + e);
        }
    }

    /**
     * Returns saved text from previous instance of Duke Bot.
     *
     * @return Saved text from previous instance of Duke Bot.
     */
    public String getPreviousText() {
        return previousText;
    }

    /**
     * Writes a string to save file of Duke Bot.
     *
     * @param text String to be written to save file.
     * @param nextLine Boolean, if true newline character will be written after string is written.
     */
    public void writeText(String text, boolean nextLine) {
        try {
            fileWriter.write(text);
            if (nextLine) {
                fileWriter.write("\n");
            }
        } catch (java.io.IOException e) {
            System.out.println("Failed to write text to save file: " + e);
        }
    }

    /**
     * Closes FileWriter instance for writing save file.
     */
    public void closeWriter() {
        try {
            fileWriter.close();
        } catch (java.io.IOException e) {
            System.out.println("Failed to close save file: " + e);
        }
    }
}
