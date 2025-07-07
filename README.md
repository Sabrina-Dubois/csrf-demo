# 🛡️ Projet CSRF – Démonstration d’une attaque et protections

## 🧪 Objectif

Ce projet a pour but de démontrer ce qu’est une attaque **CSRF (Cross-Site Request Forgery)**, comment elle fonctionne, et comment s’en protéger.

---

## 📁 Structure du projet
```
csrf-demo/
├── attacker/ 
    ├── ui
├── bank/                       
│   ├── not-protected/
│   │  └── business
│   │  └── database
│   │  └── ui
│   ├── protected/
│   │  └── business
│   │  └── database
│   │  └── ui
└── README.md                                          
```

## 🧪 Scénario d'attaque CSRF

1. L’utilisateur se connecte à l’application bancaire vulnérable (`bank-unprotected`).
2. Il reste connecté avec une session active.
3. Il visite un site malveillant (`attacker-site`).
4. Ce site exécute une requête POST (ex : transfert d’argent) **à son insu**.
5. La banque exécute la requête car aucune **protection CSRF** n’est mise en place.

---

## ✅ Composants

### 1. `bank-unprotected` – ❌ Application vulnérable

- ✅ Authentification utilisateur
- ❌ Aucune protection CSRF
- ❌ Aucune vérification de l’origine des requêtes
- 💻 Backend : Spring Boot  
- 🌐 Frontend : Vue.js  
- 🛢️ Base de données : PostgreSQL

---

### 2. `bank-protected` – ✅ Application protégée

- ✅ Protection CSRF activée via Spring Security
- ✅ Token CSRF injecté dans les formulaires / headers
- ✅ Rejet des requêtes POST non sécurisées
- 💻 Backend : Spring Boot  
- 🌐 Frontend : Vue.js  
- 🛢️ Base de données : PostgreSQL

---

### 3. `attacker-site` – ☠️ Site malveillant

- Simple page HTML contenant :
  - un formulaire caché
  - une requête POST automatique vers la banque vulnérable
- Objectif : déclencher une action **sans consentement**

---

## 🖥️ Technologies

- Spring Boot (backend)
- Vue.js (frontend)
- PostgreSQL (DB)

---

## ⚙️ Installation & Lancement

### 1. Cloner le projet

# Clone du projet
```
git clone https://github.com/Sabrina-Dubois/csrf-demo.git
cd csrf-demo
```

---

### 2. Lancer la version non protégée
### Backend
```
cd bank/not-protected/business
./mvnw spring-boot:run
```

#### Frontend
```
cd bank/not-protected/ui
npm install
npm run dev
```

### 3. Lancer la version protégée
### Backend
```
cd bank/protected/business
./mvnw spring-boot:run
```

#### Frontend
```
cd bank/protected/ui
npm install
npm run dev
```

### 4. Site attaquant
#### Frontend
```
cd attacker/ui
npm install
npm run dev
```
