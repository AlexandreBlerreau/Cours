
# Serveur web pawnee !

Projet dans le cadre du module M4101C Programmation système avancé

## Lancement

Une simple commande pour démarrer le serveur :
```
$ ./pawnee
```
Vous pouvez changer de port avec :
```
$ ./pawnee -p [port]
```

### Premier lancement ?

Si c'est le premier lancement du serveur, il va créé à la racine du dossier où il sera lané un dossier  **"www"**
"www" contiendra :
* **index.html**
 * **404.html**
* **505.html**

L'index sera accessible à partir de :
*                                                       localhost:{port}/
*                                                       localhost:{port}/index.html


### Interface console 
La console affiche un log des requêtes avec
* L'heure
* Le code de retour (200,404,505)
* La ressource demandée 

### Ajouter vos pages et / ou modifier l'index

* Tout les fichiers présents dans www seront interprétés par le serveur
* Vous pouvez y ajouter des dossiers
* Vous pouvez bien sur modifier index, 404, 505.html [attention si vous les supprimez il reprendrons leurs aspects de base]


## Auteurs

* **Alexy Ledain** - *FC2*
* **Alexandre Blerreau** - *FC2*


## Dificultés recontrées

* Affichage des images, PDF, vidéos
* Quelques bugs de gestion de la mémoire
* Confusions dans l'écriture de l'header HTTP (norme HTTP)

## Ce qui a été fait 

* Le serveur peut accueillir des connexions HTTP 1.1
* Le serveur peut recevoir des requêtes HTTP 1.1
* Le serveur peut répondre aux requêtes 
* Le serveur peut interpréter tous les MIME types
* Le serveur gère l'erreur 404
* Le serveur gère l'erreur 505

* Il répond aux fonctions d'un serveur basique 

## Attention 

Le serveur ne semble pas totalement optimisé, il est gourmand en CPU et batterie si vous êtes sur portable.

## License

Le projet est soumis à la licence Do **What the Fuck You Want to Public License (WTFPL)**

