import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.rmi.Remote;
import java.util.List;

/**
 * 1. Create two sets of test cases
 * 2. Launch the browser
 * 3. Navigate to the URL
 * 4. Hierarchy of interfaces and classes in Selenium Webdriver
 * 5. Why ChromeDrive driver = new ChromeDrvier(); is not the best practise
 */
public class Main {
    public static void main(String[] args) {
        //1. OPENS THE CHROME BROWSER
        //System.setProperty("webdriver.edge.driver", "E://Selenium//drivers//msedgedriver.exe"); //specifies the driver directory
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        //WebDriver driver = new EdgeDriver();
        WebDriver driver2 = new ChromeDriver();
        //extends SearchContext, get(), quit(), close(). getWindowHandle()
        //  SearchContext driver2 = new EdgeDriver(); //parent of webDriver, findElement(), findElements()
        //  RemoteWebDriver driver3 = new EdgeDriver();//RemoteWebDriver (Fully Implemented class)

        //2. NAVIGATES TO THE URL
        //driver.get("https://www.google.com");
        //driver2.get("https://www.google.com");
        driver2.get("http://automationpractice.com/index.php");
        String currentUrl = driver2.getCurrentUrl(); //returns the current url
        String title = driver2.getTitle(); //returns the title of the website
        String pageSrc = driver2.getPageSource(); //returns the whole html element
        //System.out.println(currentUrl + " "+title);
        //3. CLOSES THE BROWSER
        //driver.close();


        //*********************************************************************
        //*** finds element by id and set input value to the element ***
        //WebElement searchField = driver2.findElement(By.id("search_query_top"));
        //System.out.println(searchField);
        //searchField.sendKeys("testing input for search field");

        //*** finds <a> with its content ***
        //driver2.findElement(By.linkText("Blouse")).click();
        //driver2.findElement(By.partialLinkText("Dress")).click();

        //*** finds element by className ***
        WebElement searchFieldByClass = driver2.findElement(By.className("search_query"));
        searchFieldByClass.sendKeys("testing input using By.className");

        //*** finds element by tagName ***
        List<WebElement> links = driver2.findElements(By.tagName("a"));
        System.out.println(links.size());

        //outputs the content of all <a> tags
        //for(WebElement i : links){
        //    System.out.println(i.getText());
        //}

        //*** finds element using css selector
        WebElement womenTag = driver2.findElement(By.cssSelector("li>.sf-with-ul")); //returns the first matching element with className sf-with-ul
        womenTag.click();

        //driver2.close();

    }
}