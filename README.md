# Présentation

Le but de ce devoir est de réaliser une application de jeu, dotée d'une interface graphique, (mais pouvant être utilisé sans l'interface graphique) qui consiste en un puzzle à glissière. Le but du jeu est de reconstituer l'image du puzzle par déplacements successifs. La partie est finie lorsque le puzzle est reconstitué.

# Objectifs de l'application

Le but est de réaliser une structure intégralement en MVC, avec un modèle totalement indépendant de l'interface graphique. En particulier, le jeu devra être jouable en ligne de commande.

Dans l'interface graphique, le contrôle sera fait de deux façons simultanées :

- Un clic sur l'élément que l'on souhait déplacer (s'il est déplaçable). Un plus sera de mettre enexergue (par exemple en l'encadrant) un élément survolé par la souris lorsque celui-ci est déplaçable, ce qui permet à l'utilisateur de mieux voir ce qu'il peut faire.
- Un appui sur l'une des 4 flèches du clavier

# Compilation du projet

Ouvrir un terminal dans le dossier puis entrer les commandes suivantes :

```sh
chmod +x ./build.sh
./build.sh run
```
Pour voir la liste des sous-commandes possibles :
```sh
./build.sh
```

# Structuration du projet

Le projet comporte les dossiers suivants :
- rapport → Regroupe les documents du rapport
- res → Ressources utiles à l'application
- src → Code source de l'application
- test → Code des test unitaires

# Bibliothèques / Framework utilisées

- Swing

# Collaborateurs

Le projet étant réalisé en groupe, voici la liste des personnes affectées sur celui-ci :

- Auréline DEROUIN ([@Norah72](https://github.com/Norah72))
- Justine MARTIN ([@jmartin-pro](https://github.com/jmartin-pro))
- Lorada ANDRÉ ([@loradaandre](https://github.com/loradaandre))
- Maxime THOMAS ([@maxime-Thom](https://github.com/maxime-Thom))
