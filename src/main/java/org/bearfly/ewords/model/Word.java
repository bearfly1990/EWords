package org.bearfly.ewords.model;

public class Word {

    private Integer id;
    private String eword;
    private String cword;

    public Word(Integer id, String eword, String cword) {
        super();
        this.id = id;
        this.eword = eword;
        this.cword = cword;
    }
    public Word( String eword, String cword) {
        super();
        this.eword = eword;
        this.cword = cword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEword() {
        return eword;
    }

    public void setEword(String eword) {
        this.eword = eword;
    }

    public String getCword() {
        return cword;
    }

    public void setCword(String cword) {
        this.cword = cword;
    }
}
