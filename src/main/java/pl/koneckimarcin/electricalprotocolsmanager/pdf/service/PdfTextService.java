package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.protocolTextData.TextsPL;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextProperties;

import java.io.IOException;
import java.util.List;

@Service
public class PdfTextService {

    private final int pageSize = 500;
    private final int xMargin = 50;

    public void addSingleLineOfTextAlignment(PDPageContentStream content, String text, int yPos,
                                             Alignment alignment, PDFont pdFont, float fontSize) {
        try {
            content.beginText();
            content.setFont(pdFont, fontSize);
            content.newLineAtOffset(xMargin + calculateAlignmentPosition(alignment, text, pdFont, (int) fontSize), yPos);
            content.showText(text);
            content.endText();
            content.moveTo(0, 0);
        } catch (IOException e) {
            System.out.println("Error when adding text: " + text + "page. " + e.getMessage());
        }
    }
    public void addSingleLineOfTextAlignmentWithProperties(TextProperties properties) {
        try {
            PDPageContentStream content = properties.getContentStream();

            content.beginText();
            content.setFont(properties.getFontType(), properties.getFontSize());
            content.newLineAtOffset(xMargin + calculateAlignmentPosition(properties.getAlignment(), properties.getText()
                    , properties.getFontType(), properties.getFontSize()), properties.getyPosition());
            content.showText(properties.getText());
            content.endText();
            content.moveTo(0, 0);
        } catch (IOException e) {
            System.out.println("Error when adding text: " + properties.getText() + "page. " + e.getMessage());
        }
    }

    public int addMultipleLineOfTextAlignment(PDPageContentStream content, List<String> text, int yPos, Alignment alignment,
                                              float leading, PDFont pdFont, float fontSize) throws IOException {
        int yPosOffset = 0;
        content.beginText();
        content.setFont(pdFont, fontSize);
        content.setLeading(leading);
        content.newLineAtOffset(xMargin + calculateAlignmentPosition(alignment, text.get(0), pdFont, (int) fontSize), yPos);
        for (String textLine : text) {
            content.showText(textLine);
            content.newLine();
            yPosOffset -= fontSize;
        }
        content.endText();
        content.moveTo(0, 0);
        return yPosOffset;
    }
    public int addMultipleLineOfTextAlignmentWithProperties(TextProperties properties, List<String> text) {

        PDPageContentStream content = properties.getContentStream();
        int yPosOffset = 0;

        try {
            content.beginText();
            content.setFont(properties.getFontType(), properties.getFontSize());
            content.setLeading(properties.getLeading());
            content.newLineAtOffset(xMargin +
                            calculateAlignmentPosition(properties.getAlignment(), text.get(0), properties.getFontType(), properties.getFontSize()),
                    properties.getyPosition());
            for (String textLine : text) {
                content.showText(textLine);
                content.newLine();
                yPosOffset -= properties.getFontSize();
            }
            content.endText();
            content.moveTo(0, 0);

        } catch (IOException e) {
            System.out.println("Error when adding multiline text: " + text + " to page. " + e.getMessage());
        }
        return yPosOffset;
    }

    private int calculateAlignmentPosition(Alignment alignment, String text, PDFont font, int fontSize)
            throws IOException {

        int offset = 0;

        if (alignment == Alignment.CENTER) {
            offset = pageSize / 2 - getTextWidth(text, font, fontSize) / 2;
        } else if (alignment == Alignment.LEFT) {
            offset = 3;
        } else if (alignment == Alignment.RIGHT) {
            offset = pageSize - getTextWidth(text, font, fontSize);
        }
        return offset;
    }

    public List<Object> getMeasurementEntryTableHeaders(String measurementName) {

        List<Object> tableHeaders;

        if (measurementName.equals(TextsPL.measurementsMainNames.get(0))) {
            tableHeaders = TextsPL.protectionAgainstElectricShockByAutomaticShutdownEntryHeaders;
        } else if (measurementName.equals(TextsPL.measurementsMainNames.get(1))) {
            tableHeaders = TextsPL.circuitInsulationResistanceTnsHeaders;
        } else if (measurementName.equals(TextsPL.measurementsMainNames.get(2))) {
            tableHeaders = TextsPL.circuitInsulationResistanceTncHeaders;
        } else if (measurementName.equals(TextsPL.measurementsMainNames.get(3))) {
            tableHeaders = TextsPL.residualCurrentProtectionHeaders;
        } else if (measurementName.equals(TextsPL.measurementsMainNames.get(4))) {
            tableHeaders = TextsPL.soilResistanceHeaders;
        } else if (measurementName.equals(TextsPL.measurementsMainNames.get(5))) {
            tableHeaders = TextsPL.continuityOfSmallResistanceHeaders;
        } else {
            throw new IllegalArgumentException("No entry table headers for this measurement main name.");
        }
        return tableHeaders;
    }

    private int getTextWidth(String text, PDFont font, float fontSize) throws IOException {

        return (int) (font.getStringWidth(text) / 1000 * fontSize);
    }
}
