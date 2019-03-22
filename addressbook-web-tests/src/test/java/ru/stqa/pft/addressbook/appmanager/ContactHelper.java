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
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }

        return new Contacts(contactCache);
    }
}
