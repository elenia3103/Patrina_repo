package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class SecondGroupCreationTests extends TestBase {


  @Test
  public void testSecondGroupCreation() throws Exception {
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("testov", "test", "testovodze"));
    app.submitGroupCreation();
    app.returnToGroupPage();

  }

}
