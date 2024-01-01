package pl.koneckimarcin.electricalprotocolsmanager.pdf.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.Font;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TableProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TablePropertiesBuilder;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.service.PdfTableService;

import java.io.IOException;
import java.util.List;

@Component
public class PdfHeadingComponent {

    private final int headingYposition = 750;
    private final int headingFontSize = 10;

    private PdfTableService tableComponent;

    public PdfHeadingComponent(PdfTableService tableComponent) {
        this.tableComponent = tableComponent;
    }

    //todo: extract font globally
    public void addHeadingToPages(PDDocument document, List<String> headingTextData, Font font) {

        int pagesQuantity = document.getNumberOfPages();

        PDPageContentStream content;

        try {
            for (int page = 1; page < pagesQuantity; page++) {

                content = new PDPageContentStream(document, document.getPage(page),
                        PDPageContentStream.AppendMode.APPEND, false);

                TableProperties tableProperties = new TablePropertiesBuilder()
                        .setContent(content)
                        .setCellHeight(60)
                        .setYPosition(headingYposition)
                        .setTextData(List.of(headingTextData))
                        .setFontType(font.getFont())
                        .setFontSize(headingFontSize)
                        .build();

                addHeaderToPage(tableProperties, headingTextData);

                content.close();
            }
        } catch (IOException e) {
            System.out.println("Error when appending header to page. " + e.getMessage());
        }
    }

    private void addHeaderToPage(TableProperties tableProperties, List<String> headingTextData) {

        tableComponent.addTableMultilineText(tableProperties, List.of(headingTextData));
    }
}
