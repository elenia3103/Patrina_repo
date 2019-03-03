package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Alex", "Host", "nex",
            "Net", "NYC", "9877645", "78765788","testov"),true);
    app.getContactHelper().submitToContactPage();
    app.getNavigationHelper().returnToContactPage();

  }

}

