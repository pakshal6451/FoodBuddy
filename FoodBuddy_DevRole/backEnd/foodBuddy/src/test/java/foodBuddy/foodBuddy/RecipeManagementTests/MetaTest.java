package foodBuddy.foodBuddy.RecipeManagementTests;

import foodBuddy.foodBuddy.recipeManagement.Model.Meta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MetaTest {

    @Test
    public void testConstructorAndGetters() {
        String expectedValue = "meta value";
        Meta meta = new Meta(expectedValue);
        Assertions.assertEquals(expectedValue, meta.getValue());
    }

    @Test
    public void testEqualsAndHashCode() {
        Meta meta1 = new Meta("value1");
        Meta meta2 = new Meta("value1");
        Meta meta3 = new Meta("value2");
        Assertions.assertEquals(meta1, meta2);
        Assertions.assertNotEquals(meta1, meta3);
        Assertions.assertEquals(meta1.hashCode(), meta2.hashCode());
        Assertions.assertNotEquals(meta1.hashCode(), meta3.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Meta(value=meta value)";
        Meta meta = new Meta("meta value");
        Assertions.assertEquals(expectedString, meta.toString());
    }
}
