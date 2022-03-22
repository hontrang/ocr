package utility;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class OCRUtility {
    public static String doOCR(String screenshot) {
        Tesseract tesseract = new Tesseract();
        String code = "";
        try {
            tesseract.setDatapath("tessdata");
            String text = tesseract.doOCR(new File(System.getProperty("user.dir") + File.separator + screenshot));
            code = text.replaceAll("[^0-9]", "");
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return code;
    }
}
