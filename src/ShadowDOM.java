import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        WebElement shadowdom = (WebElement) j.executeScript("return arguments[0].shadowRoot",shadowHost);

        //app-header
        WebElement appheader = shadowdom.findElement(By.tagName("app-header"));
    }
}
