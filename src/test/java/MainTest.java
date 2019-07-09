import core.Expense;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Vivek
 * @version 1.0
 * @since 09-07-2019
 */
public class MainTest {

    @Test
    public void testPruneExtraExpenses() {
        List<Expense> expenseList = new ArrayList<>();
        Expense e1 = new Expense();
        e1.setFare(123.45);
        expenseList.add(e1);
        e1 = new Expense();
        e1.setFare(Double.NaN);
        expenseList.add(e1);
        e1 = new Expense();
        e1.setFare(1220.49);
        expenseList.add(e1);

        Main.pruneExtraExpenses(expenseList);
        Assert.assertEquals(2, expenseList.size());
    }
}