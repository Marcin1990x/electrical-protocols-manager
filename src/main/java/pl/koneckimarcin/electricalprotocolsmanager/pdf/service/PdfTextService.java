package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.measurement.data.TextData;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfTextService {

    private final int pageSize = 500;
    private final int xMargin = 50;

    public void addSingleLineOfTextAlignment(PDPageContentStream content, String text, int yPos,
                                             Alignment alignment, PDFont pdFont, float fontSize) throws IOException {
        content.beginText();
        content.setFont(pdFont, fontSize);
        content.newLineAtOffset(xMargin + calculateAlignmentPosition(alignment, text, pdFont, (int) fontSize), yPos);
        content.showText(text);
        content.endText();
        content.moveTo(0, 0);
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

    private int getTextWidth(String text, PDFont font, float fontSize) throws IOException {

        return (int) (font.getStringWidth(text) / 1000 * fontSize);
    }

    public List<Integer> calculateHeadersWidth(List<Object> headers, PDFont font, int fontSize) throws IOException {

        List<Integer> headersWidth = new ArrayList<>();

        for (Object header : headers) {
            headersWidth.add(getTextWidth(header.toString(), font, fontSize));
        }
        return headersWidth;
    }

    public List<Object> getHeadersForCalculation(String nameForCalculation) { // same method as below

        List<Object> headers;

        if (nameForCalculation.equals(TextData.measurementsMainNames.get(0))) {
            headers = TextData.protectionAgainstElectricShockByAutomaticShutdownEntryHeaders;
        } else if (nameForCalculation.equals(TextData.measurementsMainNames.get(1))) {
            headers = TextData.circuitInsulationResistanceTnsHeaders;
        } else if (nameForCalculation.equals(TextData.measurementsMainNames.get(2))) {
            headers = TextData.circuitInsulationResistanceTncHeaders;
        } else if (nameForCalculation.equals(TextData.measurementsMainNames.get(3))) {
            headers = TextData.residualCurrentProtectionHeaders;
        } else if (nameForCalculation.equals(TextData.measurementsMainNames.get(4))){
            headers =  TextData.soilResistanceHeaders;
        } else if (nameForCalculation.equals(TextData.measurementsMainNames.get(5))){
            headers =  TextData.continuityOfSmallResistanceHeaders;
        }
        else if (nameForCalculation.equals("ElectricianTable headers")) {
            headers = TextData.electricianPdfTableHeaders;
        } else {
            throw new IllegalArgumentException("No table headers created for this measurement name");
        }
        return headers;
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

    public List<Object> getMeasurementEntryTableHeaders(String measurementName) { // same method as above

        List<Object> tableHeaders;

        if (measurementName.equals(TextData.measurementsMainNames.get(0))) {
            tableHeaders = TextData.protectionAgainstElectricShockByAutomaticShutdownEntryHeaders;
        } else if (measurementName.equals(TextData.measurementsMainNames.get(1))) {
            tableHeaders = TextData.circuitInsulationResistanceTnsHeaders;
        } else if (measurementName.equals(TextData.measurementsMainNames.get(2))) {
            tableHeaders = TextData.circuitInsulationResistanceTncHeaders;
        } else if (measurementName.equals(TextData.measurementsMainNames.get(3))) {
            tableHeaders = TextData.residualCurrentProtectionHeaders;
        } else if (measurementName.equals(TextData.measurementsMainNames.get(4))){
            tableHeaders =  TextData.soilResistanceHeaders;
        } else if (measurementName.equals(TextData.measurementsMainNames.get(5))){
            tableHeaders =  TextData.continuityOfSmallResistanceHeaders;
        } else {
            throw new IllegalArgumentException("No entry table headers for this measurement main name.");
        }
        return tableHeaders;
    }
}
