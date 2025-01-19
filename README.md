# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react/README.md) uses [Babel](https://babeljs.io/) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

React + Vite
Ce projet utilise React avec Vite, offrant une configuration minimale pour démarrer rapidement avec React, HMR (Hot Module Replacement), et des règles ESLint.

Prérequis
Assurez-vous d'avoir les éléments suivants installés sur votre machine :

Node.js (version 14 ou plus récente)
npm ou yarn
Dépendances nécessaires
Le projet nécessite l'installation des dépendances suivantes :

React Router : Pour gérer la navigation entre les pages.
npm install react-router-dom

SweetAlert2 : Pour afficher des popups stylées.
npm install sweetalert2

Installation
Clonez le dépôt et installez les dépendances :

git clone <url_du_projet>
cd <nom_du_projet>
npm install
Exécution du projet
Pour lancer le projet en mode développement, utilisez la commande suivante :

npm run dev
Cela démarre le serveur de développement. Ouvrez http://localhost:5173 pour voir votre application dans le navigateur.

Scripts disponibles
npm run dev : Lance le serveur de développement avec HMR.
npm run build : Construit l'application pour la production.
npm run preview : Prévisualise l'application construite pour la production.

Technologies utilisées

Vite : Un build tool rapide pour le développement front-end.
React : Une bibliothèque JavaScript pour créer des interfaces utilisateur.
ESLint : Pour garantir une qualité de code optimale.