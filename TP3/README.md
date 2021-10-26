# Lucas Plé
# Rapport pour le TP3

## Exercice 1

```bash
cd ./ex1
```

### Pour compiler le serveur et l'exécuter :
```bash
make
java TCPServer
```

### Question 1
Pour pouvoir traiter une requête, il faut déjà que le serveur soit en mesure d'accepter des connexions. Pour cela, on appelle la méthode `accept()` qui met le serveur en attente jusqu'à ce qu'une connexion arrive sur le port spécifié (ici `2021`). Quand une connexion arrive, on récupère un objet `Socket` lié à cette connexion. Cet objet nous permet d'obtenir les flux d'entrées et sorties de la connexion. Ainsi, on peut écrire sur la socket et donc envoyer la réponse au client. Une fois la réponse envoyée, on interrompt la connexion en fermant la socket.

### Question 2
Toutes les exceptions à traiter dans ce programme sont des `IOException`. Elles interviennent lorsque l'on instantie la socket serveur, lorsque l'on accepte une connexion, lorsque l'on récupère le flux de sortie de la socket pour écrire la réponse, lorsqu'on crit sur la socket et lorsque l'on ferme la socket. On prend soin à chaque fois d'afficher la trace de l'exception au cas où un problème intervient et on sort du programme.

### Question 3
Pour tester le bon fonctionnement du serveur, on le lance et on essaie de s'y connecter localement et depuis une autre machine du réseau local. En utilisant le client `telnet` on voit bien la réponse du serveur au client. Si la connexion réussie, on voit bien le message de bienvenue ainsi que le message `Connexon closed by foreign host`.

### Question 4
Pour que le programme accepte les connexions en boucle et les traite une par une, on met le code permettant d'accepter une connexion et d'écrire le message de bienvenue dans une boucle infinie. Ainsi, lorsque le serveur aura terminé de traiter une connexion, il va attendre de recevoir une prochaine connexion et ce jusqu'à ce qu'on l'arrête manuellement. 

Pour garder une trace des différents clients qui se sont connectés au serveur, on écrit dans un fichier leur adresse IP locale et leur port local. On peut récupérer ces informations depuis la socket créée lors de l'acceptation d'une connexion.

## Exercice 2
