package com.templatemonster.demo.bdd;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.testng.Assert;

public class AddComputer extends StoryBase {

    @Given("I open computer database")
    public void openComputerDatabasePage(){
     driver.get("http://computer-database.herokuapp.com/computers");
    }

    @When("I click [$buttonName]")
    public void clickAddComputer(String buttonName){
        if(buttonName.equals("Add computer")){
            driver.findElement(By.id("add")).click();
        }
    }

    @Then("'Add computer' page is opened")
    public void clickAddComputer(){
        Assert.assertEquals("Computers database", driver.getTitle());
    }

}
