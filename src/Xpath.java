import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 1. What is Xpath
 * XML Path, the address of the element using the HTML DOM structure.
 * !!!Note!!!: index of Xpath starts with 1 instead of 0.
 * !!!Note!!!: Absolute Xpath starts with one forward slash, while relative Xpath starts with two
 * !!!Note!!!: The property tag only applies to relative Xpath, //input[@id='user-id']. and the value of
 * the property has to be single-quoted.
 * 2. How to locate the element using the Xpath
 * 3. About ChroPath/Selector Hub Plugin
 * 4. Types of Xpath
 * Format:
 * * - Absolute Xpath: e.g. /html/body/form/input[3] or /html[1]/body[1]/form[1]/input[1]
 * * - Relative Xpath: e.g. //input[@id='email']
 * 5. Syntax of Xpath
 * 6. Difference between absolute and relative Xpath
 * 7. Out of absolute and relative Xpath which one is preferred and why?
 */
public class Xpath {
    public static void main(String[] args) {
        //Open the Chrome browser
        System.setProperty("webdriver.chrome.driver", "E://Selenium//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Navigate to the URL
        driver.get("https://www.ebay.com/");

        //Absolute Xpath
        //Enter "Dress" in search bar
        String ebaySearchFieldXpathAbs = "/html[1]/body[1]/header[1]/table[1]/tbody[1]/tr[1]/td[5]/form[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/input[1]";
        WebElement searchBar = driver.findElement(By.xpath(ebaySearchFieldXpathAbs));
        searchBar.sendKeys("Dress");

        //Click on Search button
        String ebaySearchBtnXpathAbs = "/html[1]/body[1]/header[1]/table[1]/tbody[1]/tr[1]/td[5]/form[1]/table[1]/tbody[1]/tr[1]/td[3]/input[1]";
        WebElement searchBtn = driver.findElement(By.xpath(ebaySearchBtnXpathAbs));
        searchBtn.click();

        //Relative Xpath
        //Enter "Shoes" in search bar
        String ebaySearchFieldXpathRel = "//input[@id='gh-ac']";
        WebElement searchBar2 = driver.findElement(By.xpath(ebaySearchFieldXpathRel));
        searchBar2.clear();
        //Clears the searchBar so the search bar value would not be "DressShoes"
        searchBar2.sendKeys("Shoes");

        //Click on Search button
        String ebaySearchBtnXpathRel = "//input[@id='gh-btn']";
        WebElement searchBtn2 = driver.findElement(By.xpath(ebaySearchBtnXpathRel));
        searchBtn2.click();





    }
}
