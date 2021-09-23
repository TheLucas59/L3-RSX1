# Rapport pour le TP1

## UDP

### Question 1

![commande ip](./Screenshots/IPQ1.png "ip")


L'adresse mac de l'interface est : `e4:54:e8:59:67:fb`

L'adresse ipV4 de la machine est : `192.168.5.69/24`


### Question 2
L'identifiant de la socket est 3

![socket](./Screenshots/Q2.png "socket")

### Question 3

![statut](./Screenshots/Q3.png "statut")

### Question 4
image

### Question 5
image

### Question 6
Il doit me fournir son adresse ip ainsi que son port de destination.

### Question 7
image

### Question 8 
Il est préférable de choisir le port sur la machine qui reçoit le message pour s'assurer que celui-ci soit libre et que tout les clients qui souhaitent communiquer avec cette machine utilisent ce port.

En revanche, il importe peu que l'on choisisse nous même le port de la machine cliente, puisque le serveur recevra le port source avec le message dans l'en-tête du datagramme.

### Question 9 
image

### Question 10
image

### Question 11
image

### Question 12
image

### Question 13
image

### Question 14
image 

### Question 15
image 

a) Les adresses IP destination et sources ainsi que les ports sont corrects.

b) image

c) Un segment UDP a été envoyé pour chaque message car les données de ces communications étaient très courtes.

d) Pour le message "Comment allez-vous ?" nous avons envoyé 20 octets de données et la trame avait une taille de 62 octets.

Pour le message "Très bien. Merci !" nous avons envoyé 19 octets de données et la trame avat une taille de 61 octets.

Il y a donc une différence de 42 octets entre le message envoyé et la trame complète.  

L'efficacité pour le premier message est de 20/62 = 32.25%.
L'efficacité pour le deuxième message est de 19/61 = 31.15%.

### Question 16
image

## TCP
### Question 1
image

### Question 2
image

### Question 3
image

### Question 4
image

### Question 5
image

### Question 6 
La socket serveur est la socket qui écoute, soit S2 et la socket client est la socket S1 qui enverra des messages à S2.
