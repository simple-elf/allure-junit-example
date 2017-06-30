package my.company.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import my.company.steps.WebDriverSteps;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.allure.annotations.Issue;

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

    @Before
    public void setUp() throws Exception {
        //ChromeDriverManager.getInstance().setup();

        WebDriver webDriver;

        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
        capability.setCapability("screenResolution", "1920x1080x24");
        //capability.setCapability("enableVNC", "true");

        webDriver = new RemoteWebDriver(new URL("http://46.101.241.43:4444/wd/hub"), capability);
        System.out.println("RemoteWebDriver started!!! ");

        steps = new WebDriverSteps(webDriver);
    }

    @Test
    @Issue("ISSUE-1")
    public void searchTest() throws Exception {
        steps.openMainPage();
        steps.search("Allure framework");
        steps.makeScreenShot();
        steps.quit();
    }
}

