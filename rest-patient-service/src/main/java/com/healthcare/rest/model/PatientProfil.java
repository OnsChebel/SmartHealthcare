package com.healthcare.rest.model;

public class PatientProfil {
    private String patientId;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
    private String codeVignetteCNAM;

    public PatientProfil() {}

    public PatientProfil(String patientId, String nom, String prenom, String telephone, String adresse, String codeVignetteCNAM) {
        this.patientId = patientId;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.codeVignetteCNAM = codeVignetteCNAM;
    }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getCodeVignetteCNAM() { return codeVignetteCNAM; }
    public void setCodeVignetteCNAM(String codeVignetteCNAM) { this.codeVignetteCNAM = codeVignetteCNAM; }
}