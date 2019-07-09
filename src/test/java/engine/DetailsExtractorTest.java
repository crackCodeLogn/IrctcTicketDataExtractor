package engine;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Vivek
 * @version 1.0
 * @since 08-07-2019
 */
public class DetailsExtractorTest {

    private final Logger logger = LoggerFactory.getLogger(DetailsExtractor.class);

    @Test
    public void testConvertPdfToStringList() {
        List<String> result = DetailsExtractor.convertPdfToStringList("<source_of_irctc_tickets_goes_here>");
        result.forEach(logger::info);
        logger.info("result size : {}", result.size());
    }
}