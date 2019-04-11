package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{
    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }
    @Test
    public void testChangePassword() throws IOException, MessagingException {
        app.passwordHelper().login("Administrator","root");
        Users users = app.db().users();
        UserData user = users.iterator().next();
        String username = user.getUsername();
        String email = user.getEmail();
        String newPassword = String.format("password123");
        app.passwordHelper().startChange(username);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
        String passwordLink = findConfirmationLink(mailMessages, email);
        app.passwordHelper().changePassword(passwordLink, newPassword);
        assertTrue(app.session().login(user.getUsername(), newPassword));
    }
    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
