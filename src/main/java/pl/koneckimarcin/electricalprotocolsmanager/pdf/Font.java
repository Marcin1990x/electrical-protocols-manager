package pl.koneckimarcin.electricalprotocolsmanager.pdf;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class Font {

    public static PDFont font = new PDType1Font(Standard14Fonts.FontName.COURIER);
}
