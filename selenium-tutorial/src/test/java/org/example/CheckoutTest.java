package org.example;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Set the path to your WebDriver executable
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testCheckoutProcess() {
        // Navigate to the login page
        driver.get("http://localhost/cafe/login.php");

        // Log in as a user first
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("pass"));
        WebElement submitButton = driver.findElement(By.name("submit"));

        emailField.sendKeys("hirushiamaya392@gmail.com");
        passwordField.sendKeys("1234");
        submitButton.click();

        // Wait for redirection to the user homepage
        wait.until(ExpectedConditions.urlToBe("http://localhost/cafe/home.php"));

        // Navigate to the checkout page
        driver.get("http://localhost/cafe/checkout.php");

        // Fill out the checkout form
        WebElement nameField = driver.findElement(By.name("name"));
        WebElement numberField = driver.findElement(By.name("number"));
        WebElement emailCheckoutField = driver.findElement(By.name("email"));
        WebElement paymentMethod = driver.findElement(By.name("method"));
        WebElement flatField = driver.findElement(By.name("flat"));
        WebElement streetField = driver.findElement(By.name("street"));
        WebElement cityField = driver.findElement(By.name("city"));
        WebElement stateField = driver.findElement(By.name("state"));
        WebElement countryField = driver.findElement(By.name("country"));
        WebElement pinCodeField = driver.findElement(By.name("pin_code"));
        WebElement placeOrderButton = driver.findElement(By.name("order"));

        nameField.sendKeys("John Doe");
        numberField.sendKeys("1234567890");
        emailCheckoutField.sendKeys("john.doe@example.com");
        paymentMethod.sendKeys("cash on delivery");
        flatField.sendKeys("101");
        streetField.sendKeys("Main St");
        cityField.sendKeys("City");
        stateField.sendKeys("State");
        countryField.sendKeys("Country");
        pinCodeField.sendKeys("123456");

        // Submit the order
        placeOrderButton.click();

        // Wait for a confirmation message or redirection to confirm order placement
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'order placed successfully!')]")));
        assert confirmationMessage != null : "Order placement failed or confirmation not received.";
    }

    @AfterEach
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
