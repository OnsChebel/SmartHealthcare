package com.healthcare.rest.controller;

import com.healthcare.rest.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/rest")
public class PatientPortalController {

    private final List<Creneau> creneaux = new ArrayList<>();
    private final Map<String, RendezVous> rendezVousMap = new ConcurrentHashMap<>();
    private final Map<String, PatientProfil> patientsMap = new ConcurrentHashMap<>();
    private final Map<String, List<Ordonnance>> ordonnancesMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void initData() {
        creneaux.add(new Creneau("CR-1", "Dr. Ben Ali", "Cardiologie", "2026-11-20T09:00:00", true));
        creneaux.add(new Creneau("CR-2", "Dr. Trabelsi", "Médecine Générale", "2026-11-20T10:30:00", true));
        creneaux.add(new Creneau("CR-3", "Unité Urgences SMUR", "Urgences", "IMMEDIAT", true));

        patientsMap.put("P001", new PatientProfil(
                "P001", "Mansouri", "Sami", "+216 22 123 456", "14 Rue de la Liberté, Tunis", "CNAM-987654"
        ));

        ordonnancesMap.put("P001", List.of(
                new Ordonnance("ORD-101", "2026-10-12", "Dr. Ben Ali", List.of("Bisoprolol 5mg", "Aspirine 100mg")),
                new Ordonnance("ORD-102", "2026-11-02", "Dr. Trabelsi", List.of("Paracétamol 1g"))
        ));
    }

    @GetMapping("/creneaux")
    public List<Creneau> getCreneaux(@RequestParam(required = false) String specialite) {
        return creneaux.stream()
                .filter(Creneau::isDisponible)
                .filter(c -> specialite == null || c.getSpecialite().equalsIgnoreCase(specialite))
                .toList();
    }

    @GetMapping("/rendez-vous")
    public List<RendezVous> getRendezVous(@RequestParam String patientId) {
        return rendezVousMap.values().stream()
                .filter(rdv -> rdv.getPatientId().equals(patientId))
                .toList();
    }

    @PostMapping("/rendez-vous")
    public ResponseEntity<RendezVous> creerRendezVous(@RequestBody RendezVous rdv) {
        String id = "RDV-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        rdv.setId(id);
        rdv.setStatut(rdv.isEstUrgence() ? "URGENCE_CONFIRMEE" : "CONFIRME");
        rendezVousMap.put(id, rdv);
        return ResponseEntity.status(HttpStatus.CREATED).body(rdv);
    }

    @PutMapping("/rendez-vous/{id}")
    public ResponseEntity<RendezVous> modifierRendezVous(@PathVariable String id, @RequestBody RendezVous maj) {
        RendezVous existant = rendezVousMap.get(id);
        if (existant == null) {
            return ResponseEntity.notFound().build();
        }
        existant.setMedecin(maj.getMedecin());
        existant.setDateHeure(maj.getDateHeure());
        existant.setStatut("MODIFIE");
        return ResponseEntity.ok(existant);
    }

    @DeleteMapping("/rendez-vous/{id}")
    public ResponseEntity<Void> annulerRendezVous(@PathVariable String id) {
        if (rendezVousMap.remove(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<PatientProfil> getProfil(@PathVariable String patientId) {
        PatientProfil profil = patientsMap.get(patientId);
        return profil != null ? ResponseEntity.ok(profil) : ResponseEntity.notFound().build();
    }

    @PutMapping("/patients/{patientId}")
    public ResponseEntity<PatientProfil> updateProfil(@PathVariable String patientId, @RequestBody PatientProfil maj) {
        maj.setPatientId(patientId);
        patientsMap.put(patientId, maj);
        return ResponseEntity.ok(maj);
    }

    @GetMapping("/patients/{patientId}/ordonnances")
    public List<Ordonnance> getOrdonnances(@PathVariable String patientId) {
        return ordonnancesMap.getOrDefault(patientId, List.of());
    }
}