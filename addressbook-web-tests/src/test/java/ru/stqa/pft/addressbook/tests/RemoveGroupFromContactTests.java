package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveGroupFromContactTests extends TestBase {
    @BeforeMethod
    public void ensurePreonditions() {
        if (app.db().contacts().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Alexey").withLastname("Host").withNickname("nex").withCompany("Net")
                    .withHome("7777").withMobile("9877645").withAddress("Yrupinsk"));
        }
        if(app.db().groups().size()==0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testovyi").withHeader("test"));
        }
        if (app.db().contacts().iterator().next().getGroups().size()==0) {
            Groups groups = app.db().groups();
            Contacts contactsBefore = app.db().contacts();
            ContactData selectedContact = contactsBefore.iterator().next();
            GroupData selectedGroup = groups.iterator().next();
            app.contact().addToGroup(selectedContact,selectedGroup);
            app.contact().contactPage();
        }
    }

    @Test
    public void testRemoveGroupFromContact() {
        app.goTo().contactPage();
        Groups groupsBefore = app.db().contacts().iterator().next().getGroups();
        Contacts contactsBefore = app.db().contacts();
        ContactData selectedContact = contactsBefore.iterator().next();
        GroupData selectedGroup = groupsBefore.iterator().next();
        app.contact().removeGroup(selectedContact,selectedGroup);
        app.contact().contactPage();
        Contacts contactsAfter = app.db().contacts();
        assertThat(contactsBefore.iterator().next().getGroups(),
                equalTo(contactsAfter.iterator().next().getGroups().withAdded(selectedGroup)));
        verifyContactListInUI();
    }
}
