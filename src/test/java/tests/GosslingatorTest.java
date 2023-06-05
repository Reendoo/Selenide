package tests;

import base.TestBase;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith({TextReportExtension.class, ScreenShooterExtension.class})
public class GosslingatorTest extends TestBase {
    @BeforeEach
    public void openPage() {
        open("/gosslingator.php");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @Test
    public void itShouldDisplayTitle() {
        $(".ryan-title").shouldHave(text("GOSLINGATE ME"));
    }

    @Test
    public void itShouldAddOneRyan() {
        $(By.id("addRyan")).click();
        $(By.id("ryanCounter")).shouldHave(exactText("1"));
        System.out.println("Number of ryans: " + $("div.ryan-counter h2").getText());
        $("div.ryan-counter h3").shouldHave(exactText("ryan"));
    }

    @Test
    public void itShouldTwoRyans() {
        $(By.id("addRyan")).click();
        $(By.id("addRyan")).click();
        $(By.id("ryanCounter")).shouldHave(exactText("2"));
        $("div.ryan-counter h3").shouldHave(exactText("ryans"));
    }

    @Test
    public void itShouldDisplayWarningMessage() {
        addRyans(50);
        $("h1.tooManyRyans").shouldHave(Condition.exactText("""
                NUMBER OF
                RYANS
                IS TOO DAMN
                HIGH"""));
    }

    private static void addRyans(int count) {
        WebElement addRyanButton = $(By.id("addRyan"));
        for (int i = 0; i < count; i++) {
            addRyanButton.click();
        }
    }

    @Test
    public void itShouldDisplayNoRyanOnPageOpen() {
        $$("img").shouldHave(CollectionCondition.size(0));
    }

    @Disabled
    void itShouldRemoveRyanHeadByClickingInHisHead() {
        screenshot("beforeInsert");
        addRyans(30);
        screenshot("AfterInsert");
        $$("img").forEach(SelenideElement::click);
        $$("img").shouldHave(CollectionCondition.size(0));
        screenshot("afterDelete");
    }
}
