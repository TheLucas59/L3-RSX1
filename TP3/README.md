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

On utilise telnet de cette façon :
```bash
telnet <adresse_ip> <port>
```

Par exemple, en salle TP, j'ai utilisé telnet de cette façon sur ma machine :
```bash
telnet localhost 2021
```

Mon voisin s'est connecté à mon serveur de cette façon :
```bash
telnet a11p8 2021
```

### Question 4
Pour que le programme accepte les connexions en boucle et les traite une par une, on met le code permettant d'accepter une connexion et d'écrire le message de bienvenue dans une boucle infinie. Ainsi, lorsque le serveur aura terminé de traiter une connexion, il va attendre de recevoir une prochaine connexion et ce jusqu'à ce qu'on l'arrête manuellement. 

Pour garder une trace des différents clients qui se sont connectés au serveur, on écrit dans un fichier leur adresse IP locale et leur port local. On peut récupérer ces informations depuis la socket créée lors de l'acceptation d'une connexion.

## Exercice 2

```bash
cd ./ex2
```

### Pour compiler le serveur et l'exécuter :
```bash
make
java TCPServer
```

### Pour créer des clients se connectant au serveur :
```bash
telnet <adresse_ip> <port>
```
Si vous voulez par exemple tester en local le serveur, créez un client de cette manière : 
```bash
telnet localhost 2021
```
Vous pouvez également utiliser la boucle locale `127.0.0.1` au lieu de localhost pour vous connecter.

### Question 1
Il faut créer un nouveau thread à chaque connexion entrante du serveur. C'est ce nouveau thread qui s'occupera de gérer la réception et l'envoi de messages vers la socket dont il est responsable.

### Question 2
On peut récupérer le flux d'entrée de la socket via la méthode `getInputStream` et lire sur ce flux les messages envoyés par l'utilisateur sur la socket.

### Question 3
Pour transmettre les messages à toutes les autres sockets, il faut y avoir accès dans le thread. Comme un thread ne gère qu'une seule socket, il se retrouve isolé des autres thread qui gèrent les autres sockets. On peut donc créer dans le serveur une liste de socket statique. Ainsi, les threads auront accès à cette liste et pourront accéder aux flux de données des sockets pour écrire les messages transmis par l'utilisateur courant. Le message apparaîtra donc sur les terminaux des autres clients.

### Question 4
Pour que le programme tourne en boucle, on ajoute un `while(true)` au niveau du `accept()` des sockets côté serveur. Ainsi le serveur attendra des connexions de socket en boucle jusqu'à ce qu'on lui demande explicitement de s'arrêter. A l'intérieur du thread, il faut boucler sur le `read()` du flux d'entrée de la socket. En effet cette méthode renvoie `-1` si on arrive au bout d'un fichier ou que plus rien n'est à lire. Mais nous sommes ici dans le cas d'un flux : cette fonction est bloquante et attend que des octets arrivent sur le flux. Comme `read()` renvoie le nombre d'octets lus, elle ne retourne pas `-1` dans le cas d'un flux mais 0 : on peut donc l'appeler une fois avant la boucle pour initialiser notre variable stockant le retour de la fonction. On peut boucler dessus. On a donc un serveur qui attend des connexions et des threads qui attendent l'arrivée d'octets (messages) écrits par les utilisateurs avant de les renvoyer vers les autres sockets pour que les autres utilisateurs puissent voir ces messages.

### Question 5
Pour que les sockets se ferment correctement lors de la rupture de connexion, il faut explicitement fermer les flux de données, la socket en elle-même et la retirer de la liste des sockets pour ne pas créer d'erreurs. On peut atteindre ce bout de code grâce à la boucle du thread expliquée dans la question précédente.

Si toutes les fermetures se sont bien passées, on ne devrait plus trouver nulle part la socket correspondant au client qui a coupé la connexion. Le socket et les autres serveurs ne vont pas pouvoir écrire dessus et aucune erreur ne sera générée. 