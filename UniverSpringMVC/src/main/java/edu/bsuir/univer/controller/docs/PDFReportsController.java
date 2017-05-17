package edu.bsuir.univer.controller.docs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.DocumentException;

import edu.bsuir.univer.dao.DAOException;
import edu.bsuir.univer.entity.ReportType;
import edu.bsuir.univer.services.fact.ServicesFactory;
import edu.bsuir.univer.services.fact.ServicesFactoryImpl;

@Controller
public class PDFReportsController {

	@RequestMapping(value = "/downloadPDFReportGroupSubject/{id}", method = RequestMethod.GET)
	public void downloadGroupSubject(HttpServletResponse response, @PathVariable("type") String type) {
		try {
			ServicesFactory sf = new ServicesFactoryImpl();
			ReportType report = sf.getGoodsServices().getGoodsTypeReport(type);
			response.setContentType("application/pdf");
//			String reportName = String.format("PDF_Report_%s_%s.pdf", report.getSubjectDescription().getGroupNumber(),
//					report.getSubjectDescription().getSubject());
			String reportName = String.format("PDF_Report.pdf");
			response.setHeader("Content-disposition", "attachment;filename=" + reportName);
			try (ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream()) {
				new GenerateReportTypePDF().writePdfFile(report, byteArrayStream);
				response.getOutputStream().write(byteArrayStream.toByteArray());
				response.getOutputStream().flush();
			} catch (IOException e) {
				// TODO
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
			}
		} catch (DAOException e) {
			// TODO
		}

	}

}
