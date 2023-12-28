package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Alignment;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TableProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TablePropertiesBuilder;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TextPropertiesBuilder;
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

    public void addFooterToPages(PDDocument document, String documentNumber, Font font) {

        int pagesQuantity = document.getNumberOfPages();
        PDPageContentStream content;

        for (int page = 0; page < pagesQuantity; page++) {

            try {
                content = new PDPageContentStream(document, document.getPage(page),
                        PDPageContentStream.AppendMode.APPEND, false);

                //todo : try to extract builders
                TableProperties tableProperties = new TablePropertiesBuilder()
                        .setContent(content)
                        .setCellHeight(1)
                        .setYPosition(50)
                        .setFontType(font.getFontBold())
                        .build();


                TextProperties textProperties = new TextPropertiesBuilder()
                        .setContent(content)
                        .setText(createPageCounterText(documentNumber, page, pagesQuantity))
                        .setAlignment(Alignment.RIGHT)
                        .setyPosition(40)
                        .setFontType(font.getFontBold())
                        .setFontSize(footerFontSize)
                        .build();

                addFooterToPage(tableProperties, textProperties);

                content.close();

            } catch (IOException e) {
                System.out.println("Error when appending footer to pages. " + e.getMessage());
            }
        }
    }

    private void addFooterToPage(TableProperties tableProperties, TextProperties textProperties) {

        addFooterLine(tableProperties);
        addTextLineNew(textProperties);
    }

    private void addFooterLine(TableProperties properties) {

        tableComponent.addTableComponentWithProperties(properties);
    }

    private void addTextLineNew(TextProperties properties) {

        textService.addSingleLineOfTextAlignmentWithProperties(properties);
    }

    private String createPageCounterText(String documentNumber, int page, int pagesQuantity) {

        return documentNumber + " " + (page + 1) + "/" + pagesQuantity;
    }

}
