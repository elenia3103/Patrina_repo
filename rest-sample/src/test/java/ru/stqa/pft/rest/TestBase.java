package ru.stqa.pft.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {

    @BeforeSuite
    public void init() throws Exception {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
    }
    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
    public String statusIssue(int issueId){
        String json = RestAssured.get(String.format("http://bugify.stqa.ru/api/issues/" + issueId + ".json")).asString();
        JsonElement parsed = new JsonParser().parse(json);
        String status = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0).
                getAsJsonObject().get("state_name").getAsString();
        return status;
    }

    private boolean isIssueOpen(int issueId) {
        if(!(statusIssue(issueId).equals("Resolved")||statusIssue(issueId).equals("Closed"))){
            return true;
        } else
        return false;
    }


    @AfterSuite (alwaysRun = true)
    public void tearDown() throws Exception {


    }

}
