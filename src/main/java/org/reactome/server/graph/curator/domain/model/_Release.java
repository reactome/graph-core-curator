package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;

public class _Release extends DatabaseObject{

    public _Release() {}

    @ReactomeProperty
    private Integer releaseNumber;
    @ReactomeProperty
    private String releaseDate;

    public String getName() {
        return this.releaseDate;
    }

    public void setName(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getReleaseNumber() {
        return releaseNumber;
    }

    public void setReleaseNumber(Integer releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

}
