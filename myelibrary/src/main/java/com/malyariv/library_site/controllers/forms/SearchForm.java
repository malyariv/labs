package com.malyariv.library_site.controllers.forms;

public class SearchForm {
    private String searchField;
    private String type;

    public SearchForm() {
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SearchForm{" +
                "searchField='" + searchField + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
