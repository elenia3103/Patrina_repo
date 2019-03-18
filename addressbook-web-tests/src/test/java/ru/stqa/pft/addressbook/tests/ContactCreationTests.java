package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;


public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().contactPage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Alexey").withLastname("Host").withNickname("nex").withCompany("Net")
            .withHome("NYC").withMobile("9877645").withAddress("78765788").withGroup("testov");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()+1);

    contact.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}

