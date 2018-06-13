package app.util;

import app.dn.FileInfo;
import app.model.Ticket;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfBody;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Component
public class TicketDocsGenerator {

    @Value("${storage.path.ticket.docs}")
    private String appointmentDocsStoragePath;

    private Font cyrillicFont;

    @SneakyThrows
    public TicketDocsGenerator() {
        BaseFont bf = BaseFont.createFont("D:/fonts/FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        cyrillicFont = new Font(bf,14, Font.NORMAL);
    }

    public FileInfo generateTicketDoc(Ticket ticket) {
        FileInfo docFileInfo = generateDocFileInfo();
        writeDocToStorage(docFileInfo, ticket);
        return docFileInfo;
    }

    private FileInfo generateDocFileInfo() {
        String docStorageName = UUID.randomUUID().toString() + ".pdf";
        return FileInfo.builder()
                .storageFileName(docStorageName)
                .path(appointmentDocsStoragePath + "/" + docStorageName)
                .type("application/pdf")
                .build();
    }

    @SneakyThrows
    private void writeDocToStorage(FileInfo fileInfo, Ticket ticket) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream(fileInfo.getPath()));
        document.open();
        document.add(generateAppointmentHeader());
        document.add(generateAppointmentBody(ticket));
        document.add(generateAppointmentDescription(ticket));
        document.add(new Paragraph(""));
        document.add(generateAppointmentFooter(ticket));
        document.close();
    }

    private Paragraph generateAppointmentHeader() {
        Paragraph paragraph = new Paragraph("Заявка начальнику ПАТП", cyrillicFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        return paragraph;
    }

    private Paragraph generateAppointmentBody(Ticket ticket) {
        return new Paragraph("      Я, " + ticket.getName() + ", прошу предоставить заказной автобус на " + ticket.getDateString() +
                " для трансфера людей на " + ticket.getTheme() + ".", cyrillicFont);
    }
    private Paragraph generateAppointmentDescription(Ticket ticket) {
        if (ticket.getBody() != null && !ticket.getBody().equals("")) {
            return new Paragraph("      Также хотелось бы отметить дополнительную информацию: " + ticket.getBody(), cyrillicFont);
        }
        return new Paragraph("");
    }
    private Paragraph generateAppointmentFooter(Ticket ticket) {
        return new Paragraph("Заявитель: " + ticket.getName() + "________________", cyrillicFont);
    }
}
