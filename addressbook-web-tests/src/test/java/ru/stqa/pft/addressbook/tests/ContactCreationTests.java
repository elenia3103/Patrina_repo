package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().contactPage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("Alexey").withLastname("Host").withNickname("nex").withCompany("Net")
            .withHome("NYC").withMobile("9877645").withAddress("78765788").withGroup("testov");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size()+1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}

