package edu.bsuir.univer.docs.pdf;

import java.io.OutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import edu.bsuir.univer.entity.Goods;
import edu.bsuir.univer.entity.ReportType;
import edu.bsuir.univer.entity.SubjectDescription;

public class GenerateReportTypetPDF {

	public void writePdfFile(ReportType report, OutputStream out) throws DocumentException {
		Document document = new Document(PageSize.A4);
		PdfWriter writer = null;
		try {
			PdfCommon common = new PdfCommon();
			writer = common.initializePdf(document, getTitle(report), out);
			final int columnsCount = 3;
			final float[] columnWidths = { 2f, 1f, 1f };
			PdfPTable table = common.createTable(columnsCount, columnWidths);

			String[] columnsName = new String[] { "Full name", "Mark", "Date" };
			for (int i = 0; i < columnsCount; i++)
				table.addCell(common.getCellStyleTableHeader(columnsName[i]));
			Format formatter = new SimpleDateFormat("dd.MM.yyyy");

			for (Mark mark : report.getMarks()) {
				table.addCell(common.getCellLeftAlign(mark.getStudent().toString()));
				if (mark.getMark() != 0) {
					table.addCell(common.getCellRightAlign(mark.getMark().toString()));
					table.addCell(common.getCellRightAlign(formatter.format(mark.getPassedDate())));
				} else {
					table.addCell(common.getCellRightAlign("-"));
					table.addCell(common.getCellRightAlign("-"));
				}

			}
			document.add(table);
		} finally {
			writer.close();
			document.close();
		}
	}

	private String getTitle(ReportType report) {
		return String.format("Type");
	}

}
