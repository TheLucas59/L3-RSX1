# Lucas Plé
# Rapport pour le TP2

## Exercice 1

```
cd ./ex1
```

### Pour compiler les programmes :
```bash
make
```

### Pour se mettre en attente de réception d'un message :
```bash
java ReceiveUDP <numero_port>
```

### Pour envoyer un message :
```
java SendUDP <adresse_ip> <port> <message>
```

## Exercice 2

```
cd ./ex2
```

### Pour compiler les programmes :
```bash
make
```

### Pour se connecter en réception de message :
```bash
java ReceiveUDP
```

### Pour envoyer un message :
```
java SendUDP
```

### Question 1
Il faut d'abord créer une socket qui se chargera de recevoir ou d'envoyer selon le cas les messages sur le multicast. Le multicast est accessible via l'ip `224.0.0.1` sur le port `7654`.

Pour recevoir les messages, on demande à la socket de rejoindre le groupe de multicast en lui passant l'adresse ip de celui-ci. On crée ensuite un datagramme UDP vide et on met la socket en attente de réception d'un message. Lorsqu'un message arrive, elle le récuperera automatiquement et on lui demande d'afficher le message. On réalise cette boucle tant que l'on a pas demandé explicitement au programme de s'arrêter avec un `Ctrl + D`. 

Pour envoyer un message, le programme attend sur l'entrée standard que l'utilisateur entre un message. Lorsqu'il valide en appuyant sur `Entrée`, on crée un datagramme UDP que l'on remplit avec le message entré et on renseigne l'adresse ip de destination et le port (ici `224.0.0.1` et `7654`). On demande ensuite à la socket d'envoyer ce datagramme.

### Question 2
La plupart des exceptions levées dans ces programmes sont des `IOException`.

Elles peuvent survenir lorsque l'on crée une socket multicast, que l'on crée un objet `InetAddress` représentant une adresse IP, lorsque l'on rejoint le groupe de multicast ou que l'on essaie d'envoyer le datagramme par la socket.

Lorsqu'on envoie un message, une exception de ce type peut également arriver lors de la lecture sur l'entrée standard du message que l'on souhaite envoyer. Une exception de type `SocketException` peut également arriver lors de la création de la socket permettant d'envoyer les messages sur le multicast.
## Exercice 3

```
cd ./ex3
```

### Pour compiler les programmes :
```bash
make
```

