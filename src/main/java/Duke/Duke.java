package Duke;

import java.io.IOException;

import Duke.Commands.Command;
import Duke.Storage.FileFormatException;
import Duke.Storage.Storage;
import Duke.Storage.TaskStorage;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui.Parser;
import Duke.Ui.Ui;
import Duke.Ui.UserInput;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Duke class encapsulates the main logic and data for running the chat-bot.
 *
 * @author cai
 */
public class Duke extends Application {
    /** Default path for storing tasks */
    private static final String DEFAULT_TASK_STORAGE_PATH = "data/duke.txt";

    /** List of tasks as added by the user */
    private TaskList taskList;

    /** User interface */
    private Ui ui;

    @Override
    public void start(Stage stage) throws IOException {
        ui = new Ui(stage, this);
        say(Ui.GREETING_MESSAGE);

        Storage<Task> taskStorage = new TaskStorage(DEFAULT_TASK_STORAGE_PATH);
        try {
            this.taskList = new TaskList(taskStorage);
        } catch (FileFormatException e) {
            System.out.println(Ui.FILE_FORMAT_ERROR_MESSAGE);
            throw e;
        }
    }

    public void say(String message) {
        assert ui != null;
        ui.addDukeMessage(message);
    }

    /**
     * Parses the given input as a command and runs it.
     *
     * @param inputString Raw user input.
     */
    public void parseAndRun(String inputString) {
        UserInput input = Parser.parse(inputString);
        try {
            Command.matching(input).run(this, input);
        } catch (DukeException e) {
            say(String.format(Ui.ERROR_MESSAGE, e.getMessage()));
        }
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
