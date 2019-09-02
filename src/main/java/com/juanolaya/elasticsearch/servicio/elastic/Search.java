package com.juanolaya.elasticsearch.servicio.elastic;

public class Search {

    private final String title;
    private final String date;
    private final String highlight;

    public Search(String title, String date, String highlight) {
        this.title = title;
        this.date = date;
        this.highlight = highlight;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getHighlight() {
        return highlight;
    }
}
