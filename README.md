# ❌ Projet CSRF - Application **Vulnérable**

## 🧪 Objectif

Ce projet montre comment une application web peut être vulnérable à une attaque **CSRF** si elle ne met pas en place de protection spécifique.

---

## 📌 Fonctionnement

- L’utilisateur se connecte à l’application.
- Une requête `POST` modifie des données (retrait d'argent) **sans vérification**.
- Un attaquant force l’utilisateur à envoyer cette requête à son insu.
- Résultat : retrait malveillat **non autorisée**.

---

## ⚠️ Aucune protection CSRF

- ❌ Aucun token généré
- ❌ Requêtes acceptées dès lors que l’utilisateur est connecté
- ✅ Permet de démontrer un scénario d’attaque

---

## 🧪 Démonstration de l'attaque

1. L’utilisateur est connecté à sa banque.
2. Il visite un site tiers malveillant contenant un fichier `attacker.html`.
3. Ce fichier contient un formulaire caché qui envoie une requête `POST` vers le backend.
4. Le backend accepte la requête car l’utilisateur est connecté.
5. Un retrait d'argent est exécité **sans son consentement**.

---

## 🖥️ Technologies

- Backend : Spring Boot 
- Frontend : Vue.js

## 📦 Installation

```bash
# Clone du projet
git clone https://github.com/Sabrina-Dubois/csrf-demo.git

# Accès au dossier backend
cd csrf-demo/backend

# Build et lancement du backend
./mvnw spring-boot:run
bash


# Dans un second terminal : frontend
cd ../frontend

# Installation des dépendances (si nécessaire)
npm install

# Lancement de l'application frontend
npm run dev

