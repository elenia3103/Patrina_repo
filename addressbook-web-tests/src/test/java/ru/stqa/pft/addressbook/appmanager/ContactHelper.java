package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNickname());
        type(By.name("company"),contactData.getCompany());
        type(By.name("address"),contactData.getAddress());
        type(By.name("home"),contactData.getHome());
        type(By.name("mobile"),contactData.getMobile());
        type(By.name("work"),contactData.getWork());
        type(By.name("email"),contactData.getEmail());
        type(By.name("email2"),contactData.getEmail2());
        type(By.name("email3"),contactData.getEmail3());


    }
    public void submitToContactPage () {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void initContactModificationById (int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id="+ id +"']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptDeleteContact() {
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitToContactPage();
        contactPage();

    }
    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        returnToContactPage();
    }
    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptDeleteContact();
    }

    private void returnToContactPage() {
            click(By.linkText("home page"));
    }
    public void contactPage() {
        click(By.linkText("home"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));

    }

    public int count() {
        return wd.findElements(By.name(("selected[]"))).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache !=null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            List<WebElement> box = element.findElements(By.tagName("td"));
            String lastname = box.get(1).getText();
            String firstname = box.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String allphones = box.get(5).getText();
            String allemail = box.get(4).getText();
            String address = box.get(3).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname)
                    .withLastname(lastname).withAllPhones(allphones).withAllEmail(allemail).withAddress(address));

        }

        return new Contacts(contactCache);
    }

    public ContactData infoEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
                withHome(home).withMobile(mobile).withWork(work).
                withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }
}
