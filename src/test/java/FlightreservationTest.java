import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class FlightreservationTest {

    private WebDriver driver;
    String url = "https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html";


    private WebElement firstNameInput;

    private WebElement lastNameInput;

    private WebElement emailInput;

    private WebElement passwordInput;

    private WebElement streetInput;

    private WebElement cityInput;

    private WebElement zipInput;

    private WebElement registerButton;

    private WebElement goToFlightSearchButton;

    private WebElement searchFlightsButton;

    private WebElement passengerSelect;

    private List<WebElement> departureFlightsOptions;

    private List<WebElement> arrivalFlightsOptions;

    private WebElement confirmFlightsButton;

    private WebElement flightConfirmationElement;
    private WebElement totalPriceElement;

    Capabilities capabilities;

    private Alert alert;

    private WebDriverWait wait;


    @BeforeClass
    private void classSetup() throws MalformedURLException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        capabilities = new FirefoxOptions();
        // for running on chrome
        // capabilities = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        sleep(5);
    }


    @Test
    public void userRegistrationTest(){
        driver.get(url);
        driver.manage().window().maximize();

        firstNameInput = driver.findElement(By.id("firstName"));
        lastNameInput = driver.findElement(By.id("lastName"));
        emailInput = driver.findElement(By.id("email"));
        passwordInput = driver.findElement(By.id("password"));
        streetInput = driver.findElement(By.name("street"));
        cityInput = driver.findElement(By.name("city"));
        zipInput = driver.findElement(By.name("zip"));
        registerButton = driver.findElement(By.id("register-btn"));

        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        Assert.assertTrue(firstNameInput.isDisplayed());

        firstNameInput.sendKeys("rachit");
        lastNameInput.sendKeys("rachit");
        emailInput.sendKeys("rachit@rsa.com");
        passwordInput.sendKeys("rachit");
        streetInput.sendKeys("atta");
        cityInput.sendKeys("noida");
        zipInput.sendKeys("201303");

        registerButton.click();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        goToFlightSearchButton = driver.findElement(By.id("go-to-flights-search"));
        wait.until(ExpectedConditions.visibilityOf(goToFlightSearchButton));
        Assert.assertTrue(goToFlightSearchButton.isDisplayed());
        goToFlightSearchButton.click();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest(){
        searchFlightsButton = driver.findElement(By.id("search-flights"));
        wait.until(ExpectedConditions.visibilityOf(searchFlightsButton));

        Assert.assertTrue(searchFlightsButton.isDisplayed());
        passengerSelect = driver.findElement(By.id("passengers"));
        Select passengers = new Select(this.passengerSelect);
        passengers.selectByValue("2");
//        alert = driver.switchTo().alert();
//        alert.accept();
//        searchFlightsButton.click();
    }

//    @Test(dependsOnMethods = "flightsSearchTest")
//    public void flightsSelectionTest(){
//        departureFlightsOptions = driver.findElements(By.name("departure-flight"));
//        arrivalFlightsOptions = driver.findElements(By.name("arrival-flight"));
//        confirmFlightsButton = driver.findElement(By.id("confirm-flights"));
//
//        wait.until(ExpectedConditions.visibilityOf(confirmFlightsButton));
//        Assert.assertTrue(confirmFlightsButton.isDisplayed());
//
//        int random = ThreadLocalRandom.current().nextInt(0, departureFlightsOptions.size());
//        this.departureFlightsOptions.get(random).click();
//        this.arrivalFlightsOptions.get(random).click();
//
//        confirmFlightsButton.click();
//    }
//
//    @Test(dependsOnMethods = "flightsSelectionTest")
//    public void flightReservationConfirmationTest(){
//        flightConfirmationElement = driver.findElement(By.cssSelector("#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)"));
//        totalPriceElement = driver.findElement(By.cssSelector("#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)"));
//
//        wait.until(ExpectedConditions.visibilityOf(flightConfirmationElement));
//        flightConfirmationElement.isDisplayed();
//        String text = totalPriceElement.getText();
//        Assert.assertTrue(text.length() > 0);
//    }

    private void sleep(int seconds) {

        try {
            Thread.sleep(1000 * seconds);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }

}
