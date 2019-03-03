package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class SecondGroupCreationTests extends TestBase {


  @Test
  public void testSecondGroupCreation() throws Exception {
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("testov", "test", null));
    app.getGroupHelper().submitGroupCreation();
    app.getNavigationHelper().returnToGroupPage();

  }

}
