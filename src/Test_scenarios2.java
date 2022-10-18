import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * 1. Open the Edge browser
 * 2. Navigate to the URL (https://opensource-demo.orangehrmlive.com)
 * 3. Enter valid username
 * 4. Enter valid password
 * 5. Click on Login
 * 6. Verify URL (https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList)
 * 7. Verity Title (OrangeHRM)
 * 8. Close the browser
 */
public class Test_scenarios2 {

    public static void main(String[] args){
        //1. Open the Edge browser
        System.setProperty("webdriver.edge.driver", "E://Selenium//drivers//msedgedriver.exe"); //specifies the driver directory
        WebDriver driver = new EdgeDriver();

        //2. Navigate to the URL
        driver.get("https://opensource-demo.orangehrmlive.com/");

        //3. Maximize the window
        //driver.manage().window().maximize();

        //4. Enter valid username
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        //!!! has to wait due to slow responding !!!, otherwise NoSuchElementException
        usernameInput.sendKeys("Admin");
        //WebElement usernameInput = driver.findElement(By.className("oxd-input"));

        //5. Enter valid password
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordInput.sendKeys("admin123");

        //6. Click on login
        WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button")));
        loginBtn.click();

        //7. Verity redirected url
        String curUrl = driver.getCurrentUrl();
        String desiredUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";
        if(curUrl.equals(desiredUrl)){
            System.out.println("redirected url test passed");
        }else{
            System.out.println("redirected url test failed");
        }

        //8. Verify title
        String curTitle = driver.getTitle();
        String desiredTitle = "OrangeHRM";
        if(curTitle.equals(desiredTitle)){
            System.out.println("Title test passed");
        }else{
            System.out.println("Title test failed");
        }

        //9. Close the browser
        driver.close();
    }
}
