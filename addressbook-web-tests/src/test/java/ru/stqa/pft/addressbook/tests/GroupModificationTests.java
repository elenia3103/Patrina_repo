package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification(){
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("testov", "test", null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("testov", "test", "testovodze"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().returnToGroupPage();
    }
}
