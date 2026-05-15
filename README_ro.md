HerokuApp UI Automation – Framework de Testare Automatizată

Acest proiect reprezintă un framework complet de testare automatizată UI pentru aplicația demo The Internet – HerokuApp. Framework ul este construit în stil profesionist, folosind cele mai bune practici din industrie: Java, Selenium WebDriver, TestNG, Maven, Page Object Model și Allure Report.

Obiectivul proiectului
Scopul acestui proiect este de a demonstra:
•	abilități solide de QA Automation
•	structură profesională a framework ului
•	integrare CI/CD cu GitHub Actions
•	generarea și publicarea automată a rapoartelor Allure
•	capturi de ecran automate pentru testele eșuate
•	utilizarea Page Object Model pentru un cod clar și scalabil

Tehnologii utilizate
•	Java 17
•	Selenium WebDriver
•	TestNG
•	Maven
•	Page Object Model (POM)
•	Allure Report
•	GitHub Actions (CI/CD)
•	WebDriverManager

Structura proiectului
Cod
src
 └── test
     ├── java
     │    ├── pages
     │    │     ├── LoginPage.java
     │    │     └── SecurePage.java
     │    ├── tests
     │    │     └── LoginTests.java
     │    └── utils
     │          └── ScreenshotUtils.java
     └── resources
           └── environment.properties

Funcționalități acoperite
•	Login cu credențiale valide
•	Login cu credențiale invalide
•	Acces direct la SecurePage fără autentificare
•	Logout și verificarea mesajului de confirmare
•	Capturi de ecran automate la testele eșuate
•	Allure Steps pentru acțiuni clare și vizibile în raport

Rulare teste
1.Rulare locală:
mvn clean test
2.Generare raport Allure
mvn allure:report
3.Vizualizare raport local
allure serve target/allure-results

Raport Allure public
Raportul Allure este generat automat prin GitHub Actions și publicat pe GitHub Pages:
 https://victoria198611.github.io/Herokuapp-Automation/
Include:
•	timeline
•	steps
•	environment
•	screenshots
•	suite overview

CI/CD – GitHub Actions
Pipeline-ul rulează automat la fiecare push pe branch-ul main:
•	rulează testele
•	generează raportul Allure
•	salvează artifactele
•	publică raportul în branch-ul gh-pages

Capturi de ecran automate
La fiecare test eșuat, framework-ul capturează automat un screenshot și îl atașează în raportul Allure.
Implementarea se află în:
utils/ScreenshotUtils.java

Page Object Model
Framework-ul folosește POM pentru a separa logica UI de testele propriu-zise.
Exemple:
 LoginPage — acțiuni și elemente pentru pagina de login (introducere username, parolă, click login, citire mesaje, verificare logout)
  SecurePage — verificări pentru pagina securizată (validare titlu, buton logout, navigare înapoi la LoginPage)
  BaseTest — configurarea WebDriver, deschiderea aplicației, maximizare fereastră, teardown și atașare screenshot la testele eșuate
  LoginTests — suitele de teste pentru login valid, login invalid, acces direct la SecurePage și logout
 ScreenshotUtils — utilitar pentru capturi de ecran automate, atașate în Allure Report
 environment.properties — configurarea mediului pentru Allure (browser, OS, tester, versiuni).

Autor
Victoria QA Automation Engineer Proiect creat pentru portofoliu profesional și dezvoltare continuă.
