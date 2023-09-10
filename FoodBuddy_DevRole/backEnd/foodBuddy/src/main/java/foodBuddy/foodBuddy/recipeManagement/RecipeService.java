package foodBuddy.foodBuddy.recipeManagement;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import foodBuddy.foodBuddy.constants.AppConstants;
import foodBuddy.foodBuddy.inventory.InventoryRepository;
import foodBuddy.foodBuddy.recipeManagement.Model.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeService {
    @Autowired
    private final InventoryRepository inventoryRepository;
    public RecipeResponse viewRecipe(String groupCode) {
        RecipeResponse response = new RecipeResponse();
        try {
            response.setRecipeList(callExternalRecipeApiForRecipeList(groupCode));
            response.setMessage("Found Recipes");
            response.setStatus("success");
            return response;
        } catch (Exception e){
            response.setRecipeList(null);
            response.setStatus("failure");
            response.setMessage(e.getMessage());
            return response;
        }
    }

    private String fetchIngredientsAndPrepareUrl(String groupCode) throws UnsupportedEncodingException {
        List<String> itemList = inventoryRepository.findItemNameList(groupCode);
        String items = buildItemString(itemList);
        String encodedItems = encodeString(items);
        String url = buildUrl(encodedItems);
        System.out.println(url);
        return url;
    }

    private String buildItemString(List<String> itemList) {
        return String.join(",+", itemList);
    }

    private String encodeString(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8");
    }

    private String buildUrl(String encodedItems) {
        return AppConstants.FIND_BY_INGREDIENTS_URL.getValue().toString() +
                AppConstants.INGREDIENT_LITERAL.getValue().toString() + encodedItems +
                AppConstants.AMPERSAND.getValue().toString() + AppConstants.API_KEY_LITERAL.getValue().toString() +
                AppConstants.API_KEY.getValue().toString() + AppConstants.AMPERSAND.getValue().toString() +
                AppConstants.NUMBER_LITERAL.getValue().toString() + AppConstants.NUMBER.getValue().toString();
    }


    private List<Recipe> callExternalRecipeApiForRecipeList(String groupCode) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = fetchIngredientsAndPrepareUrl(groupCode);
        ResponseEntity<Recipe[]> response = restTemplate.getForEntity(apiUrl, Recipe[].class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to call external API. Status code: " + response.getStatusCode());
        }

        List<Recipe> recipes = Arrays.asList(response.getBody());
        List<Recipe> sortedRecipes = getSortedRecipes(recipes);
        setRecipeLinks(sortedRecipes);
        return sortedRecipes;
    }

    private List<Recipe> getSortedRecipes(List<Recipe> recipes) {
        return recipes.stream()
                .sorted(Comparator.comparingInt(Recipe::getMissedIngredientCount))
                .limit(Integer.parseInt(AppConstants.RECIPE_COUNT.getValue().toString()))
                .collect(Collectors.toList());
    }

    private void setRecipeLinks(List<Recipe> recipes) throws Exception {
        for (Recipe recipe : recipes) {
            String recipeUrl = getRecipeUrlById(recipe.getId());
            recipe.setLink(recipeUrl);
        }
    }


    private String getRecipeUrlById(int id) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = AppConstants.RECIPE_URL.getValue().toString() + id + "/information?apiKey=" + AppConstants.API_KEY.getValue().toString();
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();
            String recipeUrl = jsonObject.get("sourceUrl").getAsString();
            return recipeUrl;
        } else {
            throw new RuntimeException("Failed to call external API. Status code: " + response.getStatusCode());
        }
    }

}
