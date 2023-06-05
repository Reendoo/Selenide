package tests;

import base.TestBase;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class SortingHatTest extends TestBase {
    @Test
    public void itShouldDisplayNameOfHouse() {
        open("/sortinghat.php");
        $("button").click();
        $("img.loading").should(appear).should(disappear);
        $("p.result").shouldBe(visible).shouldNotBe(empty);
    }

    @Test
    void itShouldDisplayGryffindor() {
        open("/sortinghat.php");
        String generatedHouse = "";
        while (!generatedHouse.equals("Gryffindor")) {
            $("button").shouldBe(enabled).click();
            $("img.loading").should(appear, Duration.ofSeconds(15)).should(disappear, Duration.ofSeconds(15));
            generatedHouse = $("p.result").shouldBe(visible).shouldNotBe(empty).getText();
        }
    }
}
