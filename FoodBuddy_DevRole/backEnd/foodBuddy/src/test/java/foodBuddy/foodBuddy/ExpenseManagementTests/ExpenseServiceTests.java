package foodBuddy.foodBuddy.ExpenseManagementTests;

import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.constants.AppConstants;
import foodBuddy.foodBuddy.expense.*;
import foodBuddy.foodBuddy.groupManagement.ViewGroupUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ExpenseServiceTests {

    private ExpenseService expenseService;

    @Mock
    private ExpenseRepository expenseRepository;
    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        expenseService = new ExpenseService(expenseRepository,userRepository);
    }

    @Test
    void getTotalUserExpensesTestSuccess(){
        String emailId="user@email.com";
        when(expenseRepository.getPastUserExpenses(emailId)).thenReturn((Double.parseDouble(AppConstants.DEFAULT_PAST_EXPENSES.getValue().toString())));
        Double response= expenseService.getTotalUserExpenses(emailId);
        assertEquals(33.33,response);
    }

    @Test
    void getGroupExpensesSuccess(){
        List<ViewGroupUsers> listOfUsers = new ArrayList<ViewGroupUsers>();
        ViewGroupUsers user1 = new ViewGroupUsers("user1@email.com","user1Fname","user1LName");
        ViewGroupUsers user2 = new ViewGroupUsers("user2@email.com","user2Fname","user2LName");
        ViewGroupUsers user3 = new ViewGroupUsers("user3@email.com","user3Fname","user3LName");
        listOfUsers.add(user1);
        listOfUsers.add(user2);
        listOfUsers.add(user3);
        String groupCode = "1234";
        when(userRepository.findUsersByGroupCode(groupCode)).thenReturn(listOfUsers);
        when(expenseRepository.getPastUserExpenses("user1@email.com")).thenReturn((Double.parseDouble(AppConstants.DEFAULT_PAST_EXPENSES1.getValue().toString())));
        when(expenseRepository.getPastUserExpenses("user2@email.com")).thenReturn((Double.parseDouble(AppConstants.DEFAULT_PAST_EXPENSES2.getValue().toString())));
        when(expenseRepository.getPastUserExpenses("user3@email.com")).thenReturn((Double.parseDouble(AppConstants.DEFAULT_PAST_EXPENSES3.getValue().toString())));

        GroupExpenseRequest request = new GroupExpenseRequest();
        request.setUserGroupNumber(groupCode);
        GroupExpenseResponse response = expenseService.getGroupExpenses(request);
        assertEquals("success",response.getMessage());
        assertEquals("success",response.getStatus());
    }

}
