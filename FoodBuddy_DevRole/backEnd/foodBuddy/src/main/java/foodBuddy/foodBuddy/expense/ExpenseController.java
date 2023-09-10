package foodBuddy.foodBuddy.expense;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/expenses")
public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/total")
    public Map<String, Object> getTotalExpenses(@RequestBody ExpenseRequest expenseRequest) {
        double totalExpenses = expenseService.getTotalUserExpenses(expenseRequest.getEmailId());
        Map<String, Object> response = new HashMap<>();
        response.put("totalExpenses", totalExpenses);
        response.put("status", "success");
        response.put("message", "success");
        return response;
    }
    @PostMapping("/groupExpenses")
    public GroupExpenseResponse getGroupExpenses(@RequestBody GroupExpenseRequest request){
        GroupExpenseResponse response= expenseService.getGroupExpenses(request);
        return response;

    }



}
