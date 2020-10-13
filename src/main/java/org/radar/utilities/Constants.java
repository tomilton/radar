/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.radar.utilities;

import org.radar.analysis.implementations.ReflectionImplJar;
import org.radar.analysis.implementations.ReportImplItext;
import java.util.Date;

public class Constants {

    public static final String DEFAULT_REPORT_NAME = "Architectural Rationale Report";
    public static String CURRENT_DATE = currentDate();
    public static final String RATIONALE_REPORT_IMAGE = "resources/unicauca.png";
    public static final String DEFAULT_REPORT = ReportImplItext.class.getName();
    public static final String DEFAULT_REFLECTION = ReflectionImplJar.class.getName();

    public static final String LABEL_ORGANIZATION = "ORGANIZATION:";
    public static final String LABEL_DESCRIPTION = "DESCRIPTION:";
    public static final String LABEL_VERSION = "VERSION:";
    public static final String LABEL_AUTHOR = "AUTHOR:";
    public static final String LABEL_CURRENT_DATE = "CURRENT DATE:";
    public static final String LABEL_RATIONALE = "RATIONALE:";
    public static final String LABEL_IDENTIFIER = "ID:";
    public static final String LABEL_TYPE = "TYPE:";
    public static final String LABEL_PATH = "PATH:";
    public static final String LABEL_NAME = "NAME:";

    public static final String LABEL_CONTEXT = "CONTEXT:";
    public static final String LABEL_JUSTIFICATION = "JUSTIFICATION:";
    public static final String LABEL_CONSEQUENCE = "CONSEQUENCE:";
    public static final String LABEL_ALTERNATIVE = "ALTERNATIVE:";
    public static final String LABEL_DECISION = "DECISION:";
    public static final String LABEL_PATTERN = "PATTERN:";
    public static final String LABEL_TACTIC = "TACTIC:";
    public static final String LABEL_STRATEGY = "STRATEGY:";

    public static final String ORGANIZATION = "Universidad del Cauca";
    public static final String DESCRIPTION = ".jar library to manage the Architectural Rationale through Java Source Code Annotations";
    public static final String VERSION = "1.0";
    public static final String AUTHOR = "Milton Sanchez";
    public static final String TITLE = "RADAR";

    private static String currentDate() {
        Date date = new Date();
        return date.toGMTString().replace(":", "-").replace("GTM", "-");
    }
}
