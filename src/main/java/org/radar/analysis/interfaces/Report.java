package org.radar.analysis.interfaces;

import java.util.HashMap;
import java.util.List;
import org.radar.analysis.annotations.Rationale;
import org.radar.analysis.dtos.RationaleDTO;
import org.radar.analysis.models.Annotation;
import org.radar.utilities.Utilities;

public interface Report {

    public abstract Utilities.ResponseCode generateReportByAll(HashMap<Annotation, Rationale> rationaleInformation, String dest);

    public abstract Utilities.ResponseCode generateReportByOne(HashMap<Annotation, Rationale> rationaleInformation);

    public abstract List<RationaleDTO> generateReportRestAll(HashMap<Annotation, Rationale> rationaleInformation);

}
