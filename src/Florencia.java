public class Florencia {
  public static void main(String[] args) throws InterruptedException, AWTException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\F\\Desktop\\Projects\\Selenium\\libs\\Browers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://orangehrm-demo-6x.orangehrmlive.com/auth/login");

        //1

        driver.findElement(By.xpath("//input[@id='btnLogin']")).click();

        //2

        driver.findElement(By.xpath("//span[@class='material-icons collapsible-indicator']")).click();
        driver.findElement(By.id("menu_news_Announcements")).click();
        driver.findElement(By.id("menu_news_viewNewsList")).click();


        driver.switchTo().frame("noncoreIframe");

        //3

        List<WebElement> topics = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[2]"));
        List<WebElement> publishedDAte = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[3]"));
        List<WebElement> publishedUserRoles = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[6]"));
        List<WebElement> attachments = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[7]"));

        List<String> yesOrNoAttachment = new ArrayList<>();
        for (int i = 0; i < attachments.size(); i++) {
            if (attachments.get(i).getAttribute("class").contains("disabled")) {
                yesOrNoAttachment.add("NO");
            } else {
                yesOrNoAttachment.add("YES");
            }
        }

        Map<String, List<String>> newsList = new HashMap<>();
        for (int i = 0; i < topics.size(); i++) {

            List<String> others = new LinkedList<>(); //change the name
            others.add(publishedDAte.get(i).getText());
            others.add((publishedUserRoles.get(i).getText()));
            others.add(yesOrNoAttachment.get(i));

            newsList.put(topics.get(i).getText(), others);
        }

        //Icant print map
        for (String key : newsList.keySet()) ;

        System.out.println(topics.size());



        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        //4

        driver.findElement(By.xpath("//i[@class='large material-icons']")).click();
        driver.findElement(By.xpath("//input[@id='news_topic']")).sendKeys("Congratulations Ana");

        driver.switchTo().frame("news_description_ifr");

        driver.findElement(By.xpath("//body[@id='tinymce']")).click();
        driver.findElement(By.xpath("//body[@id='tinymce']")).sendKeys("Promotion was awarded to Anna on 1/7/2020");


        driver.switchTo().parentFrame();

        //5

        driver.findElement(By.id("nextBtn")).click();

        //6

        driver.findElement(By.xpath("//label[@for='news_publish_all']")).click();

        //7

        driver.findElement(By.xpath("//button[@btn-type='publish']")).click();



        //8
        List<WebElement> afterAddNews = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[2]"));
  

}
