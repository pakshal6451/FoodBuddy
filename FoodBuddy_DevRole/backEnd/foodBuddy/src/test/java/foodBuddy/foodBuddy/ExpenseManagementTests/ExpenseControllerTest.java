package foodBuddy.foodBuddy.ExpenseManagementTests;

import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.expense.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class ExpenseControllerTest {

    private ExpenseService expenseService;
    private ExpenseController expenseController;
    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        expenseService = new ExpenseService(expenseRepository,userRepository);
        expenseController = new ExpenseController(expenseService);
    }

    @Test
    void getTotalExpensesTest(){
        Map<String, Object> response = new HashMap<>();
        ExpenseRequest request = new ExpenseRequest();
        response.put("status", "success");
//        when(expenseService.getTotalUserExpenses("user@email.com")).thenReturn(response);
        Map<String, Object> expenseResponse = expenseController.getTotalExpenses(request);

    }
}
