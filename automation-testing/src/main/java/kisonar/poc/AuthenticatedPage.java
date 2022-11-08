package kisonar.poc;

import kisonar.poc.abstraction.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthenticatedPage extends AbstractPage {

      @FindBy(how = How.ID_OR_NAME, id = "button-1144-btnInnerEl")
      WebElement userNameLabel;

      public AuthenticatedPage(WebDriver driver) {
            super(driver);
            initialize(this);
      }

      public String getUserValue() {
            return userNameLabel.getText();
      }
}
