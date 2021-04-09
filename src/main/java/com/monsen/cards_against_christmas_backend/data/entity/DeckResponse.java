package com.monsen.cards_against_christmas_backend.data.entity;

import java.util.List;

public class DeckResponse {
    private String ms;
    private String query;
    private List<CardResponse> result;

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<CardResponse> getResult() {
        return result;
    }

    public void setResult(List<CardResponse> result) {
        this.result = result;
    }
}
