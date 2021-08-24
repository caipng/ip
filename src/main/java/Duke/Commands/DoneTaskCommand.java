package Duke.Commands;

import Duke.Duke;
import Duke.Task.InvalidTaskException;
import Duke.Task.Task;
import Duke.Task.TaskList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class DoneTaskCommand extends Command {
    private static final Set<String> KEYWORDS = new HashSet<>(List.of("done"));
    private static final String DONE_TASK_SUCCESS_MESSAGE = "Good job! I've marked this task as done:\n\t%s";

    @Override
    public void run(Duke duke, Duke.UserInput input) throws InvalidTaskException {
        int taskIndex = parseTaskNumber(input);
        TaskList taskList = duke.getTaskList();
        taskList.markAsDone(taskIndex);
        duke.say(String.format(DONE_TASK_SUCCESS_MESSAGE, taskList.get(taskIndex)));
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORDS;
    }
}
