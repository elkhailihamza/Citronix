# Citronix - Gestion de Ferme Citron

## Description

Citronix est une application Spring Boot de gestion pour une ferme de citron. Elle permet de suivre les informations sur la ferme, les champs, les arbres, les récoltes et les ventes. L'objectif est de faciliter la gestion quotidienne et d'améliorer la prise de décision en fournissant des données précises.

## Fonctionnalités

- **Création de ferme** : Ajoutez des fermes et gérez leurs informations.
- **Gestion des champs** : Suivez les champs de la ferme avec des détails comme la superficie et la culture.
- **Suivi des arbres** : Enregistrez les arbres de chaque champ, y compris leur type et leur âge.
- **Gestion des récoltes** : Enregistrez les récoltes et suivez leur quantité et leur qualité.
- **Suivi des ventes** : Suivez les ventes de citrons récoltés et générez des rapports financiers.

## Prérequis

- Java 11 ou supérieur
- Maven
- Base de données PostgreSQL
- Spring Boot 2.x

## Installation

1. Clonez ce repository :
    ```bash
    git clone https://github.com/votre-utilisateur/citronix.git
    ```

2. Allez dans le répertoire du projet :
    ```bash
    cd citronix
    ```

3. Installez les dépendances :
    ```bash
    mvn install
    ```

4. Configurez votre base de données dans le fichier `src/main/resources/application.properties`. Exemple de configuration :
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/citronix
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
    ```

5. Démarrez l'application :
    ```bash
    mvn spring-boot:run
    ```

6. L'application devrait maintenant être accessible à `http://localhost:8081`.

## Tests

Pour exécuter les tests unitaires, utilisez la commande suivante :

```bash
mvn test
```

Les tests sont configurés avec JUnit et MockMvc pour tester les API REST.

Contribution
Les contributions sont les bienvenues ! Pour ajouter une fonctionnalité ou corriger un bug, veuillez suivre ces étapes :

Forkez ce repository.
Créez une branche pour votre fonctionnalité ou correction de bug.
Effectuez vos modifications.
Soumettez une pull request.
