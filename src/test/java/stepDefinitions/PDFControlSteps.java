package stepDefinitions;

import io.cucumber.java.en.Given;

import pages.PDFControlPage;


public class PDFControlSteps {

    PDFControlPage PDFControlPage = new PDFControlPage();

    @Given("User reads PDF files")
    public void pdfKontrol() {
        PDFControlPage.verifyContentInPDf();
    }
}
