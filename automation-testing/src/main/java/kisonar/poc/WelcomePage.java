package kisonar.poc;

import kisonar.poc.abstraction.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

public class WelcomePage extends AbstractPage {

      @FindBy(how = How.ID_OR_NAME, id = "nx-header-signin-1145-btnInnerEl")
      WebElement loginLink;

      public WelcomePage(WebDriver driver) {
            super(driver);
            initialize(this);
      }

      public void clickLoginLink() throws InterruptedException {
            loginLink.click();
            TimeUnit.SECONDS.sleep(2);
      }
}