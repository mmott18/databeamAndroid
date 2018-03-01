package databeam.databeamui;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfReadExample {

    private static final String FILE_NAME = "/tmp/itext.pdf";

    public static void main(String[] args) {

        PdfReader reader;

        try {

            reader = new PdfReader("f:/itext.pdf");

            // pageNumber = 1
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);

            System.out.println(textFromPage);

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
