import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ProjectOne {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/ihorsavko/Projects/libs/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Open website
        driver.get("https://orangehrm-demo-6x.orangehrmlive.com/auth/login");
        //Click on Login button
        driver.findElement(By.id("btnLogin")).click();
        //Open Admin -> Announcements -> News
        driver.findElement(By.xpath("//a[@class='collapsible-header waves-effect waves-orange']")).click();
        driver.findElement(By.id("menu_news_Announcements")).click();
        driver.findElement(By.id("menu_news_viewNewsList")).click();







    }
}
