import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class TrackingFunction {
    public static void main(String[] args) throws InterruptedException {
        // one delivering parcel with comma.
        int caseCtn = 1;
        String[] case1 = {"JY22B9400001033831,"};
        // one delivering parcel without comma.
        String[] case2 = {"JY22B9400001033831"};
        String[] case3 = {
                "JY22B9400001033831,", "JY22BC400001030832,", "JY22BC400001030143,", "JY22BC400001029116,",
                "JY22BC400001028980,", "JY22BC400001028934,", "JY22BC400001028622,", "JY22BC400001028411,",
                "JY22BC400001028104,", "JY22BC400001027715,",
                "JY22BC400001027189,", "JY22BC100003027543,", "JY22BC100004042172,", "JY22BA100003042818,",
                "JY22BB300004018037,", "JY22BD000004030887,", "JY22BB300004017397,", "JY22BB100003051862,",
                "JY22BC100003037636,", "JY22BC100003017547,",
                "JY22BD000004028577,", "JY22BC000004042294,", "JY22BC400001024829,", "JY22BB100002000464",
        };

        String[][] test = {case1, case2, case3};
        for (String[] i : test) {
            try {
                runTest(i, caseCtn);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("test " + caseCtn + " failed");
            }
            caseCtn++;
        }
    }

    /**
     * process the content in the clipboard
     *
     * @return content of the clipboard
     */
    public static String getClipboardString() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable trans = clipboard.getContents(null);
        if (trans != null) {
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                    return text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void runTest(String[] parcels, int ctn) throws InterruptedException {

        String url = "https://test.uniuni.com/";
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        //WebDriver driver = new EdgeDriver();
        WebDriver driver2 = new ChromeDriver();
        driver2.get(url);

        WebElement orderInput = driver2.findElement(By.xpath("//textarea[@id='input-track']"));
        WebElement trackBtn = driver2.findElement(By.xpath("//button[@id='search-button']"));

        for (String i : parcels) {
            orderInput.sendKeys(i);
        }
        trackBtn.click();

        // wait for the response
        Thread.sleep(10000);
        WebElement copyInfoBtn = driver2.findElement(By.xpath("//div[@onclick='copyReportToClipboard();']"));
        copyInfoBtn.click();
        String clippedText = getClipboardString();
        String[] result = clippedText.split("\n");
        int resultCtn = result.length - 2;
        //Number of result minus first line and header line equals to actual number of returned info.
        System.out.println(resultCtn);
        for (String i : result) {
            System.out.println(i);
        }
        System.out.println("Test " + ctn + " Passed, continue.");
    }
}
