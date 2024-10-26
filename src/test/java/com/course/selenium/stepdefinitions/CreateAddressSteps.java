package com.course.selenium.stepdefinitions;

import com.course.selenium.browserFactory.BrowserFactory;
import com.course.selenium.hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class CreateAddressSteps {
    private final WebDriver driver = Hooks.getDriver();

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @When("I log in with the email {string} and password {string}")
    public void i_log_in_with_the_email_and_password(String email, String password) {
        driver.findElement(By.id("field-email")).sendKeys(email);
        driver.findElement(By.id("field-password")).sendKeys(password);
        driver.findElement(By.id("submit-login")).click();
    }

    @When("I navigate to the addresses page")
    public void i_navigate_to_the_addresses_page() {
        driver.findElement(By.xpath("//a[@title='Addresses']")).click();
    }

    @When("I create a new address with:")
    public void i_create_a_new_address_with(io.cucumber.datatable.DataTable dataTable) {
        driver.findElement(By.xpath("//a[@data-link-action='add-address']")).click();
        //mapa - mapowanie nazwy do wartości, ale zależy od konstrukcji tabel, w tym wypadku dopaskowanie jest w kolumnach
        // a get 0 dopasowuj warotości z pierwszego wiersza
        //https://github.com/cucumber/cucumber-jvm/tree/main/datatable
        Map<String, String> addressData = dataTable.entries().get(0);
        driver.findElement(By.id("field-alias")).sendKeys(addressData.get("alias"));
        driver.findElement(By.id("field-address1")).sendKeys(addressData.get("address"));
        driver.findElement(By.id("field-city")).sendKeys(addressData.get("city"));
        driver.findElement(By.id("field-postcode")).sendKeys(addressData.get("zip code"));
//        new select obiekt klasy select korzystający z listy danaych na stronie, która pozwala na wybór szukanych wartości
        Select countrySelect = new Select(driver.findElement(By.id("field-id_country")));
        countrySelect.selectByVisibleText(addressData.get("country"));

        driver.findElement(By.id("field-phone")).sendKeys(addressData.get("phone"));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("the new address should be displayed correctly")
    public void the_new_address_should_be_displayed_correctly() {
        // Znajdź wszystkie adresy na stronie i "zapisuje" w liście
        List<WebElement> adresscontainers = driver.findElements(By.className("address"));
        // Wyciągam ostatni element z listy
        WebElement adresscontainer = adresscontainers.get(adresscontainers.size()-1);
        WebElement alias = adresscontainer.findElement(By.xpath(".//h4"));  // Alias adresu
        WebElement address = adresscontainer.findElement(By.xpath(".//address"));  // Szczegóły adresu

        // Wyodrębnij tekst z adresu i podziel go na linie
        String addressText = address.getText();
        //dzielenie na oddzielne teksty po przejściu do nowej linii
        String[] addressLines = addressText.split("\n");

        // Sprawdź alias
        Assert.assertEquals("Home", alias.getText());

        // Sprawdź szczegóły adresu
        Assert.assertEquals("Sucha", addressLines[1]);       // Ulica
        Assert.assertEquals("Wwa", addressLines[2]);         // Miasto
        Assert.assertEquals("12345", addressLines[3]);       // Kod pocztowy
        Assert.assertEquals("United Kingdom", addressLines[4]); // Kraj
        Assert.assertEquals("123456789", addressLines[5]);   // Numer telefonu
    }
}


