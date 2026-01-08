# VoyageConnect1

**VoyageConnect1** est une application web d'agence de voyage complète, conçue pour être hautement disponible, scalable et sécurisée. Elle est construite sur une architecture Java EE moderne avec Spring 6, Hibernate 6 et déployée sur WildFly 30.

## Table des matières

1.  [Prérequis](#prérequis)
2.  [Configuration de l'environnement](#configuration-de-lenvironnement)
3.  [Déploiement Local (via Docker Compose)](#déploiement-local-via-docker-compose)
4.  [Déploiement sur un Serveur Distant (via Ansible)](#déploiement-sur-un-serveur-distant-via-ansible)
5.  [Accès à l'application](#accès-à-lapplication)
6.  [Architecture du Projet](#architecture-du-projet)

---

### Prérequis

Avant de commencer, assurez-vous d'avoir les outils suivants installés sur votre machine :

*   Java 21 (Eclipse Temurin recommandé)
*   Maven 3.9+
*   Docker & Docker Compose
*   Ansible (pour le déploiement distant)
*   Un client Git

### Configuration de l'environnement

La configuration de l'application est gérée via des variables d'environnement. Un modèle est fourni dans le fichier `.env.example`.

1.  **Créez votre fichier de configuration :**
    Copiez le modèle pour créer votre fichier d'environnement local.
    ```bash
    cp .env.example .env
    ```

2.  **Modifiez le fichier `.env` :**
    Ouvrez le fichier `.env` et personnalisez les variables selon votre environnement.

    ```ini
    # PostgreSQL Database Configuration
    POSTGRES_USER=postgres
    POSTGRES_PASSWORD=your_secure_password  # Remplacez par un mot de passe fort
    POSTGRES_DB=voyageconnect1_dev

    # JWT Configuration
    # Ce mot de passe chiffre la clé privée RSA utilisée pour signer les tokens JWT
    JWT_KEYSTORE_PWD=your_strong_keystore_password # Remplacez par un mot de passe fort

    # WildFly Deployment Configuration (pour Ansible)
    WILDFLY_HOSTNAME=your_server_ip
    WILDFLY_PORT=9990
    WILDFLY_USERNAME=your_wildfly_admin_user
    WILDFLY_PASSWORD=your_wildfly_admin_password
    ```
    **Important :** Ne commitez jamais votre fichier `.env` dans le dépôt Git.

### Déploiement Local (via Docker Compose)

Le moyen le plus simple de lancer l'ensemble de l'écosystème (application, base de données, cache, serveur mail) est d'utiliser Docker Compose.

1.  **Construire et lancer les conteneurs :**
    À la racine du projet, exécutez la commande suivante. L'option `--build` force la reconstruction de l'image de l'application si des changements ont été faits dans le code.
    ```bash
    docker-compose up --build
    ```

2.  **Arrêter les conteneurs :**
    Pour arrêter et supprimer les conteneurs, le réseau et les volumes, utilisez :
    ```bash
    docker-compose down -v
    ```

### Déploiement sur un Serveur Distant (via Ansible)

Le déploiement sur un ou plusieurs serveurs WildFly pré-configurés est automatisé grâce à un playbook Ansible.

1.  **Configurer l'inventaire Ansible :**
    Créez un fichier d'inventaire (ex: `hosts.ini`) pour définir les serveurs cibles.
    ```ini
    [wildfly_servers]
    votre_serveur_1 ansible_host=IP_ou_DNS_serveur_1
    votre_serveur_2 ansible_host=IP_ou_DNS_serveur_2
    ```

2.  **Compiler l'artefact EAR :**
    Assurez-vous que l'artefact déployable est à jour en compilant le projet.
    ```bash
    mvn clean package
    ```

3.  **Exécuter le Playbook de Déploiement :**
    Lancez le playbook Ansible en spécifiant votre fichier d'inventaire. Le playbook s'occupera d'arrêter WildFly, de copier le nouvel artefact EAR, puis de redémarrer le service.
    ```bash
    ansible-playbook -i hosts.ini deploy.yml
    ```
    *Note : Le playbook suppose que vous avez configuré un accès SSH sans mot de passe (via clé publique) et les droits `sudo` pour l'utilisateur de connexion sur les serveurs cibles.*

### Accès à l'application

Une fois l'application déployée (localement ou à distance) :

*   **API de l'application :** `http://localhost:8080/voyageconnect1`
*   **Documentation de l'API (Swagger UI) :** `http://localhost:8080/voyageconnect1/swagger-ui.html`
*   **Interface Web de MailHog (local) :** `http://localhost:8025`

### Architecture du Projet

Le projet suit une architecture Maven multi-modules :

*   `voyageconnect1-parent` : Le POM parent qui gère les versions et la configuration globale.
*   `core` : Contient les entités JPA, les DTOs et les objets de domaine.
*   `persistence` : Gère l'accès aux données avec Spring Data JPA, les repositories et Flyway.
*   `service` : Contient la logique métier, la gestion des transactions et l'intégration avec les couches externes.
*   `security` : Gère la configuration de Spring Security, l'authentification JWT avec RS256, etc.
*   `web` : Expose l'API REST avec les contrôleurs, la validation et la gestion des exceptions.
*   `ear` : Assemble l'application finale en un artefact EAR pour le déploiement sur WildFly.
