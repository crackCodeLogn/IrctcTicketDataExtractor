import core.Expense;
import launcher.Launcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import port.ExportAsCsv;
import util.Helper;

import java.util.List;
import java.util.Properties;

import static util.Constants.BASE_FOLDER_TICKETS;
import static util.Constants.EXPORT_CSV_LOCATION;

/**
 * @author Vivek
 * @version 1.0
 * @since 08-07-2019
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final Properties props = Helper.getPropertiesForProject();

    public static void main(String[] args) {
        final Launcher launcher = new Launcher(props.getProperty(BASE_FOLDER_TICKETS, "/"));
        List<Expense> expenses = launcher.fireComputationOnFolder();
        logger.info("Got {} expenses from the folder '{}'", expenses.size(), BASE_FOLDER_TICKETS);

        pruneExtraExpenses(expenses);
        logger.info("Got {} expenses after pruning : {}", expenses.size(), expenses);

        new ExportAsCsv(expenses, props.getProperty(EXPORT_CSV_LOCATION, "sample/output")).export();
    }

    public static void pruneExtraExpenses(List<Expense> expenses) {
        expenses.removeIf(expense -> expense.getFare().isNaN());
    }
}
