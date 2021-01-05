package com.practice.crud.domain;

public class FileDomain {

    private String original;
    private String saved;

    public FileDomain(String original, String saved) {
        this.original = original;
        this.saved = saved;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getSaved() {
        return saved;
    }

    public void setSaved(String saved) {
        this.saved = saved;
    }
}
