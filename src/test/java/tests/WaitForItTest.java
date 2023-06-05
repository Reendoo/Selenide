package tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import base.TestBase;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
public class WaitForItTest extends TestBase {

    @BeforeEach
    public void openPage() {
        open("/waitforit.php");
    }

    @Test
    public void waitForValue() {
        String expectedText = "dary !!!";
        $(byId("startWaitForText")).click();
        $(byId("waitForTextInput")).shouldHave(value(expectedText), Duration.ofSeconds(5));
    }

    @Test
    public void waitForClass() {
        $(byId("startWaitForProperty")).click();
        $(byId("waitForProperty")).shouldHave(cssClass("error"));
    }

    @Test
    public void itShouldDisplayResponseTimeMessage() {
        $(byId("startWaitForText")).click();
        $("div.current-wait-time").shouldHave(text("Response time"), Duration.ofSeconds(10));
    }

    @Test
    public void itShouldDisplayResponseTimeMessageSelenide() {
        $(byId("startWaitForText")).click();
        $("div.current-wait-time").shouldHave(text("Response time was"), Duration.ofSeconds(10));
    }
}
