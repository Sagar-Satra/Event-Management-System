package com.eventmanagement.eventapp.services;

import com.eventmanagement.eventapp.models.Address;
import com.eventmanagement.eventapp.models.Event;
import com.eventmanagement.eventapp.models.User;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.awt.*;
import java.util.Map;

public class PdfService extends AbstractPdfView {
    Event event;
    User user;
    public PdfService(Event event , User user){
        this.event = event;
        this.user = user;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Define fonts
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.NORMAL, new Color(0, 102, 204));
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.NORMAL, new Color(253, 0, 0));
        Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 0, 0));

        PdfContentByte canvas = pdfWriter.getDirectContent();
        canvas.setLineWidth(2);
        canvas.setColorStroke(new Color(0, 0, 0)); // Black border

        // border around the document
        float margin = 25;
        float xStart = margin;
        float yStart = document.getPageSize().getHeight() - margin;
        float xEnd = document.getPageSize().getWidth() - margin;
        float yEnd = margin;

        // rectangle border coordinates
        canvas.rectangle(xStart, yEnd, xEnd - xStart, yStart - yEnd);
        canvas.stroke();

        // event title
        Paragraph title = new Paragraph(event.getTitle(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        Paragraph userDetails = new Paragraph("Participant Name: " + user.getFirstName() + " " + user.getLastName() , headerFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(userDetails);
        document.add(Chunk.NEWLINE);

        // event description
        Paragraph description = new Paragraph("Description: " + event.getDescription(), contentFont);
        description.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(description);
        document.add(Chunk.NEWLINE);

        // location details
        Address location = event.getLocation();
        Paragraph locationDetails = new Paragraph("Location: " + location.getStreet() + ", " + location.getCity(), contentFont);
        document.add(locationDetails);
        document.add(Chunk.NEWLINE);

        // event date and time
        Paragraph date = new Paragraph("Date: " + event.getDate().toString(), contentFont);
        document.add(date);
        document.add(Chunk.NEWLINE);

        Paragraph startTime = new Paragraph("Start Time: " + event.getStartTime().toString(), contentFont);
        document.add(startTime);
        document.add(Chunk.NEWLINE);

        Paragraph endTime = new Paragraph("End Time: " + event.getEndTime().toString(), contentFont);
        document.add(endTime);
        document.add(Chunk.NEWLINE);

        // organizer details
        Paragraph organizer = new Paragraph("Organizer: " + event.getOrganizer().getFirstName() + " " + event.getOrganizer().getLastName(), contentFont);
        document.add(organizer);
        document.add(Chunk.NEWLINE);

        // Closing message
        Paragraph footer = new Paragraph("Thank you for registering!", contentFont);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);

        document.close();
    }

}
