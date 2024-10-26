package com.course.selenium.stepdefinitions;

import com.course.selenium.hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;

public class PurchaseSteps {
    private final WebDriver driver = Hooks.getDriver();

    @Given("I am on the main page")
    public void i_am_on_the_login_page() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @When("I log in with email {string} and password {string}")
    public void i_log_in_with_the_email_and_password(String email, String password) {
        driver.findElement(By.id("field-email")).sendKeys(email);
        driver.findElement(By.id("field-password")).sendKeys(password);
        driver.findElement(By.id("submit-login")).click();
    }

    @And("I click main button")
    public void iClickMainButton() {
        driver.findElement(By.xpath("//*[@id=\"link-product-page-prices-drop-1\"]")).click();
    }

    @And("I select the product {string}")
    public void iSelectTheProduct(String productName) {
        driver.findElement(By.xpath("//*[@id=\"js-product-list\"]/div[1]/div[3]/article/div/div[2]/h2/a")).click();
    }

    @And("I choose size M and quantity 5")
    public void iChooseSizeMAndQuantity() {
        Select sizeDropdown = new Select(driver.findElement(By.id("group_1")));
        sizeDropdown.selectByVisibleText("M");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-button-action='add-to-cart']")));

        WebElement quantityField = driver.findElement(By.id("quantity_wanted"));
        quantityField.sendKeys(Keys.chord(Keys.CONTROL, "a"), "5");

    }

    @And("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        driver.findElement(By.xpath("//button[@data-button-action='add-to-cart']")).click();
    }

    @And("I proceed to checkout")
    public void iProceedToCheckout() {
        driver.findElement(By.xpath("//div[@id='blockcart-modal']//a")).click();
    }


    @And("I proceed to secound checkout")
    public void iProceedToSecoundCheckout() {
        driver.findElement(By.xpath("//section[@id='main']//a[@class='btn btn-primary']")).click();
    }

    @And("I confirm the address")
    public void iConfirmTheAddress() {
        driver.findElement(By.name("confirm-addresses")).click();
        System.out.println("1");
    }

    @And("I choose the delivery method {string}")
    public void iChooseTheDeliveryMethod(String arg0) {
        driver.findElement(By.name("confirmDeliveryOption")).click();
    }

    @And("I choose the payment method {string}")
    public void iChooseThePaymentMethod(String arg0) {
        driver.findElement(By.id("payment-option-1")).click();
    }

    @And("I place the order")
    public void iPlaceTheOrder() {
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
        driver.findElement(By.xpath("//div[@id='payment-confirmation']//button[@class='btn btn-primary center-block']")).click();
    }

    @Then("I should see the confirmation with the total amount")
    public void iShouldSeeTheConfirmationWithTheTotalAmount() throws IOException {
        File ss = driver.findElement(By.id("main")).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(ss, new File("C:\\Users\\grudz\\TestowyScreen.png"));

    }
}


