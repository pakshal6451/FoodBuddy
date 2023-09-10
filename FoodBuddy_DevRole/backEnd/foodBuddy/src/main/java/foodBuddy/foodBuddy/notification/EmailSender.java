package foodBuddy.foodBuddy.notification;

public interface EmailSender {
    String send (String[] toEmails, String itemName);
}
