package foodBuddy.foodBuddy.inventory;

import foodBuddy.foodBuddy.expense.ExpenseRepository;
import foodBuddy.foodBuddy.expense.userExpenses;
import foodBuddy.foodBuddy.groupManagement.GroupRepository;
import foodBuddy.foodBuddy.inventory.invenotryResponses.AddItemResponse;
import foodBuddy.foodBuddy.inventory.invenotryResponses.DeleteItemResponse;
import foodBuddy.foodBuddy.inventory.invenotryResponses.ViewItemsResponse;
import foodBuddy.foodBuddy.inventory.inventoryRequests.AddItemRequest;
import foodBuddy.foodBuddy.inventory.inventoryRequests.DeleteItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class InventoryService {
    @Autowired
    private final InventoryRepository inventoryRepository;

    @Autowired
    private final GroupRepository groupRepository;

    @Autowired
    private final ExpenseRepository expenseRepository;

    public AddItemResponse addItem(AddItemRequest request) {
        AddItemResponse response = new AddItemResponse();
        InventoryEntity inventory = createInventory(request);
        boolean itemExists = checkInventoryExists(inventory);
        boolean userExpensesExists = checkUserExpensesExists(request);
        if (!itemExists) {
            if (userExpensesExists) {
                updateExistingUserExpense(request);
                updateInventoryDetails(request, inventory);
            } else {
                saveNewUserExpense(request);
            }
            saveNewInventory(inventory);
            response.setMessage("Item successfully");
            response.setStatus("success");
        } else {
            if (userExpensesExists) {
                updateExistingUserExpense(request);
                updateInventoryDetails(request, inventory);
            } else {
                response.setMessage("Item Exists");
                response.setStatus("failure");
            }
        }
        return response;
    }

    private InventoryEntity createInventory(AddItemRequest request) {
        return new InventoryEntity(
                request.getItemName(),
                request.getExpDate(),
                request.getQuantity(),
                request.getGroupCode(),
                request.getAmount()
        );
    }

    private boolean checkInventoryExists(InventoryEntity inventory) {
        return inventoryRepository
                .findInventoryEntitiesByItemName(inventory.getItemName(), inventory.getGroupCode())
                .isPresent();
    }

    private boolean checkUserExpensesExists(AddItemRequest request) {
        return expenseRepository.findUserExpenseExists(request.getEmailId()).isPresent();
    }

    private void updateExistingUserExpense(AddItemRequest request) {
        Double previousAmount = expenseRepository.getPastUserExpenses(request.getEmailId());
        expenseRepository.updateUserExpense(request.getAmount() + previousAmount, request.getEmailId());
    }

    private void updateInventoryDetails(AddItemRequest request, InventoryEntity inventory) {
        inventoryRepository.updateItemDetails(
                request.getItemName(),
                request.getGroupCode(),
                request.getExpDate(),
                request.getQuantity(),
                request.getAmount()
        );
    }

    private void saveNewUserExpense(AddItemRequest request) {
        expenseRepository.save(new userExpenses(request.getEmailId(), request.getAmount()));
    }

    private void saveNewInventory(InventoryEntity inventory) {
        inventoryRepository.save(inventory);
    }


//    public UpdateItemResponse updateItem(UpdateItemRequest request) {
//        System.out.println(request);
//        UpdateItemResponse response = new UpdateItemResponse();
//        boolean itemExists = inventoryRepository.findInventoryEntitiesByItemName(request.getItemName(),request.getGroupCode()).isPresent();
//        if (itemExists){
//            inventoryRepository.updateItemDetails(request.getItemName(),request.getGroupCode(), request.getExpDate(), request.getQuantity(),request.g);
//            response.setMessage("Item Updated successfully");
//            response.setStatus("success");
//            return response;
//        }
//        else {
//            response.setMessage("Item Does not Exists");
//            response.setStatus("failure");
//            return response;
//        }
//    }

    public ViewItemsResponse viewItems(String groupCode) {
        ViewItemsResponse response = new ViewItemsResponse();
        try {
            boolean groupExists = groupRepository.findGroupByCode(groupCode).isBlank();
            if (!groupExists){
                response.setItemList(inventoryRepository.findItemList(groupCode));
                response.setMessage("Found Group Items");
                response.setStatus("success");
                return response;
            }
            else {
                response.setItemList(null);
                response.setMessage("Invalid GroupCode");
                response.setStatus("failure");
                return response;
            }
        }
        catch (NullPointerException e){
            response.setItemList(null);
            response.setMessage("please verify the groupCode");
            response.setStatus("failure");
            return response;
        }

    }
    public DeleteItemResponse deleteItem(DeleteItemRequest request) {
        DeleteItemResponse response = new DeleteItemResponse();
        boolean itemExists = inventoryRepository.findInventoryEntitiesByItemName(request.getItemName(), request.getGroupCode()).isPresent();
        if (itemExists) {
            updateExpense(request.getEmailId(), request.getAmount());
            deleteItemFromInventory(request.getItemName(), request.getGroupCode());
            response.setMessage("Item Updated successfully");
            response.setStatus("success");
        } else {
            response.setMessage("Item Does not Exists");
            response.setStatus("failure");
        }
        return response;
    }

    private void updateExpense(String emailId, Double amount) {
        if (amount != null) {
//            Double previousAmount = expenseRepository.getPastUserExpenses(emailId);
            expenseRepository.updateUserExpense(Math.abs(amount), emailId);
        }
    }

    private void deleteItemFromInventory(String itemName, String groupCode) {
        inventoryRepository.deleteItemfromDB(itemName, groupCode);
    }

}
