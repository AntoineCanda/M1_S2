Partie I :

Nbins = Nombre de valeurs de gris disponible.

Le seuil a une influence sur la segmentation comme on peut voir sur les différentes images. 
On multiplie le seuil par 256 et les valeurs en dessous seront en blanc et celle au dessus seront en noir.

Voir à améliorer les résultats ? 

histo texture 0

seuil 0.5 pour avoir les ronds bien blanc 

histo texture 1 

seuil 0.5 pour avoir les ronds parfaitement blanc

histo texture 2

seuil 0.51 pour avoir les ronds parfaitement blanc (changé avec <=) 

histo texture 3

seuil 0.5 pour avoir les ronds parfaitement blanc (changé avec <=) 

histo texture 4

seuil 0.35 pour avoir les ronds blanc 


On remarque qu'on a beaucoup de bruits et donc pas une binarisation parfaite à part pour la texture 0 (voir difftextX.png)

Pourcentage de % entre la segmentation obtenu de manière empirique et celle de référence

Texture 0 : 19 pixels différents pour 0.1159668%

Texture 1 : 540 pixels différents pour 3.295898%

Texture 2 : 5786 pixels différents pour 35.31494%

Texture 3 : 5430 pixels différents pour 33.14209%

Texture 4 : 9291 pixels différents pour 56.70776%

Il est nécessaire d'utiliser un autre attribut pour pouvoir segmenter les images. 

Partie II : 

Expliquer comment la fonction rdfTextureEcartType détermine le niveau de texture pour chaque pixel de l'image

La fonction rdfTextureEcartType va commencer par calculer le carré de l'image moins sa moyenne. 
La moyenne est calculée grâce à la fonction rdfMoyenneImage.
Elle va calculer la matrice nécessaire à la détermination de la zone qui sera considérer autour du premier pixel.
Ensuite la fonction filter est appelée pour appliquer le masque. 
Filter2 permet d'appliquer des filtres comme des flous, des détections de contours ...
Enfin on calcule l'écart type et on normalise.

Comment fixe-t-on la dimension du voisinage de calcul grâce à l'argument taille passé à la fonction ?

Il est calculé dans rdfMoyenneImage. La taille correspond au nombre de pixels voisins que l'on considére. 
La dimension de la matrice permet de déterminer le nombre de pixel. 
On a 2*i +1 que l'on met au carré pour i = taille. 

Pourquoi l'image écart-type est-elle normalisée ?

On normalise l'image écart-type car on obtient des valeurs supérieurs à celle de base. La normalisation permet de revenir à des valeurs correspondantes.


Histogramme calculé : 

Voir histo-texture-textureX.png avec X = 0 .. 4 

Seuil trouvé :

texture 0 : 0.5 
texture 1 : 0.3
texture 2 : 0.46
texture 3 : 0.35
texture 4 : 0.3

%age erreur trouvé: 

texture 0 : 34.17969 
texture 1 : 9.802246
texture 2 : 6.274414
texture 3 : 3.900146
texture 4 : 4.064941

Quand on regarde les différents résultats obtenus, que ce soit les images ou les pourcentages d'erreurs, on peut observer que dans certains cas on obtient des résultats bien meilleurs à ce que l'on a eu avant mais aussi bien plus mauvais.
On peut par exemple prendre le cas de la texture 0 qui passe d'un taux d'erreur quasiment null à un taux d'erreurs de plus d'un tiers de ses pixels. 
Mais les textures 2, 3 et 4 passent d'un taux d'erreur de plus de 30% à moins de 6.3%. 
L'image obtenu de la texture 0 montre que la texture a crée un bruit important. 
On en conclut donc que bien qu'intéressant dans certains cas, utiliser uniquement le niveau de texture comme attribut n'est pas suffissant mais reste intéressant.
La combinaison de plusieurs attributs peut peut être permettre de combiner le meilleur des différents cas et obtenir une binarisation plus proche de celle attendue. 
 
Partie III: 

Analyser le code de la fonction rdfCalculeHistogramme2D qui permet de calculer l'histogramme conjoint des deux attributs, en vous référant à la documentation de R pour les fonctions findInterval et table

On commence par calculer les intervales entre les valeurs x et y de la matrice pour les deux images avec la fonction findInterval. 
Ensuite on va créer une matrice de 256*256 pour lequel on va calculer le nombre d'occurence des valeurs de niveaux de gris. On complète avec la valeur 0.
Ensuite on utilise la fonction log pour afficher les zones de faible surface de l'histogramme soit présente et on normalise pour afficher sous forme d'image.
