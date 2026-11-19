package com.healthcare.rest.model;

import java.util.List;

public class Ordonnance {
    private String id;
    private String dateEmission;
    private String medecinPrescripteur;
    private List<String> medicaments;

    public Ordonnance() {}

    public Ordonnance(String id, String dateEmission, String medecinPrescripteur, List<String> medicaments) {
        this.id = id;
        this.dateEmission = dateEmission;
        this.medecinPrescripteur = medecinPrescripteur;
        this.medicaments = medicaments;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDateEmission() { return dateEmission; }
    public void setDateEmission(String dateEmission) { this.dateEmission = dateEmission; }
    public String getMedecinPrescripteur() { return medecinPrescripteur; }
    public void setMedecinPrescripteur(String medecinPrescripteur) { this.medecinPrescripteur = medecinPrescripteur; }
    public List<String> getMedicaments() { return medicaments; }
    public void setMedicaments(List<String> medicaments) { this.medicaments = medicaments; }
}