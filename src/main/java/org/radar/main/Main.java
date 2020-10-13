package org.radar.main;

import org.radar.services.ReportService;

public class Main {

    public static void main(String[] args) {
        System.out.println("RADAR");

        ReportService.generateReportsByOne("org");

    }

}
