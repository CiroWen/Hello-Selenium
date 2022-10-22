import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserCommands {
    public static void main(String[] args) throws InterruptedException {
        //Open the Chrome browser
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Navigate to the URL
        String url = "https://demoqa.com/browser-windows";
        driver.get(url);

        //Maximize the window
        //driver.manage().window().maximize();

        //getCurrentUrl()
        String pageUrl = driver.getCurrentUrl();
        System.out.println("The URL of the page is: " + pageUrl);
        //Verify the URL
        if(pageUrl.equals(url)){
            System.out.println("The URL test passed");
        }else{
            System.out.println("The URL test failed");
        }

        //getTitle()
        String title = driver.getTitle();
        System.out.println("The title of the page is: " + title);
        //Verify the URL
        if(title.equals("ToolsQA")){
            System.out.println("The title test passed");
        }else{
            System.out.println("The title test failed");
        }

        //getPageSource()
        String ps = driver.getPageSource();
        System.out.println("The source of the page is: " + ps.length());

        //click()
        //driver.findElement(By.xpath("//button[@id='tabButton']")).click();

        //navigate().to()
        driver.navigate().to("https://www.google.com");

        //enter "apple" to the search bar
        WebElement searchBar = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
        searchBar.sendKeys("apple");

        //navigate().refresh()
        Thread.sleep(1000);
        driver.navigate().refresh();
        System.out.println("Refreshed the website");

        //navigate().back() to demoqa.com (Previous page)
        Thread.sleep(1000);
        driver.navigate().back();
        System.out.println("The page is navigated back to demoqa");

        //navigate().forward() to google. (Next page)
        Thread.sleep(1000);
        driver.navigate().forward();
        System.out.println("The page is forwarded to google");
        //close() closes the website/tag that Selenium is running automated tests.
        Thread.sleep(1000);
        driver.close();

        //quit() closes all browser tags and ends the WebDriver session.
        Thread.sleep(1000);
        driver.quit();

    }
}
