package com.lysenko.payments;

import com.lysenko.payments.model.entity.payment.Payment;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class CreatePDF {
    public void createPaymentPDF(Payment payment) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

            contentStream.setFont(PDType1Font.COURIER, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText(payment.toString());
            contentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.save("payment_"+payment.getId()+".pdf");
        document.close();
    }
}
