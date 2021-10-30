package pages;

import org.testng.Assert;
import utilities.BasePage;
import utilities.PDFClass;

import java.util.*;

public class PDFControlPage extends BasePage {

    public static final String url = "http://www.stemolympiad.online/";
    public static final String telNumber = "+49 176 1234484";
    public static final String superVisorTelNumber = "+49 176 1000000";
    public static final String firstName = "Test";
    public static final String lastName = "EuroStudy";
    public static final String phoneNumberType = "Viber";
    public static final String phoneNumberType2 = "Telegram";
    public static final String countryName = "DE";
    public static final String cityName = "Test";
    public static final String schoolName = "Test";
    public static final String branceName = "Physics";
    public static final String branceName2 = "Mathe";
    public static final String Email = "adanaliburjuva11@gmail.com";
    public static final String Email2 = "adanaliburjuva011@gmail.com";
    public static final String passwort = "1q2w3e.";
    public static final String gender = "m";
    public static final String grade = "1";
    public static final String studentDateBirth = "11.10.2000";
    public static final String parentEmail = "asdffg@gmail.com";
    public static final String parentTel = "+49 176 1234488";
    public static final String superVisorEmail = "EuroStudy@gmail.com";
    public static final String superVisorSchoolName = "EuroStudy";
    public static final String superVisorSelect = "Physics";
    public static final String studenNummer = "DES1010";
    public static final String firstNameEdit = "Test1";
    public static final String lastNameEdit = "EuroStudys";
    public static final String studentPhoneEdit = "1234";
    public static final String schoolNameEdit = "Test1";
    public static final String mathMiniCategoryPdf = "http://www.stemolympiad.online/files/MATHEMATICS_MINI_CATEGORY_Grades_1-2-3.pdf";
    public static final String mathminiCategory = "MATHEMATICS - MINI CATEGORY";
    public static final String mathJuniorCategoryPdf = "http://www.stemolympiad.online/files/MATHEMATICS_JUNIOR_CATEGORY_Grades_4-5-6.pdf";
    public static final String mathjuniorCategory = "MATHEMATICS - JUNIOR CATEGORY";
    public static final String mathMiddleCategoryPdf = "http://www.stemolympiad.online/files/MATHEMATICS_MIDDLE_CATEGORY_Grades_7-8-9-1.pdf";
    public static final String mathmiddleCategory = "MATHEMATICS - MIDDLE CATEGORY";
    public static final String mathSeniorCategoryPdf = "http://www.stemolympiad.online/files/MATHEMATICS_SENIOR_CATEGORY_Grades_10-11-12.pdf";
    public static final String mathseniorCategory = "What is the value of a in the equation below?";
    public static final String compScienceJuniorCategoryPdf = "http://www.stemolympiad.online/files/COMPUTER_SCIENCE_JUNIOR_CATEGORY_Grades_4-5-6.pdf";
    public static final String compScienceJunior = "Look at the challenge above and find how many blocks are used to solve it?";
    public static final String compScienceMiddleategoryPdf = "http://www.stemolympiad.online/files/COMPUTER_SCIENCE_MIDDLE_CATEGORY_Grades_7-8-9-1.pdf";
    public static final String compScienceMiddle = "What type of switch connects computers to networks?";
    public static final String compScienceSeniorCategoryPdf = "http://www.stemolympiad.online/files/COMPUTER_SCIENCE_SENIOR_CATEGORY_Grades_10-11-12.pdf";
    public static final String compScienceSenior = "3. Based on the above description, identify the correct transmission type:";
    public static final String popularScienceMiniGroupsPdf = "http://www.stemolympiad.online/files/POPULAR_SCIENCE_MINI_CATEGORY_Grades_1-2-3.pdf";
    public static final String popularScienceMini = "Group the following foods as plant-based and animal-based foods:";
    public static final String popularScienceJuniorCategoryPdf = "http://www.stemolympiad.online/files/POPULAR_SCIENCE_JUNIOR_CATEGORY_Grades_4-5-6.pdf";
    public static final String popularScienceJunior = "You want to make a rainbow. To get the best result, you use:";
    public static final String popularScienceMiddleCategoryPdf = "http://www.stemolympiad.online/files/POPULAR_SCIENCE_MIDDLE_CATEGORY_Grades_7-8-9.pdf";
    public static final String popularScienceMiddle = "D) Regardless of where people stand, wagon will not move at all.";
    public static final String popularScienceSeniorCategoryPdf = "http://www.stemolympiad.online/files/POPULAR_SCIENCE_SENIOR_CATEGORY_Grades_10-11-12.pdf";
    public static final String popularScienceSenior = "A student reacts a piece of metal with an acid according to the following diagram.";

    public void verifyContentInPDf() {
        //specify the url of the pdf file

        List<String> pdfUrlList = new ArrayList<>(
                Arrays.asList(mathMiniCategoryPdf, mathJuniorCategoryPdf, mathMiddleCategoryPdf, mathSeniorCategoryPdf, compScienceJuniorCategoryPdf
                ,compScienceMiddleategoryPdf, compScienceSeniorCategoryPdf, popularScienceMiniGroupsPdf, popularScienceJuniorCategoryPdf
                        , popularScienceMiddleCategoryPdf, popularScienceSeniorCategoryPdf));
        List<String> pdfContentslList = new ArrayList<>(Arrays.asList(mathminiCategory, mathjuniorCategory, mathmiddleCategory, mathseniorCategory
                ,compScienceJunior, compScienceMiddle, compScienceSenior, popularScienceMini, popularScienceJunior, popularScienceMiddle, popularScienceSenior));

        for (int i = 0; i < pdfUrlList.size(); i++) {
            //open(pdfUrlList.get(i));
            String pdfContent = PDFClass.getPdfContentWithUrl(pdfUrlList.get(i));
            Assert.assertTrue(pdfContent.contains(pdfContentslList.get(i)));
        }

        PDFClass.getPdfContentWithPath("Answers_ofAssigments.pdf");

        /*
        //Loading an existing document
      File file = new File("D://Sample.pdf");
      PDDocument document = PDDocument.load(file);
      //Instantiate PDFTextStripper class
      PDFTextStripper pdfStripper = new PDFTextStripper();
      //Retrieving text from PDF document
      String text = pdfStripper.getText(document);
      System.out.println(text);
      //Closing the document
      document.close();
         */

    }

}

//        informationClass.verifyContentInPDf(mathMiniCategoryPdf, mathminiCategory);
//        informationClass.verifyContentInPDf(mathJuniorCategoryPdf, mathjuniorCategory);
//        informationClass.verifyContentInPDf(mathMiddleCategoryPdf, mathmiddleCategory);
//        informationClass.verifyContentInPDf(mathSeniorCategoryPdf, mathseniorCategory);
//        informationClass.verifyContentInPDf(compScienceJuniorCategoryPdf, compScienceJunior);
//        informationClass.verifyContentInPDf(compScienceMiddleategoryPdf, compScienceMiddle);
//        informationClass.verifyContentInPDf(compScienceSeniorCategoryPdf, compScienceSenior);
//        informationClass.verifyContentInPDf(popularScienceMiniGroupsPdf, popularScienceMini);
//        informationClass.verifyContentInPDf(popularScienceJuniorCategoryPdf, popularScienceJunior);
//        informationClass.verifyContentInPDf( popularScienceMiddleCategoryPdf, popularScienceMiddle);
//        informationClass.verifyContentInPDf(popularScienceSeniorCategoryPdf, popularScienceSenior);
