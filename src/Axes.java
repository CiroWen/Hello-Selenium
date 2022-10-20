import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * 1. What are axes
 * an axis represents a relationship to the context node,and is used to locate nodes relative to that node on the tree.
 * 13 types in total.
 * 2. Syntax of axes
 * //tagname/axes::target_element
 * e.g. //a[@id='username']/self::a
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
        System.out.println(selfNode); //Mazda Ltd.
        String mazdaNode = driver.findElement(By.xpath("//a[contains(.,'Mazda Ltd.')]")).getText();
        System.out.println(mazdaNode); //Mazda Ltd.
        System.out.println(mazdaNode.equals(selfNode)); //true

        //Parent node
        String parentNode = driver.findElement(By.xpath("//a[contains(.,'Mazda Ltd.')]/parent::td")).getText();
        System.out.println(parentNode); //Mazda Ltd.: because there is no inline content in its parent, thus the content of itself is returned

        //Ancestor node
        String ancestorNode = driver.findElement(By.xpath("//a[contains(.,'Mazda Ltd.')]/ancestor::tr")).getText();
        System.out.println(ancestorNode); //ancestor tr itself doesn't have content, while its 5 children do

        //Child node
        List<WebElement> childNodes = driver.findElements(By.xpath("//a[contains(.,'Mazda Ltd.')]/ancestor::tr/child::*"));
        System.out.println("the number of child node is: " + childNodes.size());

        //Following node
        List<WebElement> followingTags = driver.findElements(By.xpath("//a[contains(.,'Mazda Ltd.')]/following::*"));
        System.out.println("the number of following elements is: " + followingTags.size()); //12580,
        // all the nodes that appear after the context node, except any descendant, attribute, and namespace nodes.

        //Following-siblings node
        List<WebElement> followingSibTags = driver.findElements(By.xpath("//a[contains(.,'Mazda Ltd.')]/parent::td/following-sibling::*"));
        System.out.println("the number of following-Siblings is: " + followingSibTags.size());
        for (WebElement i : followingSibTags){
            System.out.println("<" + i.getTagName() + ">"+ i.getText() + "</" + i.getTagName() + ">");
        }

        //Preceding node
        List<WebElement> precedingTags = driver.findElements(By.xpath("//a[contains(.,'Mazda Ltd.')]/preceding::*"));
        System.out.println("the number of preceding elements is: " + precedingTags.size()); //296,
        // all the nodes that appear before the context node, except any descendant, attribute, and namespace nodes.

        //Preceding-siblings node
        List<WebElement> precedingSibTags = driver.findElements(By.xpath("//a[contains(.,'Mazda Ltd.')]/ancestor::tr/preceding-sibling::*"));
        System.out.println("the number of preceding-Siblings is: " + precedingSibTags.size());
        for (WebElement i : precedingSibTags){
            System.out.println("<" + i.getTagName() + ">"+ i.getText() + "</" + i.getTagName() + ">");
        }

    }


}
