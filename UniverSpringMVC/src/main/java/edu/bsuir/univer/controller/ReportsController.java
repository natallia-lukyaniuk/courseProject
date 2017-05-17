package edu.bsuir.univer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.bsuir.univer.services.fact.ServicesFactory;
import edu.bsuir.univer.services.fact.ServicesFactoryImpl;

@RestController
public class ReportsController {
	ServicesFactory fact = new ServicesFactoryImpl();

//	@GetMapping("/reportGroupSubject")
//	public List<SubjectDescription> ToReportGroupSubject() throws DAOException {
//		List<SubjectDescription> subjects = fact.getSubjectDescriptionServices().getSubjectsWithAvailableReports();
//		return subjects;
//	}
}
