package utilities;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PDFGenerator {

    public static void pdfGeneratorWithTable( String fileName, String header, List<List<Object>> list, List<String> columnHeaderList,int numberOfUsers){

        Document document = new Document();
        String pdf_path = fileName;
        String pdf_title = header;

        try{
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_path));

            document.open();
            Paragraph paragraph = new Paragraph(pdf_title);
            paragraph.setAlignment(1);// 0 -> align left   1 -> center    2-> align right

            document.add(paragraph);
            //document.add(new Paragraph("                                     " + pdf_title).);

            PdfPTable table = new PdfPTable(columnHeaderList.size());
            table.setWidthPercentage(110);
            table.setSpacingBefore(25);
            table.setSpacingAfter(25);
            float [] colWidth = new float[columnHeaderList.size()];
            for (int i = 0; i < columnHeaderList.size(); i++) {
                colWidth[i]=2;
            }
            table.setWidths(colWidth);

            for(int i=0;i<columnHeaderList.size();i++) {
                PdfPCell cell = new PdfPCell(new Phrase(columnHeaderList.get(i)));
                table.addCell(cell);
            }

            table.setHeaderRows(numberOfUsers);

            for(int i=0;i<numberOfUsers;i++ ) {
                for (int j = 0; j < columnHeaderList.size(); j++) {
                    table.addCell(list.get(i).get(j).toString());
                }
            }

            document.add(table);

            document.close();

            writer.close();
        }

        catch(Exception e){
            e.printStackTrace();
        }

    }

}
