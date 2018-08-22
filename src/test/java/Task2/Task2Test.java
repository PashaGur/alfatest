package Task2;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Task2Test {

    private WebDriver driver;
    private TestPage testPage;

    private final static String CORRECT_NEXT_URL = "https://anketa.alfabank.ru/alfaform-refpil/step2";
    private final static String ERROR_MANDATORY_FIELD = "Поле обязательно для заполнения";
    private final static String LAST_NAME = "Гурин";
    private final static String FIRST_NAME = "Павел";
    private final static String MIDDLE_NAME = "Павлович";
    private final static Boolean GENDER = true;
    private final static String EMAIL = "email@yandex.ru";
    private final static String PHONE_NUMBER = "9101234567";
    private final static String WORK_REGION = "Санкт-Петербург";


    @Before
    public void setUp() {

        System.setProperty("webdriver.gecko.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.edge.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\MicrosoftWebDriver.exe");

        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        //driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://anketa.alfabank.ru/alfaform-refpil/step1");
        testPage = new TestPage(driver);
    }

    @Test
    public void headingTest() {
        String heading = testPage.getHeadingText();
        Assert.assertEquals("Заявка на кредит наличными с возможностью рефинансирования", heading);
    }

    @Test
    public void oneToTenNumberTest() {
        String testingNumber = "1234567890";
        TestPage page = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, testingNumber, EMAIL, WORK_REGION);
        waitASecond();
        System.out.println("Testing phoneNumber = " + testingNumber + ", Error text = " + page.getPhoneNumberError());
        Assert.assertEquals(CORRECT_NEXT_URL, driver.getCurrentUrl());
    }

    @Test
    public void shortAndLongNumberTest() {
        TestPage page = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, "12345", EMAIL, WORK_REGION);
        Assert.assertNotNull(page.getPhoneNumberError());
        driver.navigate().refresh();

        TestPage page2 = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, "910123456789", EMAIL, WORK_REGION);
        System.out.println("Error text = " + page.getPhoneNumberError()+", but testing number was cut after 10 digit");
        Assert.assertNotNull(page2.getPhoneNumberError());
    }

    @Test
    public void wrongEmailTest() {
        String emailError = "Указан недопустимый email";
        TestPage page = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, PHONE_NUMBER, "someText", WORK_REGION);
        Assert.assertEquals(emailError, page.getEmailError());
        driver.navigate().refresh();

        TestPage page2 = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, PHONE_NUMBER, "email@iamnotevenexist", WORK_REGION);
        Assert.assertEquals(emailError, page2.getEmailError());
        driver.navigate().refresh();

        TestPage page3 = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, PHONE_NUMBER, "00000@00000000.00", WORK_REGION);
        Assert.assertEquals(emailError, page3.getEmailError());
        driver.navigate().refresh();

        //        TestPage page4 = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, PHONE_NUMBER, "email@iamnotevenexist.somedomain", WORK_REGION);
        //        System.out.println();
        //        Assert.assertEquals(emailError, page4.getEmailError());
        //        driver.navigate().refresh();
    }

    @Test
    public void stringToPhoneNumberTest() {
        TestPage page = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, "string", EMAIL, WORK_REGION);
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page.getPhoneNumberError());
    }

    @Test
    public void emptyMandatoryFields() {
        TestPage page = testPage.continueWithInvalidValues(null, FIRST_NAME, MIDDLE_NAME, PHONE_NUMBER, EMAIL, WORK_REGION);
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page.getLastNameError());
        driver.navigate().refresh();

        TestPage page2 = testPage.continueWithInvalidValues(LAST_NAME, null, MIDDLE_NAME, PHONE_NUMBER, EMAIL, WORK_REGION);
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page2.getFirstNameError());
        driver.navigate().refresh();

        TestPage page3 = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, null, PHONE_NUMBER, EMAIL, WORK_REGION);
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page3.getMiddleNameError());
        driver.navigate().refresh();

        TestPage page4 = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, null, EMAIL, WORK_REGION);
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page4.getPhoneNumberError());
        driver.navigate().refresh();

        TestPage page5 = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, PHONE_NUMBER, null, WORK_REGION);
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page5.getEmailError());
        driver.navigate().refresh();

        TestPage page6 = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, PHONE_NUMBER, EMAIL, null);
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page6.getWorkRegionError());
        driver.navigate().refresh();

        TestPage page7 = testPage.continueWithInvalidValues("Гу", "Па", "Па", null, PHONE_NUMBER, EMAIL, null);
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page7.getGenderFieldError());
    }


    @Test
    public void wrongWorkRegionTest() {
        TestPage page = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, PHONE_NUMBER, EMAIL, "Несуществующий");
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page.getWorkRegionError());
        driver.navigate().refresh();
        TestPage page2 = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, PHONE_NUMBER, EMAIL, "123");
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page2.getWorkRegionError());
        driver.navigate().refresh();
        TestPage page3 = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, PHONE_NUMBER, EMAIL,
                "яяяяяяяяяя_ яяяяяяяяяяяя3123123яяяяяяяяяяяяяяяяяяяяяяяяяяasdadsяяяяяяяяяя");
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page3.getWorkRegionError());
        driver.navigate().refresh();
        TestPage page4 = testPage.continueWithInvalidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, PHONE_NUMBER, EMAIL, "САНКТ");
        Assert.assertEquals(ERROR_MANDATORY_FIELD, page4.getWorkRegionError());
        driver.navigate().refresh();
    }

    @Test
    public void shortNamesTest() {
        testPage.continueWithValidValues("Гу", "Па", "Па", GENDER, PHONE_NUMBER, EMAIL, WORK_REGION);
        waitASecond();
        Assert.assertEquals(CORRECT_NEXT_URL, driver.getCurrentUrl());
    }

    @Test
    public void validValuesTest() {
        testPage.continueWithValidValues(LAST_NAME, FIRST_NAME, MIDDLE_NAME, GENDER, PHONE_NUMBER, EMAIL, WORK_REGION);
        waitASecond();
        Assert.assertEquals(CORRECT_NEXT_URL, driver.getCurrentUrl());
    }

    @Test
    public void validTestWrongKeyboardLang() {
        testPage.continueWithInvalidValues("Uehby", "Gfdtk", "Gfdkjdbx", PHONE_NUMBER, EMAIL, WORK_REGION);
        waitASecond();
        Assert.assertEquals(CORRECT_NEXT_URL, driver.getCurrentUrl());
    }


    @After
    public void tearDown() {
        //   driver.quit();
    }

    private void waitASecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
