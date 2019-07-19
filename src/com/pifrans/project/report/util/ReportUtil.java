package com.pifrans.project.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class ReportUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String UNDERLINE = "_";
	private static final String FOLDER_REPORTS = "/reports";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private static final String EXTENSION_ODS = "ods";
	private static final String EXTENSION_XLSX = "xlsx";
	private static final String EXTENSION_HTML = "html";
	private static final String EXTENSION_PDF = "pdf";
	private String SEPARATOR = File.separator;
	private static final int REPORT_PDF = 1;
	private static final int REPORT_XLSX = 2;
	private static final int REPORT_HTML = 3;
	private static final int REPORT_ODS = 4;
	private static final String DOT = ".";
	private StreamedContent fileReturn = null;
	private String pathFileReport = null;
	private JRExporter typeFileExport = null;
	private String extensionFileExport = "";
	private File fileGenerated = null;
	private String pathSubreportDir = "";

	public StreamedContent generateReport(List<?> listDataBeanCollectionReport, HashMap parametersReport,
			String nameReportIn, String nameReportOut, int typeReport) throws Exception {
		/*
		 * Cria a lista de collectionDataSource de beans que carregam os dados para o
		 * relatório
		 */
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(
				listDataBeanCollectionReport);

		/*
		 * Fornece o caminho físico até a pasta que contém os relatórios compilados
		 * .jasper
		 */
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();
		ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
		String pathReport = servletContext.getRealPath(FOLDER_REPORTS);
		/* Ex: C:/Aplicação/relatorios/rel_clientes.jasper */
		File file = new File(pathReport + SEPARATOR + nameReportIn + DOT + "jasper");

		if (pathReport == null || pathReport != null && pathReport.isEmpty() || !file.exists()) {
			pathReport = this.getClass().getResource(FOLDER_REPORTS).getPath();
			SEPARATOR = "";
		}

		/* Caminho para imgens */
		parametersReport.put("REPORT_PARAMETERS_IMAGE", pathReport);

		/* Caminho completo até o relatório compilado indicado */
		String pathFileJasper = pathReport + SEPARATOR + nameReportIn + DOT + "jasper";

		/* Faz o carregamento do relatório indicado */
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(pathFileJasper);

		/* Seta parâmetro SUBREPORT_DIR como caminho físico para sub-reports */
		pathSubreportDir = pathReport + SEPARATOR;
		parametersReport.put(SUBREPORT_DIR, pathSubreportDir);

		/* Carrega o arquivo compilado para a memória */
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersReport,
				jrBeanCollectionDataSource);

		switch (typeReport) {
		case REPORT_PDF:
			typeFileExport = new JRPdfExporter();
			extensionFileExport = EXTENSION_PDF;
			break;
		case REPORT_HTML:
			typeFileExport = new JRHtmlExporter();
			extensionFileExport = EXTENSION_HTML;
			break;
		case REPORT_XLSX:
			typeFileExport = new JRXlsxExporter();
			extensionFileExport = EXTENSION_XLSX;
			break;
		case REPORT_ODS:
			typeFileExport = new JROdtExporter();
			extensionFileExport = EXTENSION_ODS;
			break;
		default:
			typeFileExport = new JRPdfExporter();
			extensionFileExport = EXTENSION_PDF;
			break;
		}

		nameReportOut += UNDERLINE + DateUtil.getDateCurrentReportName();

		/* Caminho do relatório exportado */
		pathFileReport = pathReport + SEPARATOR + nameReportOut + DOT + extensionFileExport;

		/* Cria novo file exportado */
		fileGenerated = new File(pathFileReport);

		/* Prepara a impressão do relatório */
		typeFileExport.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		/* Nome do arquivo físico a ser impresso/exportado */
		typeFileExport.setParameter(JRExporterParameter.OUTPUT_FILE, fileGenerated);

		/* Executa a exportação */
		typeFileExport.exportReport();

		/* Remove o arquivo do servidor após ser feito o download pelo usuário */
		fileGenerated.deleteOnExit();

		/* Cria o inputstream para ser usado pelo Primefaces */
		InputStream inputStream = new FileInputStream(fileGenerated);

		/* Faz o retorno para a aplicação */
		fileReturn = new DefaultStreamedContent(inputStream, "application/" + extensionFileExport,
				nameReportOut + DOT + extensionFileExport);

		return fileReturn;
	}
}
