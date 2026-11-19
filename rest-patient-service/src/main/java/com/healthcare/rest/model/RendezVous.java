package com.healthcare.rest.model;

public class RendezVous {
    private String id;
    private String patientId;
    private String medecin;
    private String dateHeure;
    private boolean estUrgence;
    private String statut;

    public RendezVous() {}

    public RendezVous(String id, String patientId, String medecin, String dateHeure, boolean estUrgence, String statut) {
        this.id = id;
        this.patientId = patientId;
        this.medecin = medecin;
        this.dateHeure = dateHeure;
        this.estUrgence = estUrgence;
        this.statut = statut;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getMedecin() { return medecin; }
    public void setMedecin(String medecin) { this.medecin = medecin; }
    public String getDateHeure() { return dateHeure; }
    public void setDateHeure(String dateHeure) { this.dateHeure = dateHeure; }
    public boolean isEstUrgence() { return estUrgence; }
    public void setEstUrgence(boolean estUrgence) { this.estUrgence = estUrgence; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}