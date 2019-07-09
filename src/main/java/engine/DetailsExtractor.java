package engine;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.Constants.REGEX_PDF_EXTRACTION;

/**
 * @author Vivek
 * @version 1.0
 * @since 08-07-2019
 */
public class DetailsExtractor {

    private static final Logger logger = LoggerFactory.getLogger(DetailsExtractor.class);

    public static List<String> convertPdfToStringList(String filePath) {
        List<String> extractedPdf = new ArrayList<>();

        try (PDDocument doc = PDDocument.load(new File(filePath))) {
            if (!doc.isEncrypted()) {
                final PDFTextStripper pdfTextStripper = new PDFTextStripper();
                final String entireText = pdfTextStripper.getText(doc);
                doc.close();
                logger.debug("entireText : {}", entireText);
                extractedPdf = Arrays.asList(entireText.split(REGEX_PDF_EXTRACTION));
            } else {
                logger.warn("The pdf '[{}]' is encrypted, thus cannot parse it...", filePath);
            }
        } catch (IOException e) {
            logger.error("Error file trying to read the pdf '[{}]' => ", filePath, e);
        }
        logger.info("Extracted {} lines from the pdf '[{}]'", extractedPdf.size(), filePath);
        return extractedPdf;
    }
}
