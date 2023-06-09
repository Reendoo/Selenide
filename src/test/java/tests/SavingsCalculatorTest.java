package tests;

import base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.SavingsCalculatorPage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class SavingsCalculatorTest extends TestBase {
    private SavingsCalculatorPage savingsCalculatorPage;

    @BeforeEach
    public void openPage() {
        open("/savingscalculator.php");
        savingsCalculatorPage = new SavingsCalculatorPage();
    }

    @Test
    public void itShouldEnableApplyButton() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");
        savingsCalculatorPage.getApplyButton().shouldBe(enabled);
    }

    @Test
    public void itShouldDisplayCalculatedAmounts() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");
        savingsCalculatorPage.getCalculatedTotalIncomeElement().shouldNotBe(empty).shouldHave(text("kr"));
        savingsCalculatorPage.getCalculatedInterestIncomeElement().shouldNotBe(empty).shouldHave(text("kr"));
    }

    @Test
    public void itShouldDisplayCalculatedRisk() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");
        savingsCalculatorPage.getCalculatedRiskElement().shouldNotBe(empty);
    }


    @Test
    public void itShouldContainFundNameInNewRequest() {
        String fundToSelect = "Hoggwart's Fund";
        savingsCalculatorPage.selectFund(fundToSelect);
        savingsCalculatorPage.enterOneTimeInvestment("25000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");
        savingsCalculatorPage.applyForSaving();
        savingsCalculatorPage
                .getRecentRequestDetail()
                .find("p.fund-description")
                .shouldHave(exactText(fundToSelect).because("DAROOOOOM!"));
    }

    @Test
    public void itShouldDisplayErrorMessageWhenEmailIsInvalid() {
        savingsCalculatorPage.enterEmail("invalid");
        savingsCalculatorPage.getEmailInputWrapper().shouldHave(cssClass("error"));
    }

    @Test
    public void itShouldHighlightNewRequestOnHover() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");
        savingsCalculatorPage.applyForSaving();

        $("div.saving-detail")
                .hover()
                .shouldHave(cssValue("background-color","rgba(4, 102, 156, 1)"));
    }

    @Test
    void itShouldEnterOneTimeInvestment() {
        $(byAttribute("placeholder", "One time investment")).sendKeys("26");
    }
}



