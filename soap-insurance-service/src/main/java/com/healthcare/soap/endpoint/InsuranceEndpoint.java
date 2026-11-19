package com.healthcare.soap.endpoint;

import com.healthcare.soap.insurance.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDate;
import java.util.*;

@Endpoint
public class InsuranceEndpoint {

    private static final String NAMESPACE_URI = "http://healthcare.com/soap/insurance";
    private final Map<String, List<FeuilleSoinCertifiee>> historiqueParPatient = new HashMap<>();

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "verifierDroitsCNAMRequest")
    @ResponsePayload
    public VerifierDroitsCNAMResponse verifierDroits(@RequestPayload VerifierDroitsCNAMRequest request) {
        VerifierDroitsCNAMResponse response = new VerifierDroitsCNAMResponse();
        boolean valide = request.getCodeVignetteCNAM() != null && request.getCodeVignetteCNAM().startsWith("CNAM");
        response.setEstEligible(valide);
        response.setTauxRemboursement(valide ? 80 : 50); // Taux 80% ou 50%
        response.setRegime(valide ? "Filière Publique CNAM" : "Régime Standard");
        response.setMessage(valide ? "Droits ouverts et pris en charge" : "Vignette non reconnue, taux réduit");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "emettreFeuilleDeSoinsRequest")
    @ResponsePayload
    public EmettreFeuilleDeSoinsResponse emettreFeuille(@RequestPayload EmettreFeuilleDeSoinsRequest request) {
        double taux = request.getCodeVignetteCNAM().startsWith("CNAM") ? 0.80 : 0.50;
        String numFeuille = "FS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        FeuilleSoinCertifiee feuille = new FeuilleSoinCertifiee();
        feuille.setNumeroFeuille(numFeuille);
        feuille.setLettreCleActe(request.getLettreCleActe());
        feuille.setMontant(request.getMontantTotal());
        feuille.setDateActe(LocalDate.now().toString());

        historiqueParPatient.computeIfAbsent(request.getPatientId(), k -> new ArrayList<>()).add(feuille);

        EmettreFeuilleDeSoinsResponse response = new EmettreFeuilleDeSoinsResponse();
        response.setNumeroFeuilleCertifiee(numFeuille);
        response.setPartRemboursee(request.getMontantTotal() * taux);
        response.setStatutValidation("CERTIFIEE_CNAM");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "consulterHistoriqueRequest")
    @ResponsePayload
    public ConsulterHistoriqueResponse consulterHistorique(@RequestPayload ConsulterHistoriqueRequest request) {
        ConsulterHistoriqueResponse response = new ConsulterHistoriqueResponse();
        List<FeuilleSoinCertifiee> actes = historiqueParPatient.getOrDefault(request.getPatientId(), List.of());
        response.getFeuillesDeSoins().addAll(actes);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "comparerGarantiesRequest")
    @ResponsePayload
    public ComparerGarantiesResponse comparerGaranties(@RequestPayload ComparerGarantiesRequest request) {
        ComparerGarantiesResponse response = new ComparerGarantiesResponse();
        response.setTauxRegimeA(80);
        response.setTauxRegimeB(50);
        response.setRecommandation("Le régime " + request.getRegimeA() + " offre une meilleure couverture pour l'acte " + request.getLettreCleActe());
        return response;
    }
}