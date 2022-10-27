import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinkStatusChecker {
    public static void main(String[] args) throws IOException {
        //Open the Chrome browser
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Navigate to the URL
        String url = "https://www.uniuni.com/";
        driver.get(url);

        //Links on home tag
        List<WebElement> links = driver.findElements(By.tagName("a"));

        //create a set of all links on the home page.
        Set<String> noDupLinks = new HashSet<>();
        for(WebElement el : links){
            noDupLinks.add(el.getAttribute("href"));
        }
        for(String el : noDupLinks){
            verifyLinks(el);
        }

    }

    //method that verifies links
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
