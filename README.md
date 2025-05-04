# Auto-diagnostic médical

A partir de l'index de santé fourni par le capteur d'une cabine d'auto-diagnostic, afficher les unités médicales vers lesquelles renvoyer le patient.

Actuellement, seuls les index de santé multiples de 3 (maladies cardiaques) et de 5 (fractures) sont supportés, tout autre type d'index terminera le programme en erreur.

## Table des Matières

- [Pré-requis](#pré-requis)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Contact](#contact)

## Pré-requis
La compilation du projet nécessite un [JDK 23](https://download.java.net/openjdk/jdk23/ri/openjdk-23+37_windows-x64_bin.zip) et [Maven 3.9](https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip).

## Installation
Instructions pour installer le projet :

```bash
git clone https://github.com/remyfrerot/softway-diagnosis.git
cd softway-diagnosis
mvn clean install
```

## Utilisation
```bash
cd softway-diagnosis/target
java -jar diagnosis-1.0-SNAPSHOT-jar-with-dependencies.jar <index de santé>
```

## Contact
[remy.frerot@gmail.com](mailto:remy.frerot@gmail.com)