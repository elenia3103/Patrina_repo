package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePreonditions() {
        app.goTo().contactPage();
        if (app.db().contacts().size()==0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Alexey").withLastname("Host").withNickname("nex").withCompany("Net")
                    .withHome("9999").withMobile("9877645").withAddress("Yrupinsk"));
        }
    }
    @Test
    public void testContactPhone() {
        app.goTo().contactPage();
        ContactData contact  = app.db().contacts().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoEditForm(contact);
        
//        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        verifyContactListInUI();
    }

    @Test
    public void testContactEmail() {
        app.goTo().contactPage();
        ContactData contact  = app.db().contacts().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoEditForm(contact);

//        assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));
        verifyContactListInUI();
    }

    @Test
    public void testContactAddress() {
        app.goTo().contactPage();
        ContactData contact  = app.db().contacts().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        verifyContactListInUI();
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(),contact.getEmail3())
                .stream().filter((s) -> !s.equals("")).
                        map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(),contact.getWork())
                .stream().filter((s) -> !s.equals("")).
                        map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned (String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}
