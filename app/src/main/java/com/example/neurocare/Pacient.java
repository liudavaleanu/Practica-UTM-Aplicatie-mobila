package com.example.neurocare;

import java.io.Serializable;

public class Pacient implements Serializable {

    private String id; // ID-ul documentului din Firestore
    private String nume;
    private String varsta;
    private String diagnostic;
    private String tratament;
    private String observatii;

    // Constructor fără parametri - necesar pentru Firebase
    public Pacient() {
    }

    // Constructor complet
    public Pacient(String nume, String varsta, String diagnostic, String tratament, String observatii) {
        this.nume = nume;
        this.varsta = varsta;
        this.diagnostic = diagnostic;
        this.tratament = tratament;
        this.observatii = observatii;
    }

    // GETTERE și SETTERE

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getVarsta() {
        return varsta;
    }

    public void setVarsta(String varsta) {
        this.varsta = varsta;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTratament() {
        return tratament;
    }

    public void setTratament(String tratament) {
        this.tratament = tratament;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }
}
