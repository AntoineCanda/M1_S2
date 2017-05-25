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

# Renvoi le barycentre et les moment d'ordre 0, 10, 01
calculMoments <- function (path){
    img <- rdfReadGreyImage(path)
    m00 <- rdfMoment(img, 0, 0)
    m10 <- rdfMoment(img, 1, 0)
    m01 <- rdfMoment(img, 1, 0)
    barycentre <- cbind(m10 / m00, m01 / m00)

    list("barycentre" = barycentre, "m00" = m00, "m10" = m10, "m01" = m01)
}

normal <- calculMoments("img/rdf-rectangle-diagonal.png")
lisse <- calculMoments("img/rdf-rectangle-diagonal-lisse.png")

cat("=========================\n")
cat(" comparaison des moments\n")
cat("=========================\n")

cat("rectangle normal -----\n")
cat("m00 : ", normal$m00, " m10 : ", normal$m10, " m01 : ", normal$m01,"\n")
cat("rectangle lisse -----\n")
cat("m00 : ", lisse$m00, " m10 : ", lisse$m10, " m01 : ", lisse$m01,"\n")
cat("différences (en %) -----\n")
cat("m00 : ", cmpMatrice(normal$m00, lisse$m00), " m10 : ", cmpMatrice(normal$m10, lisse$m10), " m01 : ", cmpMatrice(normal$m01, lisse$m01),"\n")

cat("=============================\n")
cat(" comparaison des barycentres\n")
cat("=============================\n")

cat("barycentre du rectangle : ", normal$barycentre, "\n")
cat("barycentre du rectangle Lisse: ", lisse$barycentre, "\n")
cat("pourcentage de différence : ", cmpBarycentre(normal$barycentre, lisse$barycentre), "\n")
