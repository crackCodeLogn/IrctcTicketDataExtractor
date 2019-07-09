package util;

/**
 * @author Vivek
 * @version 1.0
 * @since 08-07-2019
 */
public class Constants {

    //FOR PDF
    public static final String REGEX_PDF_EXTRACTION = "\\r?\\n";

    //FOR PROPERTIES
    public static final String PROPERTIES_FILE = "src/main/resources/config.properties";
    public static final String BASE_FOLDER_TICKETS = "base.folder.tickets";
    public static final String EXPORT_CSV_LOCATION = "export.csv.location";

    //FOR EXPORTING
    public static final String EXPORT_CSV_TITLE_EXPENSES = "EXPENSE_FOR_TRAIN_TRAVEL_UPTO_%s.csv";

    //FOR DEFAULTS FOR EXPENSE CLASS
    public static final String DEFAULT_DATE_OF_JOURNEY = "01-Jan-4567"; //dd-MM-yyyy
    public static final long DEFAULT_COMPUTABLE_DOJ = 45670101L; //yyyyMMdd
    public static final long DEFAULT_PNR = 1234567890L;
    public static final int DEFAULT_TRAIN_NUMBER = -1;
    public static final String DEFAULT_TRAIN_NAME = "TRAIN EXP";
    public static final String DEFAULT_SRC_STATION = "SOMEWHERE";
    public static final String DEFAULT_BOARDING_STATION = "SOMEWHERE_BOARDING";
    public static final String DEFAULT_DESTINATION_STATION = "DESTINATION";
    public static final double DEFAULT_FARE = Double.NaN;

    //FOR THE REFINER
    public static final String DOJ = "Date Of Journey:";
    public static final String DOB = "Date Of Boarding:";
    public static final String FROM = "From:";
    public static final String BOARDING_AT = "Boarding At:";
    public static final String TO = "To:";
    //public static final String TICKET_FARE = "Ticket Fare **";
    public static final String TICKET_FARE = "Total Fare (all inclusive)";
    public static final String RUPEES = "Rupees";
    public static final String PNR_NO = "PNR No:";
    public static final String TRAIN_NUM_NAME = "Train No. & Name:";
    public static final String QUOTA = "Quota:";

    public static final String PDF = ".pdf";
}
