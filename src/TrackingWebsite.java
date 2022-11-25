import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TrackingWebsite {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Navigate to the URL
        String url = "https://www.uniuni.com/";
        driver.get(url);
        WebElement orderInput = driver.findElement(By.xpath("//textarea[@id='input-track']"));
        WebElement trackBtn = driver.findElement(By.xpath("//button[@id='search-button']"));
        orderInput.sendKeys(
                "JY22BC400001030340," +
                        "BAUNI000300000351087");
        trackBtn.click();

    }

    private static void verifyLinks(String url) throws IOException{
        try{
            HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(500);
            conn.connect();
            int statCode = conn.getResponseCode();
            String resMsg = conn.getResponseMessage();
            if(statCode >= 400){
                System.out.println(url + " - is broken " + statCode + " " + resMsg);
            }else{
                System.out.println(url + " - is working " + statCode + " " + resMsg);
            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
    }
}
