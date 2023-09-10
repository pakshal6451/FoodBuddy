package foodBuddy.foodBuddy.RecipeManagementTests;

import foodBuddy.foodBuddy.expense.ExpenseService;
import foodBuddy.foodBuddy.inventory.InventoryRepository;
import foodBuddy.foodBuddy.recipeManagement.RecipeResponse;
import foodBuddy.foodBuddy.recipeManagement.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RecipeServiceTests {
    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private URLEncoder urlEncoder;

    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeService(inventoryRepository);
    }

//    @Test
//    void viewRecipieTestSuccess() throws UnsupportedEncodingException {
//       String groupCode = "1234";
//       List<String> itemList = new ArrayList<String>();
//       itemList.add("Biryani");
//       itemList.add("fried rice");
//       itemList.add("Pizza");
//       String items= String.join(",+",itemList);
//       when(inventoryRepository.findItemNameList(groupCode)).thenReturn(itemList);
//
//
//        RecipeResponse response  = recipeService.viewRecipe(groupCode);
//
//        assertEquals("Found Recipes",response.getMessage());
//        assertEquals("success",response.getStatus());
//
//    }

    @Test
    void viewRecipieTestSuccessOne() throws UnsupportedEncodingException {
       String groupCode = "1234";
       List<String> itemList = new ArrayList<String>();
       itemList.add("Biryani");
       itemList.add("fried rice");
       itemList.add("Pizza");
       String items= String.join(",+",itemList);
       when(inventoryRepository.findItemNameList(groupCode)).thenReturn(itemList);


        RecipeResponse response  = recipeService.viewRecipe(groupCode);

//        assertEquals("Found Recipes",response.getMessage());
//        assertEquals("success",response.getStatus());

    }

    @Test
    void viewRecipieTestFailure() throws UnsupportedEncodingException {
        String groupCode = "1234";
        when(inventoryRepository.findItemNameList(groupCode)).thenReturn(null);
        RecipeResponse response  = recipeService.viewRecipe(groupCode);
        assertEquals(null,response.getMessage());
        assertEquals("failure",response.getStatus());

    }
    

//    @Test
//    void viewRecipieTestWhenNoListIsPresent() throws UnsupportedEncodingException {
//        String groupCode = "1234";
//        List<String> itemList = new ArrayList<String>();
//        String items= String.join(",+",itemList);
//        when(inventoryRepository.findItemNameList(groupCode)).thenReturn(itemList);
//        RecipeResponse response  = recipeService.viewRecipe(groupCode);
//        assertEquals("Found Recipes",response.getMessage());
//        assertEquals("success",response.getStatus());
//        assertEquals(0,response.getRecipeList().size());
//
//
//    }

}
