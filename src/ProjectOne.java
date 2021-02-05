package src;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.jvm.hotspot.utilities.Assert;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProjectOne {

    public static void main(String[] args) throws InterruptedException {
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

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.switchTo().frame("noncoreIframe");

        // 3. Store all date in Map<String, List <String>>

        List<WebElement> topicList = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[2]"));
        List<WebElement> dateList = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[3]"));
        List<WebElement> userRoles = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[6]"));
        List<WebElement> attachment = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[7]"));

        // handling attachments
        List<String> attachmentYN = new ArrayList<>();
        for (int i = 0; i < attachment.size(); i++) {
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
            System.out.println(String.valueOf(key) + " | " + newsList.get(key).get(0) + " | " + newsList.get(key).get(1) + " | " + newsList.get(key).get(2));
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

        //Verify count of news are more now than it was before
        List<WebElement> newsSize1 = driver.findElements(By.cssSelector(".dataRaw"));

        if (newsList.size() < newsSize1.size()) {
            System.out.println("More");
        }
        //Verify your news is displayed on the current table
        List<WebElement> topicListNews = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[2]"));
        for (WebElement item : topicListNews) {
            if (item.getText().equals("Congratulations Anna")) {
                System.out.println("True");
            }
        }

        driver.findElement(By.xpath("//i[@class='material-icons']")).click();
        driver.findElement(By.id("logoutLink")).click();
        //Log in as "1st level supervisor"
        driver.findElement(By.id("loginAsButtonGroup")).click();
        driver.findElement(By.xpath("//a[@data-password='kevin.mathews']")).click();

        //Navigate to news section
        driver.findElement(By.xpath("//li[@id='menu_news_More']")).click();
        driver.findElement(By.xpath("//li[@id='menu_news_viewAnnouncementModule']")).click();
        driver.findElement(By.id("menu_news_viewNewsArticles")).click();

        //In News section verify your newly added item is present
        List<WebElement> newItem = driver.findElements(By.xpath("//div[@id='header']"));
        for (WebElement item : newItem) {
            if (item.getText().equals("Congratulations Anna")) {
                System.out.println("Newly present item is present");

            }
        }
        
        //logout

        driver.findElement(By.id("account-job")).click();
        driver.findElement(By.id("logoutLink")).click();

        //13 Log in as Administrator

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //14 Open Admis -> Announcements -> News

        driver.findElement(By.id("menu_admin_viewAdminModule")).click();
        driver.findElement(By.id("menu_news_Announcements")).click();
        driver.findElement(By.id("menu_news_viewNewsList")).click();

        Thread.sleep(3000);
        driver.close();

    }
}
