import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Aina {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/sdet-java/Selenium/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://orangehrm-demo-6x.orangehrmlive.com/client/#/dashboard");

// 1. Login as administrator
        driver.findElement(By.xpath("//input[@id='btnLogin']")).click();

// 2. Open Admin -> Announcements -> News
        driver.findElement(By.xpath("//li[@id='menu_admin_viewAdminModule']")).click();
        driver.findElement(By.cssSelector("li[id='menu_news_Announcements']")).click();
        driver.findElement(By.xpath("//a[@id='menu_news_viewNewsList']")).click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.switchTo().frame("noncoreIframe");

// 3. Store all date in Map<String, List <String>>

        List<WebElement> topicList = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[2]"));
//        for (int i = 0; i < topicList.size(); i++){
//            System.out.println(topicList.get(i).getText());
//        }
        List<WebElement> dateList = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[3]"));
        List<WebElement> userRoles = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[6]"));
        List<WebElement> attachment = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[7]"));

// a. Print out the count of News

        Map<String, List<String>> topics = new HashMap<>();
        for (int i = 0; i < topicList.size(); i++) {
            List<String> temp = new LinkedList<>();
            temp.add(dateList.get(i).getText());
            temp.add(userRoles.get(i).getText());
            temp.add(attachment.get(i).getText());

            topics.put(topicList.get(i).getText(), temp);

            for (String key : topics.keySet()) {
                System.out.println(String.valueOf(key) + " | " + topics.get(key));


            }

        }









        Thread.sleep(3000);
        driver.close();

    }
}
