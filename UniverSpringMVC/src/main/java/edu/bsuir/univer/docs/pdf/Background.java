package edu.bsuir.univer.docs.pdf;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class Background extends PdfPageEventHelper {
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
		try {
			Image image = Image
					.getInstance(Thread.currentThread().getContextClassLoader().getResource("developers.png"));
			PdfContentByte canvas = writer.getDirectContentUnder();
			float x = PageSize.A4.getHeight();
			float y = PageSize.A4.getWidth();
			image.scaleAbsolute(y, x);
			image.setAbsolutePosition(0, 0);
			canvas.addImage(image);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    }
}
