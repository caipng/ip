package Duke.Ui;

import java.util.Scanner;

public class Ui {
    public static final String GREETING_MESSAGE = "Hello I'm Duke!";
    public static final String EXIT_MESSAGE = "Bye bye! Hope you have a productive day :)";
    public static final String ERROR_MESSAGE = "Oops! An error occurred: %s";
    public static final String FILE_FORMAT_ERROR_MESSAGE = "The file storing your tasks is in an unrecognized format. "
            + "Please fix or remove it.";
    private static final String RULER = "\n````````````````````````````````````````````````````````\n";
    private static final String INPUT_PROMPT = "> ";

    private static final Scanner scanner = new Scanner(System.in);

    public static void print(String message) {
        message = RULER + message + RULER;
        String indentedMessage = String.join("\n\t", message.split("\n"));
        System.out.println(indentedMessage);
    }

    public static String read() {
        System.out.print(INPUT_PROMPT);
        return scanner.nextLine();
    }
}
