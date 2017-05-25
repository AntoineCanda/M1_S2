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
setwd("~/Cours/M1/S2/RDF/TP/RDF/TP1/")
source("rdfMoments.R")

calcul <- function(path){
    img <- rdfReadGreyImage(path)
    rdfMomentsInvariants(img)
}


carre6 <-calcul("img/rdf-carre-6.png")
carre10 <- calcul("img/rdf-carre-10.png")
carre30deg <- calcul("img/rdf-carre-10-30deg.png")
carre45deg <- calcul("img/rdf-carre-10-45deg.png")
carre20 <- calcul("img/rdf-carre-20.png")
rectH <- calcul("img/rdf-rectangle-horizontal.png")
rectV <- calcul("img/rdf-rectangle-vertical.png")
rectN <- calcul("img/rdf-rectangle-diagonal.png")
rectF <- calcul("img/rdf-rectangle-diagonal-lisse.png")
triangle10 <-calcul("img/rdf-triangle-10.png")
triangle15deg <-calcul("img/rdf-triangle-10-15deg.png")
triangle45deg <-calcul("img/rdf-triangle-10-45deg.png")
triangle60deg <-calcul("img/rdf-triangle-10-60deg.png")

cat("INVARIANCE AU CHANGEMENT DE FORME =======\n")
cat("Carre cote 6 : ", carre6, "\n")
cat(" ")
cat("Carre cote 10 : ", carre10, "\n")
cat(" ")
cat("difference avec carre 6 en % : ", cmpMatrice(carre6, carre10), "\n")
cat(" ")
cat("Carre cote 20 : ", carre20, "\n")
cat(" ")
cat("difference avec carre 6 en % : ", cmpMatrice(carre6, carre20), "\n")

cat("INVARIANCE A LA ROTATION (CARRE) =======\n")
cat("Carre cote 10 : ", carre10, "\n")
cat(" ")
cat("Rotation 30deg : ", carre30deg, "\n")
cat("difference avec carre 10 en % : ", cmpMatrice(carre10, carre30deg), "\n")
cat(" ")
cat("Rotation 45deg : ", carre45deg, "\n")
cat("difference avec carre 10 en % : ", cmpMatrice(carre10, carre45deg), "\n")
cat(" ")
cat("INVARIANCE A LA ROTATION (RECTANGE) =======\n")
cat("rectangle horizontal : ", rectH, "\n")
cat(" ")
cat("Rectangle vertical : ", rectV, "\n")
cat("difference avec rectangle horizontal en % : ", cmpMatrice(rectH, rectV), "\n")
cat(" ")
cat("Rectangle diag : ", rectN, "\n")
cat("difference avec rectangle horizontal en % : ", cmpMatrice(rectH, rectN), "\n")
cat(" ")
cat("Rectangle diagonal lisse : ", rectF, "\n")
cat("difference avec rectangle horizontal en % : ", cmpMatrice(rectH, rectF), "\n")

cat("INVARIANCE A LA ROTATION (TRIANGLE) =======\n")
cat("triangle cote 10 : ", triangle10, "\n")
cat(" ")
cat("triangle 15deg : ", triangle15deg, "\n")
cat("difference avec triangle de cote 10 en % : ", cmpMatrice(triangle10, triangle15deg), "\n")
cat(" ")
cat("triangle 45deg : ", triangle45deg, "\n")
cat("difference avec triangle de cote 10 en % : ", cmpMatrice(triangle10, triangle45deg), "\n")
cat(" ")
cat("triangle 60deg : ", triangle60deg, "\n")
cat("difference avec triangle de cote 10 en % : ", cmpMatrice(triangle10, triangle60deg), "\n")





