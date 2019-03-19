package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreonditions() {
        app.goTo().contactPage();
        if (app.contact().all().size()==0) {
            app.contact().create(new ContactData().withFirstname("Alexey").withLastname("Host").withNickname("nex").withCompany("Net")
                    .withHome("NYC").withMobile("9877645").withAddress("78765788").withGroup("testov"));
        }
    }
    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Misha").withLastname("Next").withNickname("sony").withCompany("Net")
                .withHome("NYC").withMobile("9877645").withAddress("78765788").withGroup("testov");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
