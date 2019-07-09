package core;

import util.Helper;

import static util.Constants.*;

/**
 * @author Vivek
 * @version 1.0
 * @since 08-07-2019
 */
public class Expense {

    private String displayDate = DEFAULT_DATE_OF_JOURNEY; //dd-MM-yyyy
    private Long computableDate = DEFAULT_COMPUTABLE_DOJ; //yyyyMMdd
    private String sourceStation = DEFAULT_SRC_STATION;
    private String boardingStation = DEFAULT_BOARDING_STATION;
    private String destinationStation = DEFAULT_DESTINATION_STATION;
    private Double fare = DEFAULT_FARE;

    @Override
    public String toString() {
        return String.format("Expense ~ %s => '%s' To '%s', FROM '%s' :: Rs. %.2f",
                displayDate,
                sourceStation,
                destinationStation,
                boardingStation,
                fare);
    }

    public String toCsvString() {
        return String.format("%s,%d,%s,%s,%s,%.2f\n",
                displayDate,
                computableDate,
                sourceStation,
                boardingStation,
                destinationStation,
                fare);
    }

    public Expense() {

    }

    public Expense(String displayDate, String sourceStation, String boardingStation, String destinationStation, Double fare) {
        this.displayDate = displayDate;
        this.computableDate = Helper.convertDisplayDateToComputableDate(displayDate);
        this.sourceStation = sourceStation;
        this.boardingStation = boardingStation;
        this.destinationStation = destinationStation;
        this.fare = fare;
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
