package IntegrationTesting;

import foodBuddy.foodBuddy.expense.ExpenseRepository;
import foodBuddy.foodBuddy.groupManagement.GroupRepository;
import foodBuddy.foodBuddy.inventory.InventoryController;
import foodBuddy.foodBuddy.inventory.InventoryRepository;
import foodBuddy.foodBuddy.inventory.InventoryService;
import foodBuddy.foodBuddy.inventory.invenotryResponses.AddItemResponse;
import foodBuddy.foodBuddy.inventory.invenotryResponses.DeleteItemResponse;
import foodBuddy.foodBuddy.inventory.invenotryResponses.ViewItemsResponse;
import foodBuddy.foodBuddy.inventory.inventoryRequests.AddItemRequest;
import foodBuddy.foodBuddy.inventory.inventoryRequests.DeleteItemRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {foodBuddy.foodBuddy.FoodBuddyApplication.class})
@ComponentScan(basePackages = {"src/main/java/foodBuddy.foodBuddy.appuser"})
public class InventoryApiIntegrationTest {

    @Autowired
    InventoryController inventoryController;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ExpenseRepository expenseRepository;

    @Test
    public void addInventoryTest(){
        String itemName="Mango Icecream";
        int quantity=2;
        String expDate="2023-04-09";
        String groupCode="394290";
        Double amount=200.9;
        String emailId="sujahidms@gmail.com";
        AddItemRequest request = new AddItemRequest(itemName,quantity,expDate,groupCode,amount,emailId);
        AddItemResponse response = inventoryController.addItem(request);
        if(response.getMessage()!=null)
            assertEquals("Item successfully",response.getMessage());
        else
            assertEquals(null ,response.getMessage());
    }

    @Test
    public void viewItemApiTestSuccess(){
        String groupCode="394290";
        ViewItemsResponse response = inventoryController.viewItem(groupCode);
        assertEquals("success",response.getStatus());
    }

    @Test
    public void viewItemApiTestFailure(){
        String groupCode="";
        ViewItemsResponse response = inventoryController.viewItem(groupCode);
        assertEquals("failure",response.getStatus());
    }

    @Test
    public void deleteItemApiTestFailure(){
        String itemName="Mango Icecream";
        String groupCode="394290";
        Double amount=200.9;
        String emailId="sujahidms@gmail.com";
        DeleteItemRequest request = new DeleteItemRequest(itemName,groupCode,amount,emailId);
        DeleteItemResponse response = inventoryController.deleteItem(request);
        System.out.println("Response : "+response);
        assertEquals("success",response.getStatus());
    }

}

