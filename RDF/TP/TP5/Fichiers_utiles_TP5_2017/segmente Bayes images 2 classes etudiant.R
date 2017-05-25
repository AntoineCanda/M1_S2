# -----------------------------------------------------------------------
# Extraction d'attributs de pixels pour la classification,
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

#setwd("//home//m1//canda//Cours//M1//S2//RDF//TP//TP5//Fichiers_utiles_TP5_2017")
setwd("C://Users//antoine//Documents//Cours//M1//S2//RDF//TP//RDF//TP5//Fichiers_utiles_TP5_2017")

source ("rdfSegmentation.R")

# Chargement de l'image
#nom <- "rdf-2-classes-texture-0.png"
#nom <- "2classes_100_100_8bits_2016.png"
#nom <- "rdf-chiffre-0-8bits.png"
nom <- "rdf-chiffre-1-8bits.png"

image <- rdfReadGreyImage (nom)

# Calcul et affichage de son histogramme
nbins <- 256
h <- hist (as.vector (image), freq=FALSE, breaks = seq (0, 1, 1 / nbins))

# Segmentation par binarisation 0.3
seuil <- 0.35
binaire30 <- (image - seuil) >= 0


# Affichage des deux images
#if (interactive ()) {
#  display (binaire30, "image binaire 0.3")
#  display (binaire35, "image binaire 0.35")
#  display (binaire40, "image binaire 0.4")
#}

# Chargement de l'image omega1

#nom <- "2classes_100_100_8bits_omega1_2016.png"
#nom <- "rdf-chiffre-0-8bits_omega1.png"
nom <- "rdf-chiffre-1-8bits-seuillage-auto.png"
omega1 <- rdfReadGreyImage (nom)

nom <- "rdf-chiffre-1-8bits_classe_a_trouver.png" 
omega2 <- rdfReadGreyImage (nom)

#omega1 <- rdfReadGreyImage (nom)

#nom <- "2classes_100_100_8bits_omega2_2016.png"
#nom <- "rdf-chiffre-0-8bits_omega2.png"
#omega2 <- rdfReadGreyImage (nom)

# Calcul et affichage de son histogramme
nbins <- 256
h1 <- hist (as.vector (omega1), freq=FALSE, breaks = seq (0, 1, 1 / nbins))
h2 <- hist (as.vector (omega2), freq=FALSE, breaks = seq (0, 1, 1 / nbins))


# Chargement de l'image omega2
# Calcul et affichage de son histogramme
# a completer pour h2
#  Calcul des probas a priori des classes

p_omega1= sum(h1$counts[0:255])/ sum(h$counts[0:255])
# a completer pour p_omega2

p_omega2= sum(h2$counts[0:255])/ sum(h$counts[0:255])

#affichage des probabilites p_omega1 et p_omega2

print(p_omega1)
print(p_omega2)

#  Calcul des probas conditionnelles
niveauGris <- 142

#Somme pixel de niveau de gris X selon l'image 

nbPixelI <- h$counts[niveauGris]
nbPixelO1 <- h1$counts[niveauGris]
nbPixelO2 <- h2$counts[niveauGris]

# P(X/classe) = total(X)/total(Pixel image)
p_cond_image <- nbPixelI / sum(h$counts)
p_cond_omega1 <-nbPixelO1 / sum(h1$counts)
p_cond_omega2 <- nbPixelO2 / sum(h2$counts)

print(nbPixelI)
print(p_cond_image)

print(nbPixelO1)
print(p_cond_omega1)

print(nbPixelO2)
print(p_cond_omega2)

h$density[niveauGris]
h1$density[niveauGris]
h2$density[niveauGris]


#  pour le seuil X calcul de l'erreur d'assignation
somme1 = 0:255
somme2 = 0:255
erreur = 0:255
# recherche du minimum
minimum_erreur = 1;
seuil_minimum_erreur = 0;

for (X in 1:255) 
  {
  somme1[X+1]=sum(h1$density[(X+1):256])/sum(h1$density[1:256])
  somme1[X+1]=somme1[X+1]*p_omega1

  somme2[X+1]=sum(h2$density[1:(X+1)])/sum(h2$density[1:256])
  somme2[X+1]=somme2[X+1]*p_omega2
  
  erreur[X+1] = somme1[X+1] + somme2[X+1]
  
# seuil corrrespondant a l'erreur minimale
  if (erreur[X+1] < minimum_erreur ) seuil_minimum_erreur = X
  if (erreur[X+1] < minimum_erreur ) minimum_erreur = erreur[X+1]
  }

seuil = seuil_minimum_erreur/255 
print(seuil_minimum_erreur)
print(seuil)
binaire_Bayes <- (image - seuil) >= 0
display (binaire_Bayes, "image binaire Bayes")

