import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DropdownMenu {
    public static void main(String[] args) throws InterruptedException {
        //Open the Chrome browser
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Navigate to the URL
        String url = "https://demoqa.com/select-menu";
        driver.get(url);

        //Select class
        WebElement dropdown1 = driver.findElement(By.xpath(" //select[@id='oldSelectMenu']"));
        Select selectElement1 = new Select(dropdown1);

        //Prints all the options of the dropdown menu.
        List<WebElement> options1 = selectElement1.getOptions(); //.size()
        for (WebElement i : options1){
            System.out.println(i.getText());
        }

        //selectByIndex() //select the option using index, starting with 0.
        selectElement1.selectByIndex(1);

        //selectByValue() //select the option using its value attribute. e.g.<option value="2">Green</option>
        selectElement1.selectByValue("2");

        //selectByVisibleText() //select the option using its content.
        selectElement1.selectByVisibleText("Green");

        //Select class
        WebElement dropdown2 = driver.findElement(By.xpath("//select[@id='cars']"));
        Select selectElement2 = new Select(dropdown2);

        //Prints all the options of the multiple choices dropdown menu
        List<WebElement> options2 = selectElement2.getOptions();
        System.out.println("Multiple select menu options here:");
        for(WebElement i : options2){
            System.out.println(i.getText());
        }

        //isMultiple() //returns boolean, true if the select list allows to select more than one option
        Boolean isMultipleSel = selectElement2.isMultiple();
        System.out.println(isMultipleSel); //true
        //System.out.println(selectElement1.isMultiple()); //false

        selectElement2.selectByIndex(0);
        selectElement2.selectByValue("audi");
        selectElement2.selectByVisibleText("Opel");

        //deselect methods
        selectElement2.deselectByIndex(0);
        selectElement2.deselectByValue("audi");
        selectElement2.deselectByValue("Opel");
        //selectElement2.deselectAll();

    }
}
