package edu.bsuir.univer.docs.pdf;

import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfCommon {

	public PdfPCell getCellStyleTableHeader(String columnName) {
		return getCell(columnName, FontFactory.getFont(FontFactory.TIMES_BOLD, 14), Element.ALIGN_LEFT,
				Element.ALIGN_MIDDLE);
	}

	public PdfPCell getCellLeftAlign(String data) {
		return getCell(data, FontFactory.getFont(FontFactory.TIMES, 14), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
	}

	public Paragraph getParagraph(String text, Font font, int position) {
		Paragraph p = new Paragraph(text, font);
		p.setAlignment(position);
		return p;
	}
	
	public Paragraph getParagraphSimpleCentr(String text){
		return getParagraph(text, FontFactory.getFont(FontFactory.TIMES, 14), Element.ALIGN_CENTER);
	}
	
	public Paragraph getParagraphBoldCentr(String text){
		return getParagraph(text, FontFactory.getFont(FontFactory.TIMES_BOLD, 14), Element.ALIGN_CENTER);
	}
	
	public Paragraph getParagraphSimpleLeft(String text){
		return getParagraph(text, FontFactory.getFont(FontFactory.TIMES, 14), Element.ALIGN_LEFT);
	}
	
	public Paragraph getParagraphUnderlinedLeft(String text){
		Font font = new Font(Font.FontFamily.TIMES_ROMAN , 14,
		          Font.UNDERLINE);
		return getParagraph(text, font, Element.ALIGN_LEFT);
	}

	public PdfPCell getCellRightAlign(String data) {
		return getCell(data, FontFactory.getFont(FontFactory.TIMES, 14), Element.ALIGN_RIGHT, Element.ALIGN_MIDDLE);
	}

	public void setAttributesPdf(Document document, String title) {
		document.addAuthor("Lukyaniuk Prischep Scherbinskaya");
		document.addCreationDate();
		document.addCreator("UniverProj");
		document.addTitle(title);
	}

	public PdfPTable createTable(int columnsCount, float[] columnWidths) throws DocumentException {
		PdfPTable table = new PdfPTable(columnsCount);
		table.setWidthPercentage(100);
		table.setSpacingBefore(30f);
		table.setSpacingAfter(10f);
		table.setWidths(columnWidths);
		return table;
	}

	private PdfPCell getCell(String data, Font font, int horizontalAlignment, int verticalAlignment) {
		PdfPCell cell = new PdfPCell(new Paragraph(data, font));
		cell.setPaddingLeft(10);
		cell.setHorizontalAlignment(horizontalAlignment);
		cell.setVerticalAlignment(verticalAlignment);
		return cell;
	}

	public PdfWriter initializePdf(Document document, String title, OutputStream out) throws DocumentException {
		setAttributesPdf(document, title);
		PdfWriter writer = PdfWriter.getInstance(document, out);
		writer.setPageEvent(new Background());
		writer.setEncryption("".getBytes(), "".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
		document.open();
		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
		Paragraph name = new Paragraph(title, fontTitle);
		document.add(name);
		return writer;
	}

}
