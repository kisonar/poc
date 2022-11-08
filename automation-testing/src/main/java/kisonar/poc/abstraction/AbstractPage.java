package kisonar.poc.abstraction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {

      private final WebDriver driver;

      public AbstractPage(WebDriver driver) {
            this.driver = driver;
      }

      public void initialize(Object page) {
            PageFactory.initElements(driver, page);
      }
}