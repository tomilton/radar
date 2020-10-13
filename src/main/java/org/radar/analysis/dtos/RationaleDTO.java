/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.radar.analysis.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import org.radar.analysis.models.Annotation;

/**
 *
 * @author MILTON
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RationaleDTO implements Serializable {

    @JsonProperty("information")
    Annotation annotation;
    @JsonProperty("context")
    String[] context;
    @JsonProperty("justification")
    String[] justification;
    @JsonProperty("consequence")
    String[] consequence;
    @JsonProperty("alternative")
    String[] alternative;
    @JsonProperty("decision")
    String[] decision;
    @JsonProperty("pattern")
    String[] pattern;
    @JsonProperty("tactic")
    String[] tactic;
    @JsonProperty("strategy")
    String[] strategy;

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public String[] getContext() {
        return context;
    }

    public void setContext(String[] context) {
        this.context = context;
    }

    public String[] getJustification() {
        return justification;
    }

    public void setJustification(String[] justification) {
        this.justification = justification;
    }

    public String[] getConsequence() {
        return consequence;
    }

    public void setConsequence(String[] consequence) {
        this.consequence = consequence;
    }

    public String[] getAlternative() {
        return alternative;
    }

    public void setAlternative(String[] alternative) {
        this.alternative = alternative;
    }

    public String[] getDecision() {
        return decision;
    }

    public void setDecision(String[] decision) {
        this.decision = decision;
    }

    public String[] getPattern() {
        return pattern;
    }

    public void setPattern(String[] pattern) {
        this.pattern = pattern;
    }

    public String[] getTactic() {
        return tactic;
    }

    public void setTactic(String[] tactic) {
        this.tactic = tactic;
    }

    public String[] getStrategy() {
        return strategy;
    }

    public void setStrategy(String[] strategy) {
        this.strategy = strategy;
    }

}
