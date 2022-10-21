import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * To access the element in shadow DOM, we have to start with the Shadow host(the entry of a Shadow Dom)
 */
public class ShadowDOM {
    public static void main(String[] args) {
        //Open the Chrome browser
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Navigate to the URL
        driver.get("https://books-pwakit.appspot.com/");

        //Shadow host
        WebElement shadowHost = driver.findElement(By.xpath("//book-app"));
        System.out.println("Shadow host found" + shadowHost.getTagName());

        //Shadow dom
        JavascriptExecutor j = (JavascriptExecutor) driver;
        SearchContext shadowdom = (SearchContext) j.executeScript("return arguments[0].shadowRoot",shadowHost);

        //search filed inside the shadow DOM
        WebElement searchField = shadowdom.findElement(By.cssSelector("input#input"));
        System.out.println(searchField.getTagName());
        searchField.sendKeys("Selenium WebDriver");

    }
}
