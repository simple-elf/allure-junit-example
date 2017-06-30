package my.company.tests;

//import io.github.bonigarcia.wdm.ChromeDriverManager;
import my.company.steps.WebDriverSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
//import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.allure.annotations.Issue;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 28.10.13
 */
public class SearchTest {

    private WebDriverSteps steps;

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        //options.addArguments("--start-maximized");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        return options;
    }

    @Rule
    public BrowserWebDriverContainer chrome =
            new BrowserWebDriverContainer()
                    .withDesiredCapabilities(DesiredCapabilities.chrome());

    @Before
    public void setUp() {
        RemoteWebDriver driver = chrome.getWebDriver();
        steps = new WebDriverSteps(driver);
        //WebDriverRunner.setWebDriver(driver);
    }

    @After
    public void tearDown() {
        //WebDriverRunner.closeWebDriver();
    }


    //@Before
    public void setUp1() throws Exception {
        //ChromeDriverManager.getInstance().setup();

        WebDriver webDriver;

        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
        //capability.setCapability("version", "58.0");
        capability.setCapability("screenResolution", "1920x1080x24");
        //capability.setCapability("enableVNC", true);

        webDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capability);
        System.out.println("RemoteWebDriver started!!! ");

        steps = new WebDriverSteps(webDriver);
    }

    @Test
    @Issue("ISSUE-1")
    public void searchTest() throws Exception {
        steps.openMainPage();
        steps.search("Allure framework");
        Thread.sleep(30000);
        steps.makeScreenShot();
        steps.quit();
    }
}

