package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private DbHelper dbHelper;
    private PasswordHelper passwordHelper;
    private HttpSession session;
    private SoapHelper soapHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    }

    public void stop() {
        if (wd!=null) {
            wd.quit();
        }
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }
    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public HttpSession session() {
        if(session == null) {
            session = new HttpSession(this);
        }
        return session;
    }
    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp(){
        if (ftp == null) {
            ftp = new FtpHelper(this);

        }
        return ftp;
    }
    public MailHelper mail(){
        if (mailHelper ==null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public WebDriver getDriver() {
        if (wd == null){
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.EDGE)) {
                wd = new EdgeDriver();
            }


            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
    public DbHelper db() {
        if(dbHelper==null){
            dbHelper = new DbHelper();
        }
        return dbHelper;
    }
    public PasswordHelper passwordHelper(){
        if(passwordHelper==null){
            passwordHelper = new PasswordHelper(this);
        }
        return passwordHelper;
    }
    public SoapHelper soap(){
        if(soapHelper==null){
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}


