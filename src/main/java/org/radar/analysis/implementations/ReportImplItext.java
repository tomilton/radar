/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.radar.analysis.implementations;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.radar.analysis.annotations.Rationale;
import org.radar.analysis.dtos.RationaleDTO;
import org.radar.analysis.interfaces.Report;
import org.radar.analysis.models.Annotation;
import org.radar.utilities.Constants;
import org.radar.utilities.Utilities;

public class ReportImplItext implements Report {

    private final FontFamily DEFAULT_FONT_FAMILY = Font.FontFamily.COURIER;
    private final int DEFAULT_FONT_SIZE = 14;

    /**
     * @param vector is an array to Objects for convert to Strings
     * @return String[] converted vector from Object to String
     */
    private String[] toStringVector(Object[] vector) {
        String[] vectorString = new String[vector.length];
        for (int i = 0; i < vector.length; i++) {
            vectorString[i] = vector[i].toString();
        }
        return vectorString;
    }

    /**
     * @param label is the labe of the @Rationale attribute
     * @param values is the values vector from @Rationale annotation
     * @param table is the reference to the table of the report
     */
    private void addValueToTable(String label, String[] values, PdfPTable table) {
        addLabelToTable(label, table);
        for (String value : values) {
            table.addCell("-" + value);
        }
    }

