package tests;

import base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static utils.DataUtils.getExpectedSpells;

public class SpelleologyTest extends TestBase {

    @BeforeEach
    public void openPage() {
        open("/spelleology.php");
    }

    @Test
    public void itShouldContainSpells() throws FileNotFoundException {
        $("ul.spells")
                .findAll("li")
                .shouldHave(sizeGreaterThan(0), Duration.ofSeconds(10))
                .shouldHave(texts(getExpectedSpells()));
    }

    @Test
    public void itShouldDisplayTortureSpell() {
        $("ul.spells")
                .findAll("li")
                .shouldHave(sizeGreaterThan(0), Duration.ofSeconds(10))
                .find(exactText("tortures a person")).click();
        $("div.modal-container").shouldBe(appear, Duration.ofSeconds(10)).shouldHave(text("Crucio"));
    }

    @Test
    public void itShouldFilterSpells() {
        $("input").sendKeys("tortures a person");
        $$("ul.spells li").shouldHave(size(1), Duration.ofSeconds(10));
    }

    @Test
    void itShouldExcludeSpells() {
        $("ul.spells")
                .findAll("li")
                .shouldHave(sizeGreaterThan(1))
                .filterBy(matchText("^shoots.*"))
                .exclude(matchText("^opens.*"))
                .exclude(matchText("^enlarges.*"))
                .exclude(matchText("^open.*"))
                .forEach(selenideElement -> System.out.println(selenideElement.getText()));
    }
}
