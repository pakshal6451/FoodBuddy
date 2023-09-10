package foodBuddy.foodBuddy.RecipeManagementTests;

import foodBuddy.foodBuddy.constants.AppConstants;
import foodBuddy.foodBuddy.recipeManagement.Model.Ingredient;
import foodBuddy.foodBuddy.recipeManagement.Model.Meta;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void testEqualsAndHashCode() {
        Ingredient ingredient1 = new Ingredient(1, (Double.parseDouble(AppConstants.INGREDIENT_AMOUNT.getValue().toString())), "grams", "grams", "g", "Produce", "carrots", "2 cups chopped carrots", "carrots", new ArrayList<>(), "https://www.edamam.com/food-img/650/650b5cb5b5b5c8f91a120e965463e1ce.jpg");
        Ingredient ingredient2 = new Ingredient(1, (Double.parseDouble(AppConstants.INGREDIENT_AMOUNT.getValue().toString())), "grams", "grams", "g", "Produce", "carrots", "2 cups chopped carrots", "carrots", new ArrayList<>(), "https://www.edamam.com/food-img/650/650b5cb5b5b5c8f91a120e965463e1ce.jpg");
        assertEquals(ingredient1, ingredient2);
        assertEquals(ingredient1.hashCode(), ingredient2.hashCode());
    }

    @Test
    void testToString() {
        Ingredient ingredient = new Ingredient(1, (Double.parseDouble(AppConstants.INGREDIENT_AMOUNT.getValue().toString())), "grams", "grams", "g", "Produce", "carrots", "2 cups chopped carrots", "carrots", new ArrayList<>(), "https://www.edamam.com/food-img/650/650b5cb5b5b5c8f91a120e965463e1ce.jpg");
        String expected = "Ingredient(id=1, amount=2.0, unit=grams, unitLong=grams, unitShort=g, aisle=Produce, name=carrots, original=2 cups chopped carrots, originalName=carrots, meta=[], image=https://www.edamam.com/food-img/650/650b5cb5b5b5c8f91a120e965463e1ce.jpg)";
        assertEquals(expected, ingredient.toString());
    }

    @Test
    void testAllArgsConstructor() {
        List<Meta> meta = new ArrayList<>();
        meta.add(new Meta("vegan"));
        meta.add(new Meta("gluten-free"));
        Ingredient ingredient = new Ingredient(1, (Double.parseDouble(AppConstants.INGREDIENT_AMOUNT.getValue().toString())), "grams", "grams", "g", "Produce", "carrots", "2 cups chopped carrots", "carrots", meta, "https://www.edamam.com/food-img/650/650b5cb5b5b5c8f91a120e965463e1ce.jpg");
        assertEquals(1, ingredient.getId());
        assertEquals((Double.parseDouble(AppConstants.INGREDIENT_AMOUNT.getValue().toString())), ingredient.getAmount());
        assertEquals("grams", ingredient.getUnit());
        assertEquals("grams", ingredient.getUnitLong());
        assertEquals("g", ingredient.getUnitShort());
        assertEquals("Produce", ingredient.getAisle());
        assertEquals("carrots", ingredient.getName());
        assertEquals("2 cups chopped carrots", ingredient.getOriginal());
        assertEquals("carrots", ingredient.getOriginalName());
        assertEquals(meta, ingredient.getMeta());
        assertEquals("https://www.edamam.com/food-img/650/650b5cb5b5b5c8f91a120e965463e1ce.jpg", ingredient.getImage());
    }

    @Test
    void testNoArgsConstructor() {
        Ingredient ingredient = new Ingredient();
        assertEquals(0, ingredient.getId());
        assertEquals(0.0, ingredient.getAmount());
        assertNull(ingredient.getUnit());
        assertNull(ingredient.getUnitLong());
        assertNull(ingredient.getUnitShort());
    }

}

