import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
public class DataSaver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;
        boolean done = false;

        while(!done){
            String firstName = SafeInput.getNonZeroLenString(in, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name");
            String email = SafeInput.getNonZeroLenString(in, "Enter Email");
            int birthYear = SafeInput.getRangedInt(in, "Enter Year of Birth", 1900, 2025);

            String id = String.format("%06d", idCounter);
            idCounter++;

            String csvRecord = String.format("%s, %s, %s, %s, %d", firstName, lastName, id, email, birthYear);
            records.add(csvRecord);

            done = !SafeInput.getYNConfirm(in, "Do you want to enter another record?");
        }

        String fileName = SafeInput.getNonZeroLenString(in, "Enter filename to save (without extension)") + ".csv";
        Path filePath = Path.of("src", fileName);

        try {
            PrintWriter writer = new PrintWriter(Files.newBufferedWriter(filePath));

            for (String record : records) {
                writer.println(record);
            }

            writer.close();
            System.out.println("Data saved to: " + filePath);

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }

    }
}