package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("testov", "test", null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroup();
    app.getNavigationHelper().returnToGroupPage();
  }

}
