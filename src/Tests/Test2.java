package Tests;

import Tests.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test2 extends BaseDriver {

    //        https://app.hubspot.com/login
    @Test(priority = 0)
    public void login() {
//        https://app.hubspot.com/login
        driver.get( "https://app.hubspot.com/login" );
        WebDriverWait wait = new WebDriverWait( driver, 5 );
        login( wait );
    }

    @Test(priority = 1)
    @Parameters({"Domain", "Name"})
    void contacts(String domain, String name01) throws InterruptedException {
        WebElement contacts = driver.findElement( By.cssSelector( "a[id='nav-primary-contacts-branch'][data-tracking='click hover']" ) );
        contacts.click();
        //        Click on companies button
        driver.findElement( By.xpath( "(//div[contains(text(),'Companies')])[1]" ) ).click();

        //        Click on create companies button
        driver.findElement( By.xpath( "//span[text()='Create company']" ) ).click();

//        Enter the company domain
        WebElement domainName = driver.findElement( By.cssSelector( "input[data-field='domain']" ) );
        String domNAme = domain;
        domainName.sendKeys( domNAme );

//        Enter the name
        WebElement name = driver.findElement( By.cssSelector( "input[data-field='name']" ) );
        String firstName = name01;
        name.clear();
        name.sendKeys( firstName );

//        Click on Create company button
        driver.findElement( By.cssSelector( "button[data-confirm-button=\"accept\"]" ) ).click();

        //        Verify company domain as entered
        String domainText = driver.findElement( By.xpath( "//div[@data-test-id='domain-input']/span/span/span" ) ).getText();
        Assertion( domainText, domNAme );
    }

    private void login(WebDriverWait wait) {
        By emailinput = By.id( "username" );
        wait.until( ExpectedConditions.visibilityOfElementLocated( emailinput ) );

        //        Enter the user name and password click on login button
        driver.findElement( emailinput ).sendKeys( "furkatkhalilov@gmail.com" );
        driver.findElement( By.cssSelector( "input[id='password']" ) ).sendKeys( "Hotspot12345" );
        driver.findElement( By.id( "loginBtn" ) ).click();
    }

    private void Assertion(String str1, String str2) {
        Assert.assertEquals( str1, str2 );
    }

}
