package foodBuddy.foodBuddy.NotificationTests;

import foodBuddy.foodBuddy.notification.NotificationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationRequestTest {

    @Test
    public void testConstructorAndGetters() {
        NotificationRequest request = new NotificationRequest("group1", "item1");
        assertEquals("group1", request.getGroupCode());
        assertEquals("item1", request.getItemName());
    }

    @Test
    public void testEqualsAndHashCode() {
        NotificationRequest request1 = new NotificationRequest("group1", "item1");
        NotificationRequest request2 = new NotificationRequest("group1", "item1");
        NotificationRequest request3 = new NotificationRequest("group2", "item1");

        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());
        assertEquals(request1.toString(), request2.toString());

        assertEquals(request1.equals(request3), false);
        assertEquals(request1.hashCode() == request3.hashCode(), false);
        assertEquals(request1.toString().equals(request3.toString()), false);
    }
}
