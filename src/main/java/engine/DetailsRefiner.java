package engine;

import core.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.Constants.*;

/**
 * @author Vivek
 * @version 1.0
 * @since 08-07-2019
 */
public class DetailsRefiner {

    private static Logger logger = LoggerFactory.getLogger(DetailsRefiner.class);

    public static Expense refineDetailsFromTheExtract(List<String> lines) {
        final AtomicInteger lineNumber = new AtomicInteger(1);
        long pnr = DEFAULT_PNR;
        String trainName = DEFAULT_TRAIN_NAME;
        int trainNumber = DEFAULT_TRAIN_NUMBER;
        String dateOfJourney = DEFAULT_DATE_OF_JOURNEY;
        String from = DEFAULT_SRC_STATION;
        String to = DEFAULT_DESTINATION_STATION;
        String boardingAt = DEFAULT_BOARDING_STATION;
        double ticketFare = DEFAULT_FARE;

        for (String line : lines) {
            logger.debug("{}. {}", lineNumber.getAndIncrement(), line);
            if (line.contains(PNR_NO)) {
                pnr = Long.valueOf(enrichRawString(line.substring(line.indexOf(PNR_NO), line.indexOf(TRAIN_NUM_NAME))));
                final String[] trainNumName = enrichRawString(line.substring(line.indexOf(TRAIN_NUM_NAME), line.indexOf(QUOTA))).split("/");
                trainNumber = Integer.valueOf(enrichRawString(trainNumName[0]));
                trainName = enrichRawString(trainNumName[1]);

            } else if (line.contains(DOJ)) { //DOJ, FROM, TO
                from = enrichRawString(line.substring(line.indexOf(FROM), line.indexOf(DOJ)).trim());
                dateOfJourney = enrichRawString(line.substring(line.indexOf(DOJ), line.indexOf(TO)));
                to = enrichRawString(line.substring(line.indexOf(TO)));

            } else if (line.contains(BOARDING_AT)) {
                try {
                    boardingAt = enrichRawString(line.substring(line.indexOf(BOARDING_AT), line.indexOf(DOB)));
                } catch (StringIndexOutOfBoundsException e) {
                    boardingAt = enrichRawString(line.substring(line.indexOf(BOARDING_AT)));
                }

            } else if (line.contains(TICKET_FARE)) {
                //line = line.replace("₹", ":");
                String ticketFareString = line.substring(line.indexOf(TICKET_FARE), line.indexOf(RUPEES)).trim();
                ticketFareString = ticketFareString.substring(ticketFareString.lastIndexOf(' ')).trim();
                ticketFare = Double.valueOf(ticketFareString);
                break; //avoiding unnecessary further processing
            }
        }
        return new Expense(dateOfJourney, pnr, trainNumber, trainName, from, boardingAt, to, ticketFare);
    }

    static String enrichRawString(String data) {
        if (data.contains(":")) return data.substring(data.indexOf(':') + 1).trim();
        return data.trim();
    }
}
