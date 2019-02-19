package bindings;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class Basket {

    WebDriver driver;


    @Given("^that i am on the shopping website$")
    public void that_i_am_on_the_shopping_website() throws Throwable {

        driver = ChromeDriverUtils.openChromeBrowser("https://www.edgewordstraining.co.uk/demo-site/");
    }

    @When("^i add an item to the basket$")
    public void i_add_an_item_to_the_basket() throws Throwable {
        driver.findElement(By.cssSelector("#masthead [type='search']")).click();
        driver.findElement(By.cssSelector("#masthead [type='search']")).sendKeys("cap");
        driver.findElement(By.cssSelector("#masthead [type='search']")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(".entry-summary button")).click();


    }

    @Then("^i can view the item in my basket$")
    public void i_can_view_the_item_in_my_basket() throws Throwable {
        driver.findElement(By.linkText("Cart")).click();
        assertTrue(driver.findElement(By.linkText("Cap")).isDisplayed());
        driver.findElement(By.linkText("×")).click();
        // explicit wait for item to be removed as may use AJAX
        WebDriverWait wait=new WebDriverWait(driver, 20); //explicit wait if we want to use
        WebElement returnToShopLink;
        returnToShopLink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Return to shop")));
        returnToShopLink.click();
        //driver.findElement(By.linkText("Return to shop")).click();
        driver.quit();    }

}
