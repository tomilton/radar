/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.radar.analysis.reporter;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.radar.analysis.annotations.Rationale;
import org.radar.analysis.dtos.RationaleDTO;
import org.radar.analysis.interfaces.Reflection;
import org.radar.analysis.interfaces.Report;
import org.radar.analysis.models.Annotation;
import org.radar.utilities.Constants;
import org.radar.utilities.Utilities;

public class Reporter {

    private Report reportStrategy;
    private Reflection reflection;
    private HashMap<Annotation, Rationale> rationaleInformation;

    public Reporter(String packageName) {
        try {
            reportStrategy = (Report) Class.forName(Constants.DEFAULT_REPORT).newInstance();
            reflection = (Reflection) Class.forName(Constants.DEFAULT_REFLECTION).newInstance();
            reflection.configureReflection(packageName);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Reporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        rationaleInformation = reflection.getRationaleAnnotation();
    }

    public Utilities.ResponseCode createRationaleReportByAll(String nameFile) {
        Utilities.ResponseCode response;
        response = reportStrategy.generateReportByAll(rationaleInformation, Utilities.setNameFile(nameFile));
        return response;
    }

    public Utilities.ResponseCode createRationaleReportByOne() {
        Utilities.ResponseCode response;
        response = reportStrategy.generateReportByOne(rationaleInformation);
        return response;
    }

    public List<RationaleDTO> createRationaleReportRestByOne() {
        return reportStrategy.generateReportRestAll(rationaleInformation);
    }

    public Report getReportStrategy() {
        return reportStrategy;
    }

    public void setReportStrategy(Report reportStrategy) {
        this.reportStrategy = reportStrategy;
    }

    public Reflection getReflection() {
        return reflection;
    }

    public void setReflection(Reflection reflection) {
        this.reflection = reflection;
    }

    public HashMap<Annotation, Rationale> getRationaleInformation() {
        return rationaleInformation;
    }

    public void setRationaleInformation(HashMap<Annotation, Rationale> rationaleInformation) {
        this.rationaleInformation = rationaleInformation;
    }

}
