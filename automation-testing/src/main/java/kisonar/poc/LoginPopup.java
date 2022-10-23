package kisonar.poc;

import kisonar.poc.abstraction.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

public class LoginPopup extends AbstractPage {

      @FindBy(how = How.ID_OR_NAME, id = "textfield-1171-inputEl")
      WebElement userNameInput;
      @FindBy(how = How.ID_OR_NAME, id = "textfield-1172-inputEl")
      WebElement userPasswordInput;
      @FindBy(how = How.ID_OR_NAME, id = "button-1174-btnInnerEl")
      WebElement loginButton;

      public LoginPopup(WebDriver driver) {
            super(driver);
            initialize(this);
      }

      public void enterCredentials(String username, String password) throws InterruptedException {
            userNameInput.sendKeys(username);
            TimeUnit.SECONDS.sleep(2); // so that we can see typing in
            userPasswordInput.sendKeys(password);
            TimeUnit.SECONDS.sleep(2); // so that we can see typing in
      }

      public void login() throws InterruptedException {
            loginButton.click();
            TimeUnit.SECONDS.sleep(2); // page must reload
      }
}
