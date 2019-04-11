package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.File;
import java.util.List;

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config.inc.php"),
                "config.inc.php","config.inc.php.bak");
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config.inc.php.bak","config.inc.php");
        app.stop();

    }

}
