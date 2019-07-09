package util;

import org.junit.Assert;
import org.junit.Test;

import static util.Constants.DEFAULT_COMPUTABLE_DOJ;

/**
 * @author Vivek
 * @version 1.0
 * @since 09-07-2019
 */
public class HelperTest {

    @Test
    public void testConvertDisplayDateToComputableDate() {
        Assert.assertEquals(20181016L, (long) Helper.convertDisplayDateToComputableDate("16-Oct-2018"));

        Assert.assertEquals(DEFAULT_COMPUTABLE_DOJ, (long) Helper.convertDisplayDateToComputableDate("16-Oc-2018"));
    }

    @Test
    public void testGenerateCsvExportName() {
        final String result = Helper.generateCsvExportName();
        System.out.println(result);
        Assert.assertTrue(result.endsWith(".csv"));
    }

    @Test
    public void testGetColumnHeaderForExpensesCsv() {
        Assert.assertEquals("displayDate,computableDate,sourceStation,boardingStation,destinationStation,fare\n", Helper.getColumnHeaderForExpensesCsv());
    }
}