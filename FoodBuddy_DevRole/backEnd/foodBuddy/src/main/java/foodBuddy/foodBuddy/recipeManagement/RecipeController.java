package foodBuddy.foodBuddy.recipeManagement;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/recipe")
@CrossOrigin
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/view")
    public RecipeResponse viewRecipe(@RequestParam(value = "groupCode") String groupCode){
        RecipeResponse response = recipeService.viewRecipe(groupCode);
        return response;
    }
}
