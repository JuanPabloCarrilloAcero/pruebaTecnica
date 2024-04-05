package com.pruebatecnica.util;

import com.pruebatecnica.model.Producto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;

public class PDFGenerator {

    public static void generatePDF(Producto[] products, String filePath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 4);
                contentStream.newLineAtOffset(100, 700);
                for (Producto product : products) {
                    contentStream.showText(product.toString());
                    contentStream.newLine();
                }
                contentStream.endText();
            }

            document.save(filePath);
        }
    }
}
