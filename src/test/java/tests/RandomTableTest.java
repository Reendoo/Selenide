package tests;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import base.TestBase;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RandomTableTest extends TestBase {
    @BeforeEach
    public void openPage() {
        open("/tabulka.php");
    }

    @Test
    public void itShouldContainDataForEachRow() {
        for (WebElement tableRow : getRows()) {
            assertFalse(tableRow.getText().isEmpty());
        }
    }

    @Test
    public void itShouldContainNameForEachRow() {
        getRows().forEach(tableRow -> tableRow.find(By.xpath("./td[2]"))
                .shouldNotBe(empty));
    }

    @Test
    public void itShouldScrollToLastElement() {
        $("table > tbody > tr:last-child").scrollIntoView(false);
    }

    @Test
    void itShouldDisplaySecondRow() {
        System.out.println($(By.xpath("//table/tbody/tr[2]")).getText());
        System.out.println($("table > tbody > tr", 1).getText());
        printOnlyEmail();
    }

    private void printOnlyEmail() {
        System.out.println($(By.xpath("//table/tbody/tr[2]/td[4]")).getText());
        System.out.println($("table > tbody > tr", 1).find("td", 3).getText());
    }

    private ElementsCollection getRows() {
        return $$(By.cssSelector("table tbody tr"));
    }
}
