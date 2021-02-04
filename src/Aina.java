import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.util.*;
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
        List<WebElement> dateList = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[3]"));
        List<WebElement> userRoles = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[6]"));
        List<WebElement> attachment = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[7]/i"));

        // handling attachments
        List<String> attachmentYN = new ArrayList<>();
        for (var i=0; i<attachment.size(); i++) {
            if (attachment.get(i).getAttribute("class").contains("disabled")) {     //getting the value from attribute('class')
                attachmentYN.add("No");
            } else {
                attachmentYN.add("Yes");
            }
        }

        Map<String, List<String>> newsList = new HashMap<>();

        for (int i = 0; i < topicList.size(); i++) {
            List<String> temp = new LinkedList<>();
            temp.add(dateList.get(i).getText());
            temp.add(userRoles.get(i).getText());
            temp.add(attachmentYN.get(i));

            newsList.put(topicList.get(i).getText(), temp);
        }

        // a. Print out the count of news
        System.out.println("News List: " + newsList.size());

        // b. Print out the map
        for (String key : newsList.keySet()) {
            System.out.println(String.valueOf(key) +" | "+ newsList.get(key).get(0) +" | "+ newsList.get(key).get(1) +" | "+ newsList.get(key).get(2));
        }

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        // 4. Add new News item
        driver.findElement(By.cssSelector("i[class='large material-icons']")).click();
        driver.findElement(By.cssSelector("input[id='news_topic']")).sendKeys("Congratulations Anna");

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.switchTo().frame("news_description_ifr");

        driver.findElement(By.cssSelector("body[id='tinymce']")).click();
        driver.findElement(By.cssSelector("body[id='tinymce']")).sendKeys("Promotion was awarded to Anna on 1/7/2020");

        // Next button
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//button[@class='modal-action waves-effect action-btn btn right cancel-btn']")).click();

        //Publish to all other users
        driver.findElement(By.xpath("//label[contains(text(), 'Publish To - All User Roles')]")).click();

        //Click publish
        driver.findElement(By.xpath("//button[@class='modal-action waves-effect action-btn btn right cancel-btn publish-btn']")).click();
        Thread.sleep(4000);







        Thread.sleep(3000);
        driver.close();

    }
}
