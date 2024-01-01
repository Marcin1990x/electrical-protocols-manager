package pl.koneckimarcin.electricalprotocolsmanager.pdf.service;

import org.springframework.stereotype.Service;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.component.builder.TableProperties;
import pl.koneckimarcin.electricalprotocolsmanager.pdf.table.Table;

import java.util.List;

@Service
public class PdfTableService {

    public void addTableSinglelineText(TableProperties properties) {

        Table table = new Table(properties);

        for (int i = 0; i < properties.getCellWidths().length; i++) {
            table.addCellWithProperties(properties.getTextData().get(i).toString(), properties);
        }
    }

    public void addTableMultilineText(TableProperties properties, List<List<String>> textData) {
        Table table = new Table(properties);

        for (int i = 0; i < properties.getCellWidths().length; i++) {
            table.addCellWithMultilineTextWithProperties(properties, textData.get(i));
        }
    }
}
