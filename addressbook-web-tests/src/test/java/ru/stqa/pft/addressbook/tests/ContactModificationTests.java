package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToContactPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().fillContactForm(new ContactData("Alex", "Host", "nex",
                    "Net", "NYC", "9877645", "78765788","testov"), true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("me", "Host", "nex", "Net",
                "NYC", "9877645", "78765788", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToContactPage();
    }
}
