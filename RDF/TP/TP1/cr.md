%TP1 Rdf : moments d'une forme
%Antoine Canda et Arthur d'Azémar

# Code R

Pour retrouver la formule $M_{ij} = \sum_{x} \sum_{y} x^iy^jI(x,y)$ On crée 2 vecteurs (X et Y) qui vont contenir, respectivement la suite $x^i$ et $y^j$. Ensuite, on multiplie matriciellement X avec l'image et la transposée de Y.


Cette méthode est beaucoup plus efficace car les calculs matriciels peuvent être optimisés par R.

# Moment d'une forme

Les 2 rectangles ont la même forme mais l'un est flou (les pixels du contour ne sont pas de la même couleur)
Comme le calcul du moment se base sur le niveau de gris de chaque pixel, les valeurs des moments seront différentes.
Par contre, comme le barycentre est un rapport de 2 moments, la différence de valeurs va s'annuler dans la division des moments. La différence sera donc minime.

On peut constater ces résultats en exécutant le script ``calculMomentRectFlou.R``


Pour lancer le calcul des moments d'inertie pour les formes proposées, utilisez le script ``calculMomentsInertie.R`` 


Le script montre que les moments principaux d'inertie sont sensibles au changement d'échelle mais résistent à la rotation.

# Moments normalisés

le script ``calculMomentsCentreNormalise.R`` permet de calculer les moments centrés normalisés des carrés et des rectangles. On peut remarquer que pour les carrés, les moments centrés normalisés sont invariants en rotation et en changements de forme. Par contre, ils ne sont pas invariants en rotation pour les rectangles (on observe de fortes différences entres les moments)


En calculant les moments principaux d'inertie normalisés (grâce aux script ``calculMomentsPrincipauxInertieNormalise.R``), on observe que ces valeurs sont insensible aux rotations et aux changements de formes. Ces moments peuvent donc être utilisés comme attributs de forme.

# Moments invariants

On calcul les moments invariants grâce aux script ``calculMomentsInvariants.R``. On remarque que les moments ne sont pas sensibles à certaines transformations. Pour la rotation, le premier moments semble insensible aux rotations. Pour les autres moments, ils ne sont pas insensibles aux rotations.


Pour comparer les moments entre eux, nous calculons le rapport de la distance entre les 2 valeurs par le moments comparé.
Par exemple, pour comparer le moment A et B on calcul l'expression : $(\left|A - B\right|) \times 100 / A$. Si cette valeur est petite, on peut dire que A et B sont suffisamment proches pour être égales.

Le problème c'est que les moments de Hu que nous avons calculé ont une valeur très faible. Donc le moindre changement crée un pourcentage de différence très important. On peut donc dire que, dans l'absolue, les moments de Hu ne varient pas lorsque la forme subit une rotation.


Le script ``calculMomentsInvariantsChiffres.R`` permet de calculer les moments de Hu pour les images de chiffres de 0 à 9.
Ces moments sont tous différents pour chacun de ces chiffres, on peut donc supposer qu'ils permettent de différencier ces images.
