package Task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task1_1 {

    private static WebDriver driver;

    public static void main(String[] args) {

        System.setProperty("webdriver.gecko.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.edge.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\MicrosoftWebDriver.exe");

        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://yandex.ru/");
        driver.findElement(By.xpath("//div[@class='home-arrow__tabs']//a[text()='Маркет']")).click();
        driver.findElement(By.xpath("//a[@class='link topmenu__link'][text()='Электроника']")).click();
        driver.findElement(By.xpath("//div[@class='theme_light']//a[text()='Мобильные телефоны']")).click();
        driver.findElement(By.xpath("//span[text()='Все фильтры']")).click();
        WebElement startPriceField = driver.findElement(By.xpath("//input[@id='glf-pricefrom-var']"));
        startPriceField.sendKeys("20000");
        driver.findElement(By.xpath("//label[text()='Apple']")).click();
        driver.findElement(By.xpath("//label[text()='Samsung']")).click();
        driver.findElement(By.xpath("//div[@class='n-filter-panel-extend__button-bar']//a[2]")).click();
        String webElementsPath = "/html[1]/body[1]/div[1]/div[4]/div[2]/div[1]/div[2]/div[1]/div[1]/div[starts-with(@class,'n-snippet')]";
        List<WebElement> webElements = driver.findElements(By.xpath(webElementsPath));

        checkElementsSize(webElements);

        String firstElemName = driver.findElement(By.xpath("(" + webElementsPath + ")[1]/div[3]/div[2]/a")).getText();
        driver.findElement(By.xpath("//input[@id='header-search']")).sendKeys(firstElemName);
        driver.findElement(By.xpath("//button[@type='submit']")).submit();

        checkFirstElem(firstElemName);

        //        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        driver.quit();
    }

    private static void checkFirstElem(String firstElemName) {
        try {
            driver.findElement(By.xpath("//a[@title='" + firstElemName + "']"));
            System.out.println(firstElemName + " was found by search");
        } catch (Exception e) {
            System.out.println("Failed to find " + firstElemName);
        }
    }

    private static Boolean checkElementsSize(List<WebElement> webElements) {
        if (webElements.size() == 12) {
            System.out.println("There are 12 elements on check page");
            return true;
        } else {
            System.out.println("Elements of check page != 12, actual size = " + webElements.size());
            return false;
        }
    }

}
