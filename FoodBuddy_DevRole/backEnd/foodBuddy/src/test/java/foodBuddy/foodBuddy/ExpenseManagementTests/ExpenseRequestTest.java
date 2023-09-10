package foodBuddy.foodBuddy.ExpenseManagementTests;

import foodBuddy.foodBuddy.expense.ExpenseRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseRequestTest {
    @Test
    void getEmailIdTest(){
        String emailId = "user@email.com";
        ExpenseRequest request = new ExpenseRequest();
        request.setEmailId(emailId);
        assertEquals(emailId,request.getEmailId());
    }
}
