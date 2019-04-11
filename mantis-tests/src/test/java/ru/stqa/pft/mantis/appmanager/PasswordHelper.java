package ru.stqa.pft.mantis.appmanager;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static jdk.nashorn.internal.objects.NativeJava.type;

public class PasswordHelper extends BaseHelper {

    public PasswordHelper(ApplicationManager app) {
        super(app);
    }



    public void selectUserById(int id){
        WebElement selectedUser = wd.findElement(By.cssSelector("//a[contains(@href, 'manage_user_edit_page.php?user_id="+id+"')]"));
        selectedUser.click();
    }

    public void startChange(String username) {
        click(By.linkText("Manage"));
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        click(By.linkText(username));
        click(By.cssSelector("input[value='Reset Password']"));
        click(By.linkText("Logout"));
    }

    public void changePassword(String passwordLink, String newPassword) {
        wd.get(passwordLink);
        click(By.cssSelector("input[value='Update User']"));
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login.php");
        type(By.name("username"),username);
        type(By.name("password"),password);
        click(By.cssSelector("input[value='Login']"));
    }
}
