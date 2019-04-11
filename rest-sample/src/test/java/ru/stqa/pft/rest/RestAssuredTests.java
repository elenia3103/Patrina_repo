package ru.stqa.pft.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {
    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(1209);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test").withDescription("Test desc");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        newIssues.add(newIssue.withId(issueId));
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues,oldIssues);

    }

    private Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given().parameter("subject", newIssue.getSubject()).
                parameter("description", newIssue.getDescription()).
                post("http://bugify.stqa.ru/api/issues.json").asString();

        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
