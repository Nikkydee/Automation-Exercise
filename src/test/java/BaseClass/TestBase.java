package BaseClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class TestBase {

    public String testUrl = "https://www.automationexercise.com/";
    public String username = "qat@mailinator.com";
    public String password = "123456";
    public ExtentReports extent;

    public static WebDriver driver;

    @BeforeClass
    public void setUp() {
        String browser = "chrome";

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setAcceptInsecureCerts(true);
                chromeOptions.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().clearDriverCache().setup();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                firefoxOptions.setAcceptInsecureCerts(true);
                firefoxOptions.addArguments("--remote-allow-origins=*");

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setAcceptInsecureCerts(true);

                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("The browser is not configured");
        }
        reporterSetup();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(testUrl);
    }
    public void sleep(int seconds) {
        try{
            Thread.sleep(seconds * 1000);
        }catch(InterruptedException e) {

        }
    }

    public void reporterSetup() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("src/Reports/test-reports.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("Automation Report");
        extent.setSystemInfo("Author", "Adenike Olapetan");
        extent.setSystemInfo("Platform", "Web");
        extent.setSystemInfo("OS version", System.getProperty("os.version"));
        extent.attachReporter(spark);
    }

    public WebDriverWait waitForElement(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            extent.flush();
            driver.quit();
        }
    }

}
