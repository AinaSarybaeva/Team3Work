import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Karyna {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\karyk\\Documents\\Selenium\\selenium-java-3.141.59\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //4. Add new News item
        driver.findElement(By.xpath("//i[@class='large material-icons']")).click();
        driver.findElement(By.xpath("//input[@id='news_topic']")).sendKeys("Congratulations Anna");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.switchTo().frame("news_description_ifr");

        driver.findElement(By.xpath("//body[@id='tinymce']")).click();
        driver.findElement(By.xpath("//body[@id='tinymce']")).sendKeys("Promotion was awarded to Anna on 1/7/2020");

        // 5. Next
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//button[@class='modal-action waves-effect action-btn btn right cancel-btn']")).click();

        //6. Check "Publish to - All User Roles" option
        driver.findElement(By.xpath("//label[contains(text(), 'Publish To - All User Roles')]")).click();

        // 7. Publish
        driver.findElement(By.xpath("//button[@class='modal-action waves-effect action-btn btn right cancel-btn publish-btn']")).click();
        Thread.sleep(2000);
    }
}

    