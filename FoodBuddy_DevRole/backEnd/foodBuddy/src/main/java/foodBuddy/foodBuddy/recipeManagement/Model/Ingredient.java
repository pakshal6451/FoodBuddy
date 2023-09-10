package foodBuddy.foodBuddy.recipeManagement.Model;

import lombok.*;

import java.util.List;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private int id;
    private double amount;
    private String unit;
    private String unitLong;
    private String unitShort;
    private String aisle;
    private String name;
    private String original;
    private String originalName;
    private List<Meta> meta;
    private String image;

    public Ingredient(String pepper, String s) {

    }
}
