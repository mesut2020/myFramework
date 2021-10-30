package utilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PDFClass {

    public static int getPageCount(PDDocument doc) {
        //get the total number of pages in the pdf document
        return doc.getNumberOfPages();
    }

    public static String getPdfContentWithUrl(String url) {

        String content = "";

        try {
        URL pdfUrl = new URL(url);
        InputStream in = pdfUrl.openStream();
        BufferedInputStream bf = new BufferedInputStream(in);
        PDDocument document = PDDocument.load(bf);

        int numberOfPages = getPageCount(document);
        System.out.println("The total number of pages: "+numberOfPages);

        content = new PDFTextStripper().getText(document);
        document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=================================================");
        return content;
    }

    public static String getPdfContentWithPath(String path) {

        String content = "";

        try {

            File file = new File(path); //Loading an existing document
            PDDocument document = PDDocument.load(file);

            int numberOfPages = getPageCount(document);
            System.out.println("The total number of pages: "+numberOfPages);

            PDFTextStripper pdfStripper = new PDFTextStripper(); //Instantiate PDFTextStripper class
            content = pdfStripper.getText(document); //Retrieving text from PDF document
            document.close(); //Closing the document
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=================================================");
        return content;
    }

}
