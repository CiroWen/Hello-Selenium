import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

public class WindowHandle {
    public static void main(String[] args) throws InterruptedException {
        //Open the Chrome browser
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Navigate to the URL
        String url = "https://demoqa.com/browser-windows";
        driver.get(url);

        //getWindowHandle()
        String parentWindowHandle = driver.getWindowHandle();
        System.out.println("the parent window handle is: " + parentWindowHandle);

        //click on new window button 3 times
        WebElement newWindowBtn = driver.findElement(By.xpath("//button[@id='windowButton']"));
        for (int i = 0; i < 1; i++) {
            newWindowBtn.click();
            Thread.sleep(800);
        }

        //getWindowHandles()
        Set<String> windowHandles = driver.getWindowHandles();
        //switch()
        //driver.switchTo().window(windowHandle);
        for(String hdl : windowHandles){
            System.out.println("current handle: " + hdl);
            System.out.println("handle before switch: " + driver.getWindowHandle());
            driver.switchTo().window(hdl);
            Thread.sleep(1000);
            System.out.println("handle after switch: " + driver.getWindowHandle());
            Thread.sleep(1000);
            driver.get("https://www.uniuni.com");
            driver.manage().window().maximize();
        }

        //close all the websites accordingly
        for(String hdl : windowHandles){
            driver.switchTo().window(hdl);
            Thread.sleep(1000);
            driver.close();
        }


        //closes all websites that generated by this automation test
        //while close() only close the initial website.
        Thread.sleep(5000);
        driver.quit();
    }
}
