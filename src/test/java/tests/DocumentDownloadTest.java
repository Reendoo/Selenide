package tests;

import base.TestBase;
import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.FileDownloadMode;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DocumentDownloadTest extends TestBase {
    @Test
    void itShouldDownloadPDFFile() throws IOException {
        open("https://www.tatrabanka.sk/sk/o-banke/dolezite-dokumenty/obchodne-podmienky/");
        File file = $(byText("Všeobecné obchodné podmienky Tatra banky, a.s. pre klientov – spotrebiteľov")).download();
        printPDFToConsole(file);
    }

    private static void printPDFToConsole(File file) throws IOException {
        PDDocument document = PDDocument.load(file);
        if(!document.isEncrypted()){
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println(text);
        }
        document.close();
    }
}
