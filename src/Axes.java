import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 1. What are axes
 * an axis represents a relationship to the context node,and is used to locate nodes relative to that node on the tree.
 * 13 types in total.
 * 2. Syntax of axes
 * //tagname/axes::target_element
 * e.g. //a[@id='username']/self::a
 *
 */
public class Axes {
    public static void main(String[] args) {
        //Open the Chrome browser
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Navigate to the URL
        driver.get("https://money.rediff.com/gainers");

        //Xpath axes
        //Self node
        String selfNode = driver.findElement(By.xpath("//a[contains(.,'Mazda Ltd.')]/self::a")).getText();
        System.out.println(selfNode);
        String mazdaNode = driver.findElement(By.xpath("//a[contains(.,'Mazda Ltd.')]")).getText();
        System.out.println(mazdaNode);
        System.out.println(mazdaNode.equals(selfNode));

        //Parent node
        String parentNode = driver.findElement(By.xpath("//a[contains(.,'Mazda Ltd.')]/parent::td")).getText();
        System.out.println(parentNode);
    }



}
