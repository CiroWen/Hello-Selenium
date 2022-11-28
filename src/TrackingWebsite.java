import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.*;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.http.HttpRequest;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.List;

public class TrackingWebsite {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        // start the proxy
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start();
        //proxy.addHeaders(headers);
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.setHarCaptureTypes(CaptureType.RESPONSE_CONTENT);
        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        //options.setCapability(CapabilityType.PROXY, seleniumProxy);
        options.setProxy(seleniumProxy);
        options.setAcceptInsecureCerts(true);
        options.setExperimentalOption("useAutomationExtension", false);
        //options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        //options.setCapability (CapabilityType.ACCEPT_INSECURE_CERTS, true)
        //deprecated
        WebDriver driver = new ChromeDriver(options);

        //Navigate to the URL
        String url = "https://test.uniuni.com/";
        proxy.newHar("https://test.uniuni.com/");
        driver.get(url);
        WebElement orderInput = driver.findElement(By.xpath("//textarea[@id='input-track']"));
        WebElement trackBtn = driver.findElement(By.xpath("//button[@id='search-button']"));
        orderInput.sendKeys(
                "JY22BC400001030340," +
                        "BAUNI000300000351087");
        trackBtn.click();

        Har har = proxy.getHar();
        Thread.sleep(4000);
        System.out.println("after sleep");
        List<HarEntry> entries = har.getLog().getEntries();
        //System.out.println(har.getLog().getEntries().get(2).getResponse().getT);
        for (HarEntry harEntry : entries) {
            if (harEntry.getResponse().getContent().getText() != null && harEntry.getResponse().getContent().getText().startsWith("{")) {
                System.out.println(harEntry.getResponse().getContent().getText());
            }
            HarResponse response = harEntry.getResponse();
            HarRequest request = harEntry.getRequest();
            String tempUrl = harEntry.getRequest().getUrl();
            List<HarNameValuePair> headers = request.getHeaders();
            for (HarNameValuePair harp : headers) {
                System.out.println(harp.toString());
            }
        }
        //FileOutputStream fos = new FileOutputStream("G:\\HAR-Information.har");
        //har.writeTo(fos);
    }

    private static void verifyLinks(String url) throws IOException {
        try {
            HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(500);
            conn.connect();
            int statCode = conn.getResponseCode();
            String resMsg = conn.getResponseMessage();
            if (statCode >= 400) {
                System.out.println(url + " - is broken " + statCode + " " + resMsg);
            } else {
                System.out.println(url + " - is working " + statCode + " " + resMsg);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
