package Task2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class TestPage {

    private WebDriver driver;

    public TestPage(WebDriver driver) {
        this.driver = driver;
    }

    private By headingText = By.xpath("//div[@class='top__info']/h2");
    private By lastNameField = By.xpath("//input[@name='lastName']");
    private By firstNameField = By.xpath("//input[@name='firstName']");
    private By middleNameField = By.xpath("//input[@name='middleName']");
    private By mobilePhoneField = By.xpath("//input[@name='mobilePhone']");
    private By emailField = By.xpath("//input[@name='email']");
    private By workRegionField = By.xpath("//input[@name='workRegionCode']");
    private By continueButton = By.xpath("//span[text()='Продолжить']");
    private By lastNameError = By.xpath("//input[@name='lastName']/parent::span/following-sibling::span/div");
    private By firstNameError = By.xpath("//input[@name='firstName']/parent::span/following-sibling::span/div");
    private By middleNameError = By.xpath("//input[@name='middleName']/parent::span/following-sibling::span/div");
    private By genderFieldError = By.xpath("//span[@class='radio-group__sub']//div");
    private By mobilePhoneError = By.xpath("//input[@name='mobilePhone']/parent::span/following-sibling::span/div");
    private By eMailError = By.xpath("//input[@name='email']/parent::span/following-sibling::span/div");
    private By workRegionError = By.xpath("//input[@name='workRegionCode']/parent::span/following-sibling::span/div");

    public TestPage continueWithValidValues(String lastName, String firstName, String middleName, Boolean gender, String mobilePhone, String email, String workRegion) {
        this.typeLastName(lastName);
        this.typeFistName(firstName);
        this.typeMiddleName(middleName);
        this.typeGender(gender);
        this.typePhoneNumber(mobilePhone);
        this.typeEmail(email);
        this.typeWorkRegion(workRegion);
        this.clickContinueButton();
        return new TestPage(driver);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public String getHeadingText() {
        return driver.findElement(headingText).getText();
    }

    public TestPage typeLastName(String lastName) {
        if (lastName != null) {
            driver.findElement(lastNameField).sendKeys(lastName);
        }
        return this;
    }

    public TestPage typeFistName(String firstName) {
        if (firstName != null) {
            driver.findElement(firstNameField).sendKeys(firstName);
        }
        return this;
    }

    public TestPage typeMiddleName(String middleName) {
        if (middleName != null) {
            driver.findElement(middleNameField).sendKeys(middleName + Keys.TAB);
        }
        return this;
    }

    public TestPage chooseGender(Boolean male) {
        if (male != null) {
            if (male) {
                driver.findElement(By.xpath("//span[text()='Мужской']"));
            } else {
                driver.findElement(By.xpath("//span[text()='Женский']"));
            }
        }
        return this;
    }

    public TestPage typeGender(Boolean gender) {
        if (gender != null) {
            try {
                if (gender) {
                    driver.findElement(By.xpath("//span[text()='Мужской']")).click();
                } else {
                    driver.findElement(By.xpath("//span[text()='Женский']")).click();
                }
            } catch (Exception e) {
                return this;
            }
        }
        return this;
    }

    public TestPage typePhoneNumber(String phoneNumber) {
        if (phoneNumber != null) {
            driver.findElement(mobilePhoneField).sendKeys(phoneNumber);
        }
        return this;
    }

    public TestPage typeEmail(String email) {
        if (email != null) {
            driver.findElement(emailField).sendKeys(email);
        }
        return this;
    }

    public TestPage typeWorkRegion(String workRegion) {
        if (workRegion != null) {
            String workRegionPath = String.format("//span[text()='%s']", workRegion);
            driver.findElement(workRegionField).click();
            try {
                driver.findElement(By.xpath(workRegionPath)).click();
            } catch (Exception e) {
                return this;
            }
        }
        return this;
    }

    public TestPage continueWithInvalidValues(String lastName, String firstName, String middleName, String mobilePhone, String email, String workRegion) {
        this.typeLastName(lastName);
        this.typeFistName(firstName);
        this.typeMiddleName(middleName);
        this.typePhoneNumber(mobilePhone);
        this.typeEmail(email);
        this.typeWorkRegion(workRegion);
        this.clickContinueButton();
        return new TestPage(driver);
    }

    public TestPage continueWithInvalidValues(String lastName, String firstName, String middleName, Boolean gender, String mobilePhone, String email, String workRegion) {
        this.typeLastName(lastName);
        this.typeFistName(firstName);
        this.typeMiddleName(middleName);
        this.chooseGender(gender);
        this.typePhoneNumber(mobilePhone);
        this.typeEmail(email);
        this.typeWorkRegion(workRegion);
        this.clickContinueButton();
        // driver.findElement(continueButton).click();
        return new TestPage(driver);
    }

    public String getLastNameError() {
        return driver.findElement(lastNameError).getText();
    }

    public String getFirstNameError() {
        return driver.findElement(firstNameError).getText();
    }

    public String getMiddleNameError() {
        return driver.findElement(middleNameError).getText();
    }

    public String getGenderFieldError() {
        return driver.findElement(genderFieldError).getText();
    }

    public String getPhoneNumberError() {
        try {
            return driver.findElement(mobilePhoneError).getText();
        } catch (Exception e){
            return null;
        }

    }

    public String getEmailError() {
        return driver.findElement(eMailError).getText();
    }

    public String getWorkRegionError() {
        return driver.findElement(workRegionError).getText();
    }
}
