package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class SecondGroupCreationTests extends TestBase {


  @Test
  public void testSecondGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("testov", "test", null));
    app.getGroupHelper().submitGroupCreation();
    app.getNavigationHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before+1);
  }

}
