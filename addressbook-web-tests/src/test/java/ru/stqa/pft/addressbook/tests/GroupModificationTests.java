package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().groupPage();
        if (app.group().all().size()==0) {
            app.group().create(new GroupData().withName("testovyi").withHeader("test"));
        }
    }
    @Test
    public void testGroupModification(){

        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData  group = new GroupData().withId(modifiedGroup.getId()).withName("testovich").withHeader("test").withFooter("testovodze");
        app.group().modify(group);
        Assert.assertEquals(app.group().count(), before.size());
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
