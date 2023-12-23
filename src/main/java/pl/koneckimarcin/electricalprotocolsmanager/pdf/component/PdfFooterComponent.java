package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TableProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TablePropertiesBuilder;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTableComponent;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTextService;

import java.io.IOException;

@Component
public class PdfFooterComponent {

    @Autowired
    private PdfTableComponent tableComponent;
    @Autowired
    private PdfTextService textService;

    private final int footerFontSize = 9;

    public void addFooter(PDDocument document, String documentNumber, Font font) {

        int pagesCount = document.getNumberOfPages();
        PDPageContentStream content;

        for (int i = 0; i < pagesCount; i++) {

            try {
                content = new PDPageContentStream(document, document.getPage(i),
                        PDPageContentStream.AppendMode.APPEND, false);

                TablePropertiesBuilder builder = new TablePropertiesBuilder();
                TableProperties properties = builder
                        .setContent(content)
                        .setCellHeight(1)
                        .setYPosition(50)
                        .setAlignment(Alignment.RIGHT)
                        .setFontType(font)
                        .build();

                addFooterLine(properties);
                addTextLine(content, documentNumber, i, pagesCount, font);

                content.close();
            } catch (IOException e) {
                System.out.println("Error when appending footer to pages. " + e.getMessage());
            }
        }
    }
    private void addFooterLine(TableProperties properties) {

        tableComponent.addTableComponentWithProperties(properties);
    }

    private void addTextLine(PDPageContentStream content, String documentNumber, int page, int pagesCount, Font font) {

        //create builder for text!!!
        textService.addSingleLineOfTextAlignment(content, createPageCounterText(documentNumber, page, pagesCount), 40,
                Alignment.RIGHT, font.getFontBold(), footerFontSize);
    }

    private String createPageCounterText(String documentNumber, int page, int pagesCount) {

        return documentNumber + " " + (page + 1) + "/" + pagesCount;
    }

}
