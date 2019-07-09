package launcher;

import core.Expense;
import engine.DetailsExtractor;
import engine.DetailsRefiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.Constants.PDF;

/**
 * @author Vivek
 * @version 1.0
 * @since 09-07-2019
 */
public class Launcher {

    private final Logger logger = LoggerFactory.getLogger(Launcher.class);

    private final String baseFolderPath;

    public Launcher(String baseFolderPath) {
        this.baseFolderPath = baseFolderPath;
    }

    private void fireOnEachFile(File file, AtomicInteger fileNumber, List<Expense> expenses) {
        logger.info("{}. {}", fileNumber.getAndIncrement(), file.getName());
        try {
            final List<String> extractedLines = DetailsExtractor.convertPdfToStringList(file.getAbsolutePath());
            final Expense expense = DetailsRefiner.refineDetailsFromTheExtract(extractedLines);
            logger.info(expense.toString());
            expenses.add(expense);
        } catch (Exception e) {
            logger.error("FAILED to compute the expense for '{}'. Look up this error : ", file.getName(), e);
        }
    }

    public List<Expense> fireComputationOnFolder() {
        final List<Expense> expenses = new ArrayList<>();
        final File folder = new File(baseFolderPath);
        final AtomicInteger fileNumber = new AtomicInteger(1);

        if (folder.isDirectory()) {
            final File[] files = folder.listFiles();

            assert files != null;
            if (files.length > 0) {
                Arrays.stream(files).forEach(file -> {
                    if (checkForIrctcPdfValidness(file.getName())) fireOnEachFile(file, fileNumber, expenses);
                });
            } else {
                logger.warn("Nothing to go on in the folder '{}'", baseFolderPath);
            }
        } else {
            fireOnEachFile(folder, fileNumber, expenses);
        }

        logger.info("Populated expenses list with {} expenses instances..", expenses.size());
        return expenses;
    }

    private boolean checkForIrctcPdfValidness(String fileName) {
        return fileName.endsWith(PDF) && !(fileName.contains("Paytm") || fileName.contains("PAYTM"));
    }
}
