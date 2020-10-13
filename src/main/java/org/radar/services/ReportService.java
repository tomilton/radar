/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.radar.services;

import org.radar.analysis.reporter.Reporter;
import org.radar.utilities.Constants;
import org.radar.utilities.Utilities;

public class ReportService {

    private static Reporter reporter = null;

    /**
     *
     * @param packageName Package where is generated the file with annotations
     * @return ResponseCode is the response code in the report generation
     */
    public static Utilities.ResponseCode generateReportByAll(String packageName) {
        reporter = new Reporter(packageName);
        Utilities.ResponseCode response = reporter.createRationaleReportByAll(Constants.CURRENT_DATE + Constants.DEFAULT_REPORT_NAME);
        switch (response) {
            case SUCCESS:
                Utilities.showLogSuccessMessage();
                break;
            case WARNING:
                Utilities.showLogWarningMessage();
                break;
            case FAILURE:
                Utilities.showLogFailureMessage();
                break;
        }
        return response;
    }

    /**
     *
     * @param packageName Package where is generated the file with annotations
     * @return ResponseCode is the response code in the report generation
     */
    public static Utilities.ResponseCode generateReportsByOne(String packageName) {
        reporter = new Reporter(packageName);
        Utilities.ResponseCode response = reporter.createRationaleReportByOne();
        switch (response) {
            case SUCCESS:
                Utilities.showLogSuccessMessage();
                break;
            case WARNING:
                Utilities.showLogWarningMessage();
                break;
            case FAILURE:
                Utilities.showLogFailureMessage();
                break;
        }
        return response;
    }

}
