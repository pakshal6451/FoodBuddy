package foodBuddy.foodBuddy.recipeManagement.Model;

import foodBuddy.foodBuddy.recipeManagement.Model.Ingredient;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private int id;
    private String title;
    private String image;
    private String imageType;
    private int usedIngredientCount;
    private int missedIngredientCount;
    private List<Ingredient> missedIngredients;
    private List<Ingredient> usedIngredients;
    private List<Ingredient> unusedIngredients;
    private int likes;
    private String link;
}
