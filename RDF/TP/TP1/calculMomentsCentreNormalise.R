# -----------------------------------------------------------------------
# Extraction d'attributs de forme,
# Module RdF, reconnaissance de formes
# Copyleft (C) 2014, Universite Lille 1
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
# -----------------------------------------------------------------------

# Chargement des fonctions externes
library ("EBImage")
source ("rdfMoments.R")

calcul <- function(path){
    img <- rdfReadGreyImage(path)
    rdfMomentCentreNormaliseOrdre2(img)
}

## Caldul des moments d'inertie
carre6 <-calcul("img/rdf-carre-6.png")
carre10 <- calcul("img/rdf-carre-10.png")
carre30deg <- calcul("img/rdf-carre-10-30deg.png")
carre45deg <- calcul("img/rdf-carre-10-45deg.png")
carre20 <- calcul("img/rdf-carre-20.png")
rectH <- calcul("img/rdf-rectangle-horizontal.png")
rectV <- calcul("img/rdf-rectangle-vertical.png")
rectN <- calcul("img/rdf-rectangle-diagonal.png")
rectF <- calcul("img/rdf-rectangle-diagonal-lisse.png")


cat("INVARIANCE AU CHANGEMENT DE FORME =======\n")
cat("Carré coté 6 : ", carre6, "\n")
cat("Carré côté 10 : ", carre10, "\n")
cat("différence avec carré 6 en % : ", cmpMatrice(carre6, carre10), "\n")
cat("Carré côté 20 : ", carre20, "\n")
cat("différence avec carré 6 en % : ", cmpMatrice(carre6, carre20), "\n")

cat("INVARIANCE À LA ROTATION (CARRE) =======\n")
cat("Carré côté 10 : ", carre10, "\n")
cat("Rotation 30deg : ", carre30deg, "\n")
cat("différence avec carré 10 en % : ", cmpMatrice(carre10, carre30deg), "\n")
cat("Rotation 45deg : ", carre45deg, "\n")
cat("différence avec carré 10 en % : ", cmpMatrice(carre10, carre45deg), "\n")
cat("INVARIANCE À LA ROTATION (RECTANGE) =======\n")
cat("rectangle horizontal : ", rectH, "\n")
cat("Rectangle vertical : ", rectV, "\n")
cat("différence avec rectangle horizontal en % : ", cmpMatrice(rectH, rectV), "\n")
cat("Rectangle diag : ", rectN, "\n")
cat("différence avec rectangle horizontal en % : ", cmpMatrice(rectH, rectN), "\n")
cat("Rectangle diagonal lisse : ", rectF, "\n")
cat("différence avec rectangle horizontal en % : ", cmpMatrice(rectH, rectF), "\n")


## ## print("==========================")
## ## print("Triangle normal : ")
## ## img <- rdfReadGreyImage("img/rdf-triangle-10.png")
## ## m <- rdfMatriceInertieOrdre2(img)
## ## ## print(m)
## ## print(eigen(m))
## ## print("==========================")
## ## print("Triangle rotation 15deg : ")
## ## img <- rdfReadGreyImage("img/rdf-triangle-10-15deg.png")
## ## m <- rdfMatriceInertieOrdre2(img)
## ## ## print(m)
## ## print(eigen(m))
## ## print("==========================")
## ## print("Triangle rotation 45deg : ")
## ## img <- rdfReadGreyImage("img/rdf-triangle-10-45deg.png")
## ## m <- rdfMatriceInertieOrdre2(img)
## ## ## print(m)
## ## print(eigen(m))
## ## print("==========================")
## ## print("Triangle rotation 45deg : ")
## ## img <- rdfReadGreyImage("img/rdf-triangle-10-60deg.png")
## ## m <- rdfMatriceInertieOrdre2(img)
## ## ## print(m)
## ## print(eigen(m))
