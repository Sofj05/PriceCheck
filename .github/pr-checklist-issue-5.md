PR-checkliste for PR #6 (Markér bestilling som klar)

- Refererer Issue: #5
- Ændringer:
  - Order: status kan ændres, markReady() tilføjet
  - OrderService: markOrderReady(id) tilføjet med validering
  - Main: menuvalg 3 til at markere bestilling som klar
- Test:
  - Kompileret og kørt scenarier:
    - Kendt id i MODTAGET -> ændres til KLAR
    - Ukendt id -> vis brugervenlig fejl
    - Ikke-numerisk id -> vis brugervenlig fejl
    - Allerede KLAR -> vis besked om ingen ændring
    - Programmet crasher ikke ved forkert input
- Fokus for review: korrekt status-flow, fejlhåndtering, ingen utilsigtede sideeffekter
- Manual test: kør Main og vælg menu 2 (opret), derefter 3 (markér klar)
