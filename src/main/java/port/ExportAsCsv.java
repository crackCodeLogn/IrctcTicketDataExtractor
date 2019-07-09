package port;

import core.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Vivek
 * @version 1.0
 * @since 09-07-2019
 */
public class ExportAsCsv {

    private final Logger logger = LoggerFactory.getLogger(ExportAsCsv.class);

    private final List<Expense> expenses;
    private final String csvDesiredLocation;

    public ExportAsCsv(List<Expense> expenses, String csvDesiredLocation) {
        this.expenses = expenses;
        this.csvDesiredLocation = csvDesiredLocation;
    }

    public void export() {
        final StringBuilder csv = new StringBuilder();
        expenses.forEach(expense -> csv.append(expense.toCsvString()));

        final File destination = new File(csvDesiredLocation + "/" + Helper.generateCsvExportName());
        try (FileWriter fileWriter = new FileWriter(destination)) {
            fileWriter.write(Helper.getColumnHeaderForExpensesCsv());
            fileWriter.write(csv.toString());
            logger.info("Export to CSV complete! File location : '{}'", destination.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Error while writing to the export csv file '{}' : ", destination.getAbsolutePath(), e);
        }
    }
}
