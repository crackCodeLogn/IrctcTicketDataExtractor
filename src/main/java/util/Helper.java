package util;

import core.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static util.Constants.*;

/**
 * @author Vivek
 * @version 1.0
 * @since 08-07-2019
 */
public class Helper {

    private static final Logger logger = LoggerFactory.getLogger(Helper.class);

    private final static SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
    private final static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy_HHmmss");

    public static Properties getPropertiesForProject() {
        final Properties properties = new Properties();

        try (FileReader in = new FileReader(PROPERTIES_FILE)) {
            properties.load(in);
        } catch (IOException e) {
            logger.error("FNF / R-W error for properties file '{}' : ", PROPERTIES_FILE, e);
        }
        return properties;
    }

    /**
     * @param date dd-MMM-yyyy format
     * @return
     */
    public static Long convertDisplayDateToComputableDate(String inputDate) {
        try {
            Date date = sdf1.parse(inputDate);
            return Long.parseLong(sdf2.format(date));
        } catch (ParseException e) {
            logger.error("Error while parsing '{}' to Date, ", inputDate, e);
        }
        return DEFAULT_COMPUTABLE_DOJ;
    }

    public static String generateCsvExportName() {
        return String.format(EXPORT_CSV_TITLE_EXPENSES, sdf3.format(new Date()));
    }

    public static String getColumnHeaderForExpensesCsv() {
        final Field[] fields = Expense.class.getDeclaredFields();
        final StringBuilder headerString = new StringBuilder();

        for (Field field : fields) {
            logger.info(field.getName());
            headerString.append(field.getName()).append(",");
        }
        headerString.deleteCharAt(headerString.length() - 1);
        headerString.append("\n");
        return headerString.toString();
    }
}
