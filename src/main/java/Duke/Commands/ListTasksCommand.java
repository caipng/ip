package Duke.Commands;

import Duke.Duke;
import Duke.Ui.Ui;
import Duke.Ui.UserInput;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ListTasksCommand extends Command {
    private static final Set<String> KEYWORDS = new HashSet<>(List.of("list", "ls"));
    private static final String HELP_MESSAGE = "Here are the tasks in your list:\n%s";

    @Override
    public void run(Duke duke, UserInput input) {
        Ui.print(String.format(HELP_MESSAGE, duke.getTaskList().toString()));
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORDS;
    }
}
