Partie I :

Nbins = Nombre de valeurs de gris disponible.

Le seuil a une influence sur la segmentation comme on peut voir sur les diff�rentes images. 
On multiplie le seuil par 256 et les valeurs en dessous seront en blanc et celle au dessus seront en noir.

Voir � am�liorer les r�sultats ? 

histo texture 0

seuil 0.5 pour avoir les ronds bien blanc 

histo texture 1 

seuil 0.5 pour avoir les ronds parfaitement blanc

histo texture 2

seuil 0.51 pour avoir les ronds parfaitement blanc (chang� avec <=) 

histo texture 3

seuil 0.5 pour avoir les ronds parfaitement blanc (chang� avec <=) 

histo texture 4

seuil 0.35 pour avoir les ronds blanc 


On remarque qu'on a beaucoup de bruits et donc pas une binarisation parfaite � part pour la texture 0 (voir difftextX.png)

Pourcentage de % entre la segmentation obtenu de mani�re empirique et celle de r�f�rence

Texture 0 : 19 pixels diff�rents pour 0.1159668%

Texture 1 : 540 pixels diff�rents pour 3.295898%

Texture 2 : 5786 pixels diff�rents pour 35.31494%

Texture 3 : 5430 pixels diff�rents pour 33.14209%

Texture 4 : 9291 pixels diff�rents pour 56.70776%

Il est n�cessaire d'utiliser un autre attribut pour pouvoir segmenter les images. 

Partie II : 

Expliquer comment la fonction rdfTextureEcartType d�termine le niveau de texture pour chaque pixel de l'image

La fonction rdfTextureEcartType va commencer par calculer le carr� de l'image moins sa moyenne. 
La moyenne est calcul�e gr�ce � la fonction rdfMoyenneImage.
Elle va calculer la matrice n�cessaire � la d�termination de la zone qui sera consid�rer autour du premier pixel.
Ensuite la fonction filter est appel�e pour appliquer le masque. 
Filter2 permet d'appliquer des filtres comme des flous, des d�tections de contours ...
Enfin on calcule l'�cart type et on normalise.

Comment fixe-t-on la dimension du voisinage de calcul gr�ce � l'argument taille pass� � la fonction ?

Il est calcul� dans rdfMoyenneImage. La taille correspond au nombre de pixels voisins que l'on consid�re. 
La dimension de la matrice permet de d�terminer le nombre de pixel. 
On a 2*i +1 que l'on met au carr� pour i = taille. 

Pourquoi l'image �cart-type est-elle normalis�e ?

On normalise l'image �cart-type car on obtient des valeurs sup�rieurs � celle de base. La normalisation permet de revenir � des valeurs correspondantes.


Histogramme calcul� : 

Voir histo-texture-textureX.png avec X = 0 .. 4 

Seuil trouv� :

texture 0 : 0.5 
texture 1 : 0.3
texture 2 : 0.46
texture 3 : 0.35
texture 4 : 0.3

%age erreur trouv�: 

texture 0 : 34.17969 
texture 1 : 9.802246
texture 2 : 6.274414
texture 3 : 3.900146
texture 4 : 4.064941

Quand on regarde les diff�rents r�sultats obtenus, que ce soit les images ou les pourcentages d'erreurs, on peut observer que dans certains cas on obtient des r�sultats bien meilleurs � ce que l'on a eu avant mais aussi bien plus mauvais.
On peut par exemple prendre le cas de la texture 0 qui passe d'un taux d'erreur quasiment null � un taux d'erreurs de plus d'un tiers de ses pixels. 
Mais les textures 2, 3 et 4 passent d'un taux d'erreur de plus de 30% � moins de 6.3%. 
L'image obtenu de la texture 0 montre que la texture a cr�e un bruit important. 
On en conclut donc que bien qu'int�ressant dans certains cas, utiliser uniquement le niveau de texture comme attribut n'est pas suffissant mais reste int�ressant.
La combinaison de plusieurs attributs peut peut �tre permettre de combiner le meilleur des diff�rents cas et obtenir une binarisation plus proche de celle attendue. 
 
Partie III: 

Analyser le code de la fonction rdfCalculeHistogramme2D qui permet de calculer l'histogramme conjoint des deux attributs, en vous r�f�rant � la documentation de R pour les fonctions findInterval et table

On commence par calculer les intervales entre les valeurs x et y de la matrice pour les deux images avec la fonction findInterval. 
Ensuite on va cr�er une matrice de 256*256 pour lequel on va calculer le nombre d'occurence des valeurs de niveaux de gris. On compl�te avec la valeur 0.
Ensuite on utilise la fonction log pour afficher les zones de faible surface de l'histogramme soit pr�sente et on normalise pour afficher sous forme d'image.
