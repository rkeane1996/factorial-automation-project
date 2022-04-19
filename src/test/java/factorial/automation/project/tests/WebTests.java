package factorial.automation.project.tests;

import factorial.automation.project.model.Assertions;
import factorial.automation.project.pages.FactorialPage;
import factorial.automation.project.pages.PrivacyPage;
import factorial.automation.project.pages.TermsConditionsPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebTests {

    private WebDriver driver;
    private Assertions assertions;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        assertions = new Assertions();
        driver.navigate().to("http://qainterview.pythonanywhere.com/");
        this.wait = new WebDriverWait(driver,2);
    }

    @After
    public void tearDownDriver(){
        driver.quit();
    }

    @Test
    public void testFactorialIsReturnedWhenIntegerEntered()  {
        FactorialPage homePage = new FactorialPage(driver);
        homePage.enterNumber("2");
        homePage.clickCalculateButton();
        this.wait.until(ExpectedConditions.textToBePresentInElement(homePage.getResultsDiv(),"The factorial "));
        Assert.assertEquals(assertions.SUCCESSFULL_MESSAGE, homePage.getResult());
    }

    @Test
    public void testCorrectFactorialIsReturnedWhenIntegerAboveTwentyEntered() {
        FactorialPage homePage = new FactorialPage(driver);
        homePage.enterNumber("22");
        homePage.clickCalculateButton();
        this.wait.until(ExpectedConditions.textToBePresentInElement(homePage.getResultsDiv(),"The factorial "));
        Assert.assertEquals(assertions.SUCCESSFULL_MESSAGE22, homePage.getResult());
    }

    @Test
    public void testErrorMessageAppearWhenDecimalEntered() {
        FactorialPage homePage = new FactorialPage(driver);
        homePage.enterNumber("2.2");
        homePage.clickCalculateButton();
        Assert.assertEquals(assertions.UNSUCCESSFULL_MESSAGE, homePage.getResult());
    }

    @Test
    public void testTermsAndConditionLinkIsCorrect()  {
        FactorialPage homePage = new FactorialPage(driver);
        TermsConditionsPage termsConditionsPage = new TermsConditionsPage(driver);
        homePage.clickTCsLink();
        Assert.assertEquals(assertions.TERM_CONDITION_LINK, termsConditionsPage.getUrl());
    }

    @Test
    public void testPrivacyLinkIsCorrect()  {
        FactorialPage homePage = new FactorialPage(driver);
        PrivacyPage privacyPage = new PrivacyPage(driver);
        homePage.clickPrivacyLink();
        Assert.assertEquals(assertions.PRIVACY_LINK, privacyPage.getUrl());
    }

    @Test
    public void testTermsAndConditionPageTextIsCorrect() {
        FactorialPage homePage = new FactorialPage(driver);
        TermsConditionsPage termsConditionsPage = new TermsConditionsPage(driver);
        homePage.clickTCsLink();
        Assert.assertEquals(assertions.TERM_CONDITION_MESSAGE, termsConditionsPage.getText());
    }

    @Test
    public void testPrivacyPageTextIsCorrect() {
        FactorialPage homePage = new FactorialPage(driver);
        PrivacyPage privacyPage = new PrivacyPage(driver);
        homePage.clickPrivacyLink();
        Assert.assertEquals(assertions.PRIVACY_MESSAGE, privacyPage.getText());
    }

    @Test
    public void verifyPageTitleIsSpelledCorrectly() {
        FactorialPage homePage = new FactorialPage(driver);
        Assert.assertEquals(assertions.HOME_PAGE_TITLE, homePage.getTitle());
    }

    @Test
    public void verifyCorrectTextInCopyRightSection() {
        FactorialPage homePage = new FactorialPage(driver);
        Assert.assertEquals(assertions.COPYRIGHT_TEXT, homePage.copyrightYears());
    }
}
