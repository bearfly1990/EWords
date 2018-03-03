package org.bearfly.ewords.model;

public class EWord {

    private int id;
    private String eword;
    private String cword;

    public EWord(int id, String eword, String cword) {
        super();
        this.id = id;
        this.eword = eword;
        this.cword = cword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
