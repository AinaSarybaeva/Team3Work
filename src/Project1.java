import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Project1 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/sdet-java/Selenium/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://orangehrm-demo-6x.orangehrmlive.com/client/#/dashboard");

<<<<<<< HEAD




        Thread.sleep(3000);
        driver.close();
=======
>>>>>>> 46a2ff103606f1e6bfcf6bc2b3e6e88237a31011
    }
}
