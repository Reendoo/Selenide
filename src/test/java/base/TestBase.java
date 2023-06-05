package base;
import com.codeborne.selenide.Configuration;
import java.awt.*;

public class TestBase {
    static {
        Configuration.baseUrl = "http://localhost:8888";
        Configuration.timeout = 20_000;
        Configuration.pollingInterval = 200;
        Configuration.headless = false;
//        Configuration.holdBrowserOpen = true;
//        Configuration.browser = Browsers.CHROME;
//        Configuration.remote = "fsdfdsfdsfds";
//        Configuration.clickViaJs = false;
//        Configuration.browserSize = screenSetUP();
//        Configuration.reportsUrl = "jenkins";
        Configuration.reportsFolder = "src/test/resources/failers";
        Configuration.downloadsFolder = "src/test/resources/failers/download/";
    }

    private static String screenSetUP() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        System.out.println(width + "x" + height);
        return (width + "x" + height);
    }
}
