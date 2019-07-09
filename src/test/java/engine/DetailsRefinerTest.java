package engine;

import core.Expense;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Vivek
 * @version 1.0
 * @since 09-07-2019
 */
public class DetailsRefinerTest {

    private final Logger logger = LoggerFactory.getLogger(DetailsRefinerTest.class);

    @Test
    public void testRefineDetailsFromTheExtract() {
        List<String> result = DetailsExtractor.convertPdfToStringList("<source_of_irctc_tickets_goes_here>");
        Assert.assertEquals(137, result.size());

        logger.info("Entering refinery...");
        Expense expense = DetailsRefiner.refineDetailsFromTheExtract(result);
        logger.info(expense.toString());
        Assert.assertTrue(expense.getDisplayDate().contains("09-Jul-2018"));
    }

    @Test
    public void testEnrichRawString() {
        Assert.assertEquals("val", DetailsRefiner.enrichRawString("key:val"));
        Assert.assertEquals("val", DetailsRefiner.enrichRawString("key: val"));
        Assert.assertEquals("val", DetailsRefiner.enrichRawString("key: val   "));

        Assert.assertEquals("key+ val", DetailsRefiner.enrichRawString("key+ val"));
        Assert.assertEquals("key+ val", DetailsRefiner.enrichRawString("key+ val     "));
    }
}