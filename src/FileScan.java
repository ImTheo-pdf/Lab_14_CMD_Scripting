import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileScan {
    public static void main(String[] args) {
        Path filePath = null;

        if (args.length > 0) {
            filePath = Path.of(args[0]);

            if (!Files.exists(filePath)) {
                System.out.println("File not found: " + args[0]);
                return;
            }
        } else {

            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                filePath = chooser.getSelectedFile().toPath();
            } else {
                System.out.println("No file selected. Program ending.");
                return;
            }
        }

        try {
            List<String> lines = Files.readAllLines(filePath);
            int lineCount = 0, wordCount = 0, charCount = 0;

            for (String line : lines) {
                System.out.println(line);
                lineCount++;
                wordCount += line.trim().isEmpty() ? 0 : line.trim().split("\\s+").length;
                charCount += line.length() + System.lineSeparator().length();
            }

            System.out.println("\n--- File Summary ---");
            System.out.println("File: " + filePath.getFileName());
            System.out.println("Lines: " + lineCount);
            System.out.println("Words: " + wordCount);
            System.out.println("Characters: " + charCount);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}