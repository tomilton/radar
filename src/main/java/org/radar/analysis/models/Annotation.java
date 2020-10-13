package org.radar.analysis.models;

public class Annotation {

    private String path;
    private String name;
    private String type;

    public Annotation(String path, String name, String type) {
        super();
        this.path = path;
        this.name = name;
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
