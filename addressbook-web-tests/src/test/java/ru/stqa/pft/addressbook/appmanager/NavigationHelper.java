package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }
    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }
    public void returnToContactPage () {
        wd.findElement(By.linkText("home page")).click();
    }



}
