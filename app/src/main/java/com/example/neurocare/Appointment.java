package com.example.neurocare;

public class Appointment {
    private String numePacient, data, ora, scop;

    public Appointment() {}

    public Appointment(String numePacient, String data, String ora, String scop) {
        this.numePacient = numePacient;
        this.data = data;
        this.ora = ora;
        this.scop = scop;
    }

    public String getNumePacient() { return numePacient; }
    public String getData() { return data; }
    public String getOra() { return ora; }
    public String getScop() { return scop; }

    public void setNumePacient(String numePacient) { this.numePacient = numePacient; }
    public void setData(String data) { this.data = data; }
    public void setOra(String ora) { this.ora = ora; }
    public void setScop(String scop) { this.scop = scop; }
}
