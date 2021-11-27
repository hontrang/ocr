package utility;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class OCRUtility {
    public static String doOCR() {
        Tesseract tesseract = new Tesseract();
        String code = "";
        try {
            tesseract.setDatapath(System.getProperty("user.dir"));
            String text = tesseract.doOCR(new File(System.getProperty("user.dir") + File.separator + "Screenshot.png"));
            code = text.replaceAll("[^0-9]", "");
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return code;
    }
}
