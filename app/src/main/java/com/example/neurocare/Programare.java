package com.example.neurocare;

public class Programare {
    private String id;
    private String numePacient;
    private String data;
    private String ora;
    private String scop;

    // Constructor gol necesar pentru Firebase
    public Programare() {}

    // Constructor complet (ID inclus)
    public Programare(String id, String numePacient, String data, String ora, String scop) {
        this.id = id;
        this.numePacient = numePacient;
        this.data = data;
        this.ora = ora;
        this.scop = scop;
    }

    // ✅ Constructor fără ID — pentru crearea programărilor noi
    public Programare(String numePacient, String scop, String data, String ora) {
        this.numePacient = numePacient;
        this.scop = scop;
        this.data = data;
        this.ora = ora;
    }

    // Getter și Setter pentru toate câmpurile
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumePacient() {
        return numePacient;
    }

    public void setNumePacient(String numePacient) {
        this.numePacient = numePacient;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getScop() {
        return scop;
    }

    public void setScop(String scop) {
        this.scop = scop;
    }
}
