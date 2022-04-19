package factorial.automation.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PrivacyPage {

    private WebDriver driver;

    public PrivacyPage(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body")
    private WebElement privacyPage;

    public String getText(){
        return this.privacyPage.getText();
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }
}

