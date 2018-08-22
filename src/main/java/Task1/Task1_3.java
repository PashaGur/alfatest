package Task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task1_3 {

    private static WebDriver driver;

    public static void main(String[] args) {

        System.setProperty("webdriver.gecko.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.edge.driver", "D:\\IdeaProjects\\AlfaTest\\drivers\\MicrosoftWebDriver.exe");

//        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://yandex.ru/");
        driver.findElement(By.xpath("//div[@class='home-arrow__tabs']//a[text()='Маркет']")).click();
        driver.findElement(By.xpath("//a[@class='link topmenu__link'][text()='Электроника']")).click();
        driver.findElement(By.xpath("//div[@class='theme_light']//a[text()='Мобильные телефоны']")).click();
        driver.findElement(By.xpath("//a[text()='по цене']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> webElements =
                driver.findElements(By.xpath("//div[starts-with(@class,'n-snippet')]//div[@class='price']"));

        List<Integer> pricesList = new ArrayList<>();
        for (WebElement element : webElements) {
            pricesList.add(Integer.parseInt(element.getText().replaceAll("[ \u20BD]", "")));
        }

        checkIfElemsSorted(pricesList);

    }

    private static void checkIfElemsSorted(List<Integer> pricesList) {
        boolean sorted = true;
        for (int i = 1; i < pricesList.size(); i++) {
            if (pricesList.get(i - 1) > pricesList.get(i)) {
                sorted = false;
                break;
            }
        }
        System.out.println("Are prices sorted? " + sorted);
    }
}
