package com.carrito.util;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.carrito.dto.JasperData;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Component
public class JasperReportComponent {

	public void exportToExcel(JasperData data) throws Exception {

		InputStream reportStream = getClass().getResourceAsStream(data.getPathJrxml());
		JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data.getDataSource(),
				new JREmptyDataSource());

		JRXlsxExporter exporter = new JRXlsxExporter();

		SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
		// reportConfig.setSheetNames(new String[] { "Employee Data" });

		HttpServletResponse response = data.getResponse();
		response.addHeader("Content-Disposition", "attachment; filename=reporte.xls");

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		exporter.setConfiguration(reportConfig);
		exporter.exportReport();
	}

	public void exportToPdf(JasperData data) throws Exception {

		InputStream reportStream = getClass().getResourceAsStream(data.getPathJrxml());
		JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data.getDataSource(),
				new JREmptyDataSource());

		JRPdfExporter exporter = new JRPdfExporter();

		HttpServletResponse response = data.getResponse();
		response.addHeader("Content-Disposition", "attachment; filename=reporte.pdf");

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);

		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");

		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);

		exporter.exportReport();
	}
	
}
