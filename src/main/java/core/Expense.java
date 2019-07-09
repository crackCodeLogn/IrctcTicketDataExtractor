package core;

import util.Helper;

import java.lang.reflect.Field;

import static util.Constants.*;

/**
 * @author Vivek
 * @version 1.0
 * @since 08-07-2019
 */
public class Expense {

    private String displayDate = DEFAULT_DATE_OF_JOURNEY; //dd-MM-yyyy
    private Long computableDate = DEFAULT_COMPUTABLE_DOJ; //yyyyMMdd
    private Long pnr = DEFAULT_PNR;
    private Integer trainNumber = DEFAULT_TRAIN_NUMBER;
    private String trainName = DEFAULT_TRAIN_NAME;
    private String sourceStation = DEFAULT_SRC_STATION;
    private String boardingStation = DEFAULT_BOARDING_STATION;
    private String destinationStation = DEFAULT_DESTINATION_STATION;
    private Double fare = DEFAULT_FARE;

    public Expense(String displayDate, Long pnr, Integer trainNumber, String trainName, String sourceStation, String boardingStation, String destinationStation, Double fare) {
        this.displayDate = displayDate;
        this.computableDate = Helper.convertDisplayDateToComputableDate(displayDate);
        this.pnr = pnr;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.sourceStation = sourceStation;
        this.boardingStation = boardingStation;
        this.destinationStation = destinationStation;
        this.fare = fare;
    }

    @Override
    public String toString() {
        return String.format("Expense ~ %s %d %s %s => '%s' To '%s', FROM '%s' :: Rs. %.2f",
                pnr,
                trainNumber,
                trainName,
                displayDate,
                sourceStation,
                destinationStation,
                boardingStation,
                fare);
    }

    public Expense() {

    }

    public String toCsvString() {
        StringBuilder result = new StringBuilder();

        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String format = "%s,";
            if (Double.class.getSimpleName().equals(field.getType().getSimpleName())) {
                format = "%.2f";
            }
            try {
                result.append(String.format(format, field.get(this)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        result.append("\n");
        return result.toString();
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }
}
