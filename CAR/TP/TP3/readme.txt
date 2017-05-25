CANDA Antoine et Su Hui.

Le tp marche. 

On peut crée deux systèmes avec deux fichiers conf aux adresses 127.0.0.1:3552 et 127.0.0.1:3553.
Il y a différentes classes de types greetings répondant à différents besoins : 
Ajouts d'acteur (parent ou fils) à un noeud. 
Message simple, message servant d'accusé réception, message de lancement depuis un noeud autre que la racine, message depuis un parent... 
Ils implémentent une interface.

Pour le graphe on a mis en place un système de flag avec un booleen visited afin d'éviter de tomber dans une boucle infinie.

 
