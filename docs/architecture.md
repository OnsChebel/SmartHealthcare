# Architecture du projet Smart Healthcare

## 1. Schéma d'Architecture Microservices

```text
                        +-----------------------------------+
                        |        Client Web Unique          |
                        |         (Port : 3000)             |
                        +-----------------+-----------------+
                                          |
                                          | HTTP / REST / GraphQL / SOAP
                                          v
                        +-----------------------------------+
                        |           API Gateway             |
                        |   (Spring Cloud Gateway : 8888)   |
                        +-----------------+-----------------+
                                          |
          +-------------------------------+-------------------------------+
          |                               |                               |
          | /ws/**                        | /api/rest/**                  | /api/graphql/**
          v                               v                               v
+-------------------+           +-------------------+           +-------------------+
|   Service SOAP    |           |   Service REST    |           |  Service GraphQL  |
|  (Port : 8081)    |           |  (Port : 8082)    |           |  (Port : 8083)    |
|  Assurance & CNAM |           | Portail & RDV     |           | Dashboard Médecin |
+---------+---------+           +---------+---------+           +---------+---------+
          ^                               ^                               ^
          |                               |                               |
          | 2. Vérifier droits            | 3. Réserver RDV               | 4. Publier fiche
          |    CNAM (XML)                 |    urgence (JSON)             |    intervention
          |                               |                               |
          +-------------------------------+-------------------------------+
                                          |
                        +-----------------+-----------------+
                        |      Service Orchestrateur        |
                        |          (Port : 8084)            |
                        |   (Processus Métier d'Urgence)    |
                        +-----------------+-----------------+
                                          ^
                                          | 1. Alerte seuil critique
                                          |
                        +-----------------+-----------------+
                        |           Service gRPC            |
                        |   (Port gRPC : 9090 / HTTP : 8085)|
                        |   Télémétrie & Alertes Temps Réel |
                        +-----------------+-----------------+
                                          ^
                                          | Streaming Protobuf (HTTP/2)
                        +-----------------+-----------------+
                        |       Capteur IoT Virtuel         |
                        |    (Simulateur Client Java)       |
                        +-----------------------------------+