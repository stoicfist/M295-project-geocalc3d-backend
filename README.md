# 3D-Geometrie Visualizer

Ein Backend-System zur Berechnung von geometrischen Eigenschaften wie Volumen, Oberfläche oder Diagonalen auf Basis benutzerdefinierter Eingaben.

## 🧠 Projektidee

Benutzer:innen können 3D-Formen wie Kugeln, Quader, Kegel oder Pyramiden eingeben. Das System berechnet automatisch mathematische Werte wie Volumen und Oberfläche. Später ist eine visuelle Darstellung im Angular-Frontend geplant.

## ⚙️ Technologien

- Java 23
- Spring Boot
- PostgreSQL (JPA/Hibernate)
- Keycloak (OAuth2 für Login & Rollen)
- Maven
- Swagger UI
- GitHub

## 🔐 Rollen

- `USER`: Figuren eingeben und berechnen
- `ADMIN`: Zugriff auf alle Figuren & Ergebnisse, ggf. neue Figurenmodelle freischalten

## 🧩 REST-API Übersicht

| Endpoint            | Methode | Beschreibung                         | Rolle     |
|---------------------|---------|--------------------------------------|-----------|
| `/api/shapes`       | GET/POST| Figuren speichern und verwalten      | USER/ADMIN|
| `/api/calculate`    | POST    | Berechnung starten                   | USER      |
| `/api/results`      | GET     | Ergebnisse abrufen                   | USER/ADMIN|
| `/api/users`        | GET     | Benutzerübersicht (nur Admins)       | ADMIN     |

## 📐 Unterstützte Figuren (Beispiel)

- Kugel (radius)
- Quader (länge, breite, höhe)
- Kegel (radius, höhe)
- Pyramide (grundkante, höhe)

## 🏁 Projektziel

- Umsetzung der Anforderungen im Modul 295
- Sicheres REST-Backend mit Spring Boot + Keycloak
- Speicherung von Benutzerberechnungen
- Später Visualisierung im Angular-Frontend (Modul 293)

## 📂 Projektstruktur (Backend)
```
src/
└── main/
    └── java/
        └── ch/
            └── peterngo/
                └── geometrie/
                    ├── controller/       # REST-Controller (API-Endpunkte)
                    ├── service/          # Geschäftslogik & Berechnungen
                    ├── repository/       # Datenbankzugriffe (JPA)
                    ├── model/            # Datenmodelle / Entitäten
                    ├── security/         # Keycloak-Konfiguration & Rollen
                    └── config/           # CORS, Swagger, allgemeine Einstellungen
    └── resources/
        ├── application.yml               # Konfiguration (DB, Security etc.)
        └── static/                       # (optional für Frontend später)
```

## 🔧 Setup & Start

1. Keycloak lokal starten (siehe `keycloak-config/`)
2. PostgreSQL-Datenbank einrichten
3. Projekt mit `mvn spring-boot:run` starten
4. Swagger öffnen: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🧑‍💻 Autor

- Peter Ngo
- Kurs: 23-295-F
- Projektarbeit ÜK 295

---

> Dieses Projekt ist Teil des Kompetenznachweises im Modul 295 (Backend für Applikationen realisieren).
