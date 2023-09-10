package foodBuddy.foodBuddy.recipeManagement;

import foodBuddy.foodBuddy.recipeManagement.Model.Recipe;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse {

    private List<Recipe> recipeList;
    private String status;
    private String message;
}



