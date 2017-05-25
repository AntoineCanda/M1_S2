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

## Chargement des fonctions externes
library ("EBImage")
source ("rdfMoments.R")

calcul <- function (path){
    img <- rdfReadGreyImage(path)
    m <- rdfMatriceInertie(img)
    e <- eigen(m)
    list("mInertie" = m, "mPrinInertie" = e$values, "axeInertie" = e$vectors, "\n")
}

affichage <- function(nom, result){
    cat(nom, " : \n")
    cat("Moments Principaux d'inertie : ", result$mPrinInertie, "\n")
    cat("Axe d'inertie : ", result$axeInertie, "\n")
}




## Calcul des moments d'Inertie des rectangles
rectH <- calcul("img/rdf-rectangle-horizontal.png")
rectV <- calcul("img/rdf-rectangle-vertical.png")
rectNormal <- calcul("img/rdf-rectangle-diagonal.png")
rectFlou <- calcul("img/rdf-rectangle-diagonal-lisse.png")

## Affichage de Valeurs
cat("=====================================================\n")
cat(" Calcul des Moments et Axes d'inertie des Rectangles\n")
cat("=====================================================\n")
    
affichage("rectangle H", rectH)
cat("----------\n")
affichage("rectangle V", rectV)
cat("----------\n")
affichage("rectangle normal", rectNormal)
cat("----------\n")
affichage("rectangle flou", rectFlou)

## Affichage de Valeurs
cat("=====================================================\n")
cat(" Calcul des Moments et Axes d'inertie des Carrés\n")
cat("=====================================================\n")
## Calcul des moments d'inertie des carrées
carre6 <- calcul("img/rdf-carre-6.png")
carre10 <- calcul("img/rdf-carre-10.png")
carre20 <- calcul("img/rdf-carre-20.png")
carre30deg <- calcul("img/rdf-carre-10-30deg.png")
carre45deg <- calcul("img/rdf-carre-10-45deg.png")

cat(" Invariance au changement d'échelle ----------\n")
cat("carre6 : ", carre6$mPrinInertie, "\n")
cat("carre10 : ", carre10$mPrinInertie, "\n")
cat("différence par rapport à carre6 en % : ", cmpMatrice(carre6$mPrinInertie,carre10$mPrinInertie), "\n")
cat("carre20 : ", carre20$mPrinInertie, "\n")
cat("différence par rapport à carre6 en %: ", cmpMatrice(carre6$mPrinInertie,carre20$mPrinInertie), "\n")
cat(" Invariance aux rotations --------- \n")
cat("carre10 : ", carre10$mPrinInertie, "\n")
cat("rotation de 30 deg : ", carre30deg$mPrinInertie, "\n")
cat("différence par rapport à carre10 en %: ", cmpMatrice(carre10$mPrinInertie,carre30deg$mPrinInertie), "\n")
cat("rotation de 45 deg : ", carre45deg$mPrinInertie, "\n")
cat("différence par rapport à carre10 en %: ", cmpMatrice(carre10$mPrinInertie,carre45deg$mPrinInertie), "\n")
