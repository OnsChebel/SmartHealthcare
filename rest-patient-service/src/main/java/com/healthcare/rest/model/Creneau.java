package com.healthcare.rest.model;

public class Creneau {
    private String id;
    private String medecin;
    private String specialite;
    private String dateHeure;
    private boolean disponible;

    public Creneau() {}

    public Creneau(String id, String medecin, String specialite, String dateHeure, boolean disponible) {
        this.id = id;
        this.medecin = medecin;
        this.specialite = specialite;
        this.dateHeure = dateHeure;
        this.disponible = disponible;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMedecin() { return medecin; }
    public void setMedecin(String medecin) { this.medecin = medecin; }
    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
    public String getDateHeure() { return dateHeure; }
    public void setDateHeure(String dateHeure) { this.dateHeure = dateHeure; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}