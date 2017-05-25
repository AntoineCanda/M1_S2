# -----------------------------------------------------------------------
# Segmente par seuillage ,
# Module RdF, reconnaissance de formes
# Copyleft (C) 2016, Universite Lille 1
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
# Répertoire de travail
setwd("//home//m1//canda//Cours//M1//S2//RDF//TP//TP5//Fichiers_utiles_TP5_2017")
#setwd("C://Users//antoine//Documents//Cours//M1//S2//RDF//TP//RDF//TP5//Fichiers_utiles_TP5_2017")

source ("rdfSegmentation.R")

# Chargement de l'image
#nom <- "2classes_100_100_8bits_2016.png"
nom <- "rdf-chiffre-1-8bits.png"
image <- rdfReadGreyImage (nom)

# Calcul et affichage de son histogramme
nbins <- 256
h <- hist (as.vector (image), freq=FALSE, breaks = seq (0, 1, 1 / nbins))

# Segmentation par binarisation 0.5
seuil <- 0.50
binaire50 <- (image - seuil) >= 0

# Segmentation par binarisation 0.55
seuil <- 0.55
binaire55 <- (image - seuil) >= 0

# Segmentation par binarisation 0.6
seuil <- 0.6
binaire60 <- (image - seuil) >= 0

# Segmentation par binarisation 0.556862745098039
seuil <- 0.556862745098039
binaire1 <- (image - seuil) >= 0

# Affichage des images binarisées
display (binaire50, "image binaire 0.50")
display (binaire55, "image binaire 0.55")
display (binaire60, "image binaire 0.6")
display (binaire1, "image binaire avec seuil du 0 trouve de 1")




