package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SavingsCalculatorPage {
    private final SelenideElement emailInput = $(byId("emailInput"));
    private final SelenideElement yearsInput = $(byId("yearsInput"));
    private final SelenideElement oneTimeInvestmentInput = $(byId("oneTimeInvestmentInput"));
    private final SelenideElement fundSelect = $(byId("fundSelect"));
    private final SelenideElement applyButton = $("button.btn-success");
    private final SelenideElement resultElement = $("div.result");
    private final SelenideElement mostRecentSavingsDetail = $("ul.saving-list").find("div.saving-detail");

    public SavingsCalculatorPage() {
        page(this);
    }

    public void enterEmail(String email) {
        emailInput.val(email).pressTab();
    }

    public void enterYears(int years) {
        yearsInput.val(String.valueOf(years));
    }

    public void enterOneTimeInvestment(String amount) {
        oneTimeInvestmentInput.val(amount);
    }

    public void selectFund(String fundToSelect) {
        fundSelect.shouldBe(visible).selectOption(fundToSelect);
    }

    public void applyForSaving() {
        applyButton.click();
    }

    public SelenideElement getCalculatedTotalIncomeElement() {
        return resultElement.find(By.xpath("./div[1]/p"));
    }

    public SelenideElement getCalculatedInterestIncomeElement() {
        return resultElement.find("div", 1).find("p");
    }

    public SelenideElement getCalculatedRiskElement() {
        return resultElement.find(byXpath("./div[3]/p"));
    }

    public SelenideElement getRecentRequestDetail() {
        return mostRecentSavingsDetail;
    }

    public SelenideElement getApplyButton() {
        return applyButton;
    }

    public SelenideElement getEmailInputWrapper() {
        return emailInput.parent();
    }
}