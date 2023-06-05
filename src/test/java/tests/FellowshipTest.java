package tests;

import java.util.ArrayList;
import java.util.List;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import base.TestBase;
import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class FellowshipTest extends TestBase {

    @BeforeEach
    public void openPage() {
        open("/fellowship.php");
    }

    @Test
    public void itShouldContainNameForEachFellow() {
        getFellowElements().forEach(fellowElement -> fellowElement.find("h1")
                .shouldNotBe(empty));
    }

    @Test
    public void itShouldContainSpecifiedFellows() {
        List<String> fellowNames = getFellowElements()
                .stream()
                .map(selenideElement -> selenideElement.find("h1").getText())
                .toList();
        assertTrue(fellowNames.contains("Gandalf"));
        assertTrue(fellowNames.contains("Aragorn"));
        assertTrue(fellowNames.contains("Frodo"));
    }

    @Test
    public void itShouldDisplayMessageComplete() {
        List<String> fellowsToSelect = new ArrayList<>();
        fellowsToSelect.add("Gandalf");
        fellowsToSelect.add("Aragorn");
        fellowsToSelect.add("Legolas");
        fellowsToSelect.add("Frodo");
        fellowsToSelect.forEach(this::selectFellow);
        $("div.points-left h3").shouldHave(exactText("complete"));
    }

    @Test
    public void itShouldDisplayPointsForEachFellow() {
        getFellowElements().forEach(displayedFellow -> displayedFellow.find("div.fellow-points h2")
                .shouldNotBe(empty));
    }

    @Test
    public void itShouldHighlightFellows() {
        List<String> fellowsToSelect = new ArrayList<>();
        fellowsToSelect.add("Gandalf");
        fellowsToSelect.add("Aragorn");
        fellowsToSelect.add("Legolas");
        fellowsToSelect.add("Frodo");
        fellowsToSelect.forEach(this::selectFellow);
        $("ul.list-of-fellows")
                .findAll("li > div")
                .filterBy(cssClass("active"))
                .shouldHave(textsInAnyOrder(fellowsToSelect));
    }

    private void selectFellow(String fellowName) {
        $(byText(fellowName)).click();
    }

    private ElementsCollection getFellowElements() {
        return $("ul.list-of-fellows").findAll("li");
    }
}
