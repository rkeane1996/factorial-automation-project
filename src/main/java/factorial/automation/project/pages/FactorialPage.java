package factorial.automation.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class FactorialPage {
    private WebDriver driver;

    public FactorialPage(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "getFactorial")
    private WebElement calculateFactorialBtn;

    @FindBy(id = "number")
    private WebElement numberTextBox;

    @FindBy(id = "resultDiv")
    private WebElement resultsDiv;

    @FindBy(xpath = "/html/body/div[2]/div/p[1]/a[1]")
    private WebElement termsAndConditionsLink;

    @FindBy(xpath = "/html/body/div[2]/div/p[1]/a[2]")
    private WebElement privacyLink;

    public void clickCalculateButton(){
        this.calculateFactorialBtn.click();
    }

    public void enterNumber(String number){
        this.numberTextBox.sendKeys(number);
    }

    public String getResult() {
        return this.resultsDiv.getText();
    }

    public WebElement getResultsDiv(){
        return this.resultsDiv;
    }

    public void clickTCsLink(){
        this.termsAndConditionsLink.click();
    }

    public void clickPrivacyLink(){
        this.privacyLink.click();
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
