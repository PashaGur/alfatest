package Task2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MainClass {

    static WebDriver driver;

    public static void main(String[] args) {

        System.setProperty("webdriver.gecko.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.edge.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\MicrosoftWebDriver.exe");

        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        //driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://anketa.alfabank.ru/alfaform-refpil/step1");
        TestPage testPage = new TestPage(driver);
    }
}
