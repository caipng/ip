package Duke.Commands;

import Duke.Duke;
import Duke.DukeException;
import Duke.Task.InvalidTaskException;
import Duke.Ui.UserInput;

import java.util.Set;

/**
 * An instruction, possibly with arguments, to be executed.
 *
 * @author cai
 */
public abstract class Command {
    /** List of possible commands to run */
    private static final Command[] COMMAND_LIST = {
            new ExitCommand(),
            new AddTaskCommand(),
            new ListTasksCommand(),
            new FindTaskCommand(),
            new DoneTaskCommand(),
            new RemoveTaskCommand()
    };

    private static final String INVALID_TASK_NUMBER_FORMAT_MESSAGE = "\"%s\" is not a valid task number.";
    protected static final String TASKS_COUNT_MESSAGE = "Now you have %d tasks in the list.";

    /**
     * Returns the command matching the specified user input.
     *
     * @param input Input to match against each command in the command list.
     * @return The respective command object if matched.
     * @throws UnknownCommandException If no command matching the keyword was found.
     */
    public static Command matching(UserInput input) throws UnknownCommandException {
        String keyword = input.getKeyword();
        for (Command cmd : COMMAND_LIST) {
            // Case insensitive contains
            if (cmd.getKeywords().stream().anyMatch(keyword::equalsIgnoreCase)) {
                return cmd;
            }
        }

        throw new UnknownCommandException(input.getRaw());
    }

    /**
     * Runs the command.
     *
     * @param duke The Duke instance to run the command on.
     * @param input The user input that triggered this command.
     * @throws DukeException If a DukeException was thrown when running the command.
     */
    abstract public void run(Duke duke, UserInput input) throws DukeException;

    /**
     * Returns a list of keywords to match against.
     * The user input is matched against this list of keywords to determine which command to run.
     *
     * @return The list of keywords corresponding to this command.
     */
    abstract protected Set<String> getKeywords();

    /**
     * Parses the task number in the user input as an integer.
     *
     * @param input The input containing the task number.
     * @return The integer value of the task number.
     * @throws InvalidTaskException If an invalid task number was provided.
     */
    protected static int parseTaskNumber(UserInput input) throws InvalidTaskException {
        String args = input.getArgs();
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException(String.format(INVALID_TASK_NUMBER_FORMAT_MESSAGE, args));
        }
    }
}
