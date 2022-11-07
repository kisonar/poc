package kisonar.poc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled("Requires running Nexus")
public class SimpleWebCrawlerTest {

      private ChromeDriver driver;

      @BeforeEach
      public void setup() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
      }

      @Test
      public void login() throws InterruptedException {
            String correctUsername = "admin";
            String correctPassword = "Nexus2022";

            driver.get("http://localhost:8083");
            TimeUnit.SECONDS.sleep(3);

            WelcomePage welcomePage = new WelcomePage(driver);
            welcomePage.clickLoginLink();

            LoginPopup loginPopup = new LoginPopup(driver);
            loginPopup.enterCredentials(correctUsername, correctPassword);
            loginPopup.login();

            AuthenticatedPage authenticatedPage = new AuthenticatedPage(driver);
            assertEquals("admin", authenticatedPage.getUserValue());

            driver.close();
      }
}