import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notes {
    private static final String FILE_NAME = "notes.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    writeNote();
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.println("\nThank you for using NotesApp.. ");
                    return;
                default:
                    System.out.println("Invalid option! Please choose 1, 2, or 3.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=============================");
        System.out.println("        NOTES APP         ");
        System.out.println("=============================");
        System.out.println("1.Write a New Note");
        System.out.println("2.Read All Notes");
        System.out.println("3.Exit");
        System.out.print("Select your option (1-3): ");
    }

    private static int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number (1-3): ");
            scanner.next(); 
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        return choice;
    }

    private static void writeNote() {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedNote = "[" + timestamp.format(formatter) + "] " + note;

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(formattedNote + "\n");
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    private static void readNotes() {
        // System.out.println("\n=============================");
        System.out.println("        # YOUR NOTES #        ");
        System.out.println("=============================");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(count + ". " + line);
                count++;
            }
            if (count == 1) {
                System.out.println("No notes found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }
}
