package foodBuddy.foodBuddy.ExpenseManagementTests;

import foodBuddy.foodBuddy.constants.AppConstants;
import foodBuddy.foodBuddy.expense.userExpenses;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserExpensesTest {

    @Test
    void getEmailIdTest(){
        userExpenses usp = new userExpenses("user@email.com",(Double.parseDouble(AppConstants.DEFAULT_EXPENSE.getValue().toString())));
        String result = usp.getEmailId();
        assertEquals("user@email.com",result);
    }

    @Test
    void getAmountTest(){
        userExpenses usp = new userExpenses("user@email.com",(Double.parseDouble(AppConstants.DEFAULT_EXPENSE.getValue().toString())));
        Double result = usp.getAmount();
        assertEquals((Double.parseDouble(AppConstants.DEFAULT_EXPENSE.getValue().toString())),result);
    }
}
