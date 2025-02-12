Voici un projet Java avec une interface graphique (GUI) et une base de données (JDBC) pour gérer un magasin de voitures :

---

### Description du Projet : **Gestion d'un Magasin de Voitures**

Ce projet est une application de gestion d'un magasin de voitures qui utilise Java avec une interface graphique (GUI) et l'accès à une base de données SQL (via JDBC).
L'application permet de gérer les voitures disponibles à la vente, les clients qui s'inscrivent pour commander des voitures, 
ainsi que l'accès administrateur pour effectuer des modifications sur le stock de voitures.

### Fonctionnalités principales :
1. **Écran d'accueil** :
   - À l'exécution de l'application, une fenêtre principale s'ouvre, affichant deux boutons :
     - **Button Admin** : pour accéder à l'interface d'administration.
     - **Button Client** : pour accéder à l'interface client.
![image alt](https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/Main_Window.png?raw=true)

2. **Connexion en tant qu'Admin** :
   - Si l'utilisateur clique sur **Button Admin**, il doit entrer un nom d'utilisateur et un mot de passe.
   - Le programme vérifie ces informations dans la table `admin` de la base de données (id_admin, username, password).
![image alt](https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/Main_Window.png?raw=true](https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/Login_Page.png?raw=true))

- Si les informations sont valides, l'admin accède à un menu où il peut :
     - **Ajouter** une voiture à la base de données (via la table `voiture`).
![image alt]([https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/Main_Window.png?raw=true](https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/Login_Page.png?raw=true)](https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/Add_Car.png?raw=true))

- **Supprimer** une voiture de la base de données.
   - La table `voiture` contient des informations comme `id_voiture`, `marque`, `modele`, `annee`, et `prix`.
   
3. **Connexion en tant que Client** :
   - Si l'utilisateur clique sur **Button Client**, il peut soit s'inscrire, soit se connecter si déjà inscrit.
![image alt]((https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/ClientPage.png?raw=true))

- **Inscription** : Si l'utilisateur n'est pas encore inscrit, il doit remplir un formulaire avec ses informations personnelles :
     - `nom`, `prenom`, `email`, et `telephone`.
     - Ces informations sont enregistrées dans la table `client` (id_client, nom, prenom, email, telephone).
   - **Connexion** : Si l'utilisateur est déjà inscrit, il peut simplement entrer son `nom` pour s'identifier et accéder à une fenêtre de commande.
   
4. **Commande de Voiture** :
   - Après la connexion en tant que client, l'utilisateur peut consulter les voitures disponibles à la vente, qui sont extraites de la table `voiture`.
   - Le client choisit une voiture à acheter et peut passer une **commande** en entrant la voiture et son ID client, ce qui crée une entrée dans la table `commande` (id_commande, id_voiture, id_client, date_commande).
![image alt](https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/%C2%A8Page%20du%20Commade.png?raw=true)
### Structure de la Base de Données :
1. **Table voiture** :
   - `id_voiture` (Clé primaire) : identifiant unique de chaque voiture.
   - `marque` : marque de la voiture.
   - `modele` : modèle de la voiture.
   - `annee` : année de fabrication de la voiture.
   - `prix` : prix de la voiture.
![image alt](https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/table_Voiture.png?raw=true)
2. **Table client** :
   - `id_client` (Clé primaire) : identifiant unique du client.
   - `nom` : nom du client.
   - `prenom` : prénom du client.
   - `email` : adresse email du client.
   - `telephone` : numéro de téléphone du client.
![image alt](https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/table_Client.png?raw=true)
3. **Table commande** :
   - `id_commande` (Clé primaire) : identifiant unique de la commande.
   - `id_voiture` (Clé étrangère) : identifiant de la voiture commandée.
   - `id_client` (Clé étrangère) : identifiant du client ayant passé la commande.
   - `date_commande` : date de la commande.
![image alt](
https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/table_Commande.png?raw=true)
4. **Table admin** :
   - `id_admin` (Clé primaire) : identifiant unique de l'admin.
   - `username` : nom d'utilisateur de l'admin.
   - `password` : mot de passe de l'admin.
![image alt](https://github.com/Ibrxhxm-Bxddxne/Java_MagasinVoitures/blob/main/table_Admin.png?raw=true)

### Technologies utilisées :
- **Java (JDK)** : pour développer l'application.
- **JDBC** : pour la gestion de la base de données SQL et l'exécution des requêtes.
- **MySQL** : pour gérer la base de données (les tables `voiture`, `client`, `commande`, `admin`).
- **Java Swing** : pour créer l'interface graphique (GUI).