    /**
     * @param label is the attribute label to show
     * @param table is the table for add a cell with attribute label
     */
    private void addLabelToTable(String label, PdfPTable table) {
        Paragraph paragraph = new Paragraph(label, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE));
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    /**
     * @param document is the Itext Document to generate
     * @param information is the Information about a @Rationale
     * @param ratinale is the vale of the @Rationale annotation
     * @param cont is the number of @Rationale in the loop
     */
    private void addInformation(Document document, Annotation information, Rationale rationale, int cont) {
        try {
            document.add(new Paragraph("\n"));
            PdfPTable table = new PdfPTable(4);
            table.setWidths(new int[]{1, 1, 1, 1});
            table.addCell(new Paragraph(Constants.LABEL_RATIONALE, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE)));
            table.addCell("" + cont);
            table.addCell(new Paragraph(Constants.LABEL_IDENTIFIER, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE)));
            table.addCell(rationale.id());
            table.addCell(new Paragraph(Constants.LABEL_TYPE, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE)));
            table.addCell(information.getType());
            table.addCell(new Paragraph(Constants.LABEL_NAME, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE)));
            table.addCell(information.getName());
            PdfPCell cell;
            cell = new PdfPCell(new Paragraph(Constants.LABEL_PATH, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE)));
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph(information.getPath()));
            cell.setColspan(3);
            table.addCell(cell);
            table.setWidthPercentage(100);
            document.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportImplItext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param document is the Itext Document to generate
     * @param rationale is the value of the @Rationale annotation
     */
    private void addRationale(Document document, Rationale rationale) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        if (rationale.context().length > 0) {
            addValueToTable(Constants.LABEL_CONTEXT, toStringVector(rationale.context()), table);
        }
        if (rationale.justification().length > 0) {
            addValueToTable(Constants.LABEL_JUSTIFICATION, toStringVector(rationale.justification()), table);
        }
        if (rationale.consequence().length > 0) {
            addValueToTable(Constants.LABEL_CONSEQUENCE, toStringVector(rationale.consequence()), table);
        }
        if (rationale.alternative().length > 0) {
            addValueToTable(Constants.LABEL_ALTERNATIVE, toStringVector(rationale.alternative()), table);
        }
        if (rationale.decision().length > 0) {
            addValueToTable(Constants.LABEL_DECISION, toStringVector(rationale.decision()), table);
        }
        if (rationale.pattern().length > 0) {
            addValueToTable(Constants.LABEL_PATTERN, toStringVector(rationale.pattern()), table);
        }
        if (rationale.tactic().length > 0) {
            addValueToTable(Constants.LABEL_TACTIC, toStringVector(rationale.tactic()), table);
        }
        if (rationale.strategy().length > 0) {
            addValueToTable(Constants.LABEL_STRATEGY, toStringVector(rationale.strategy()), table);
        }
        try {
            document.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportImplItext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param document is the Itext Document to generate
     */
    private void addHeader(Document document) {
        try {
            PdfPTable table = new PdfPTable(3);
            table.setWidths(new int[]{1, 2, 3});
            PdfPCell cell;
            cell = new PdfPCell(new Paragraph(Constants.TITLE, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE)));
            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(Image.getInstance(Constants.RATIONALE_REPORT_IMAGE), true);
            cell.setRowspan(10);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(2);
            table.addCell(cell);
            table.addCell(new Paragraph(Constants.LABEL_ORGANIZATION, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE)));
            table.addCell(Constants.ORGANIZATION);
            table.addCell(new Paragraph(Constants.LABEL_DESCRIPTION, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE)));
            table.addCell(Constants.DESCRIPTION);
            table.addCell(new Paragraph(Constants.LABEL_VERSION, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE)));
            table.addCell(Constants.VERSION);
            table.addCell(new Paragraph(Constants.LABEL_AUTHOR, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE)));
            table.addCell(Constants.AUTHOR);
            table.addCell(new Paragraph(Constants.LABEL_CURRENT_DATE, new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_SIZE)));
            table.addCell(Constants.CURRENT_DATE);
            table.setWidthPercentage(100);
            document.add(table);
        } catch (BadElementException ex) {
            Logger.getLogger(ReportImplItext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(ReportImplItext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param information is the Information about a @Rationale
     * @param rat is the vale of the @Rationale annotation
     * @param cont is the number of @Rationale in the loop
     */
    private void addInformationAndRationaleToReport(Annotation information, Rationale rat, int cont) {
        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(Utilities.setNameFile(Constants.CURRENT_DATE + "/" + rat.id() + "-" + information.getType() + "-" + information.getName())));
            document.open();
            addHeader(document);
            addInformation(document, information, rat, cont);
            addRationale(document, rat);
            document.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(ReportImplItext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Utilities.ResponseCode generateReportByAll(HashMap<Annotation, Rationale> rationaleInformation, String dest) {
        try {
            if (rationaleInformation.isEmpty()) {
                return Utilities.ResponseCode.WARNING;
            } else {
                Utilities.LOG.info("Initializing generation of Rationale Report by all annotations");
                int cont = 1;
                Document document = new Document(PageSize.A4, 50, 50, 50, 50);
                PdfWriter.getInstance(document, new FileOutputStream(dest));
                document.open();
                addHeader(document);
                for (Annotation information : rationaleInformation.keySet()) {
                    Rationale rationale = rationaleInformation.get(information);
                    //if (!rationale.hiden()) {
                    addInformation(document, information, rationale, cont);
                    addRationale(document, rationale);
                    cont++;
                    //}
                }
                document.close();
                Utilities.LOG.info("Closing generation of Rationale Report by all annotations");
                return Utilities.ResponseCode.SUCCESS;
            }
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(ReportImplItext.class.getName()).log(Level.SEVERE, null, ex);
            return Utilities.ResponseCode.FAILURE;
        }
    }

    @Override
    public Utilities.ResponseCode generateReportByOne(HashMap<Annotation, Rationale> rationaleInformation) {
        try {
            if (rationaleInformation.isEmpty()) {
                return Utilities.ResponseCode.WARNING;
            } else {
                Utilities.LOG.info("Initializing generation of Rationale Report by annotation");
                int cont = 1;
                for (Map.Entry<Annotation, Rationale> entry : rationaleInformation.entrySet()) {
                    Utilities.LOG.info("Add rationale information to report");
                    Annotation information = entry.getKey();
                    Rationale rationale = entry.getValue();
                    // if (!rationale.hiden()) {
                    addInformationAndRationaleToReport(information, rationale, cont);
                    // }
                    cont++;
                }
                Utilities.LOG.info("Close generation of Rationale Report by annotation");
                return Utilities.ResponseCode.SUCCESS;
            }
        } catch (Exception e) {
            Logger.getLogger(ReportImplItext.class.getName()).log(Level.SEVERE, null, e);
            return Utilities.ResponseCode.FAILURE;
        }
    }

    @Override
    public List<RationaleDTO> generateReportRestAll(HashMap<Annotation, Rationale> rationaleInformation) {

        try {
            if (rationaleInformation.isEmpty()) {
                return new ArrayList<>();
            } else {
                Utilities.LOG.info("Initializing generation of Rationale Report by annotation");
                int cont = 1;
                List<RationaleDTO> listRationale = new ArrayList<>();
                for (Annotation information : rationaleInformation.keySet()) {
                    Utilities.LOG.info("Add rationale information to report");
                    Rationale rationale = rationaleInformation.get(information);
                    RationaleDTO rdto = new RationaleDTO();
                    rdto.setAnnotation(information);
                    rdto.setContext(toStringVector(rationale.context()));
                    rdto.setJustification(rationale.justification());
                    rdto.setConsequence(rationale.consequence());
                    rdto.setAlternative(rationale.alternative());
                    rdto.setDecision(rationale.decision());
                    rdto.setPattern(rationale.pattern());
                    rdto.setTactic(rationale.tactic());
                    rdto.setStrategy(rationale.strategy());
                    listRationale.add(rdto);
                    cont++;
                }
                Utilities.LOG.info("Close generation of Rationale Report by annotation");
                return listRationale;
            }
        } catch (Exception e) {
            Logger.getLogger(ReportImplItext.class.getName()).log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }

    }
}
