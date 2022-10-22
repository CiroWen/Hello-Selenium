import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElementCommands {
    public static void main(String[] args) throws InterruptedException {
        //Open the Chrome browser
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Navigate to the URL
        String url = "https://opensource-demo.orangehrmlive.com/";
        driver.get(url);

        //Enter username and password
        Thread.sleep(2000);
        WebElement usernameInput = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement pswdInput = driver.findElement(By.xpath("//input[@name='password']"));

        //sendKeys() simulate typing value into an element
        usernameInput.sendKeys("Admin");
        pswdInput.sendKeys("admin123");
        Thread.sleep(2000);

        //clear() reset value of a form entry element.
        // it doesn't work for the website for some reasons, so we fake a select all and delete instead.
        inputClear(usernameInput);
        inputClear(pswdInput);

        driver.findElement(By.xpath("//button[normalize-space()='Login']")).submit();

        //getLocation()
        Point point = usernameInput.getLocation();
        System.out.println("X cordinate of username input: " + point.x);
        System.out.println("Y cordinate of username input: " + point.y);

        //getSize()
        Dimension dim = usernameInput.getSize();
        System.out.println("element height of username input: " + dim.height);
        System.out.println("element width of username input: " + dim.width);

        //isDisplayed() check if the element is displayed on the page
        Boolean isLoginDisplayed = driver.findElement(By.xpath("//input[@name='username']")).isDisplayed();
        System.out.println("is login input displayed? " + isLoginDisplayed );

        //isEnable() check if the element is enabled //can be selected, clicked on, typed into, or accept focus
        Boolean isLoginBtnEnabled = driver.findElement(By.xpath("//button[normalize-space()='Login']")).isEnabled();
        System.out.println("is login button enabled? " + isLoginBtnEnabled );

        //isSelected() check if the element is selected
        Boolean isLoginBtnSelected = driver.findElement(By.xpath("//button[normalize-space()='Login']")).isSelected();
        System.out.println("is login button selected? " + isLoginBtnSelected);




    }




    /**
     * sendkeys with control + a then delete.
     *
     * @param i WebElement
     */
    public static void inputClear(WebElement i) {
        i.sendKeys(Keys.CONTROL + "a");
        i.sendKeys(Keys.DELETE);
    }

}
