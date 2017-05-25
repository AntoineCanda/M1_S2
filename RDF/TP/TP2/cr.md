
# Codage d'un contour
Comment sont codés les points du contour dans la variable cont? 

- ce sont des nombres complexes

Quel est l'intérêt du dernier argument de la méthode plot?

- il indique les valeurs minimales et maximale de l'axe des ordonnés sur le graphique. Ici, on spécifie les partie imaginaire minimales et maximale comme limite.


# codage d'un contour

## Descripteur de fourier
A quoi correspond le descripteur de Fourier Z0 d'une forme décrite par son contour?
- C'est le barycentre de la forme en coordonée complexe
- en additionanant z à Z0, on effectue une translation de vecteur z

## Filtrage des descripteurs de Fourier


# Caré

test avec compresion 0,5

- algorithme de la corde : ok
- fourier : ok avec tous, proche de la forme initial avec compresion 0,5
-> algo de la corde plus efficace quelque soit la comprhesion (car un caré ce n'est que 4 droites).

# Croix
- corde : la forme étant plus complexe, avec une distance de 1, on ne reconnait plus la croix (en utilisant une distrance de 0,5, trés proche de la forme initial)
- fourier : trés proche quelque soit le niveau de compresion.

# Patate

forme complexe
corde : avec une distance de 1, on a quelque choses d'assez grossier, difficile pour reconaitre les détails de la forme (surtout quand il y a des pointes avec des angles aigue)

# Triangle
corde : ne suis pas le contour, perte de beaucoup de détails
fourier : reste proche du contour, malgrès une forte compresion

# Rectangle
corde : suis parfaitement le contour
fourier : aproximatif, quelque soit la compresion

# Remarque général
- fourier : 
- corde : exellent pour les formes géométrique simple constitué de lignes droites
