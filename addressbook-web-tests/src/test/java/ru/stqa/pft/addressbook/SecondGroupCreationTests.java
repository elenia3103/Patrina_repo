package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class SecondGroupCreationTests extends TestBase {


  @Test
  public void testSecondGroupCreation() throws Exception {
    initGroupCreation();
    fillGroupForm(new GroupData("testov", "test", "testovodze"));
    submitGroupCreation();
    returnToGroupPage();

  }

}
