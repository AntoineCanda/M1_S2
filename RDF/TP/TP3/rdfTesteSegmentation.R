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
#setwd("C://Users//antoine//Documents//Cours//M1//S2//RDF//TP//RDF//TP3//")
setwd("//home//m1//canda//Cours//M1//S2//RDF//TP//TP3")
source ("rdfSegmentation.R")

# Chargement d'une image
nom <- "img/rdf-2-classes-texture-0.png"
image <- rdfReadGreyImage (nom)

# Calcul et affichage de son histogramme
nbins <- 256
nbins2 <- 128
nbins3 <- 512
nbins4 <- 1024
h <- hist (as.vector (image), breaks = seq (0, 1, 1 / nbins))
h2 <- hist (as.vector (image), breaks = seq (0, 1, 1 / nbins2))
h3 <- hist (as.vector (image), breaks = seq (0, 1, 1 / nbins3))
h4 <- hist (as.vector (image), breaks = seq (0, 1, 1 / nbins4))


# Segmentation par binarisation
#seuil <- 0.3
#seuil2 <- 0.4
#seuil3 <- 0.5
#seuil4 <- 0.6

#binaire <- (image - seuil) >= 0
#binaire2 <- (image - seuil2) >= 0
#binaire3 <- (image - seuil3) >= 0
#binaire4 <- (image - seuil4) >= 0


# Affichage des deux images
#if (interactive ()) {
#  display (image, nom)
#  display (binaire, "image binaire")
#  display (binaire2, "image binaire 2")
#  display (binaire3, "image binaire 3")
#  display (binaire4, "image binaire 4")
#}

# Chargement d'une image
nom0 <- "img/rdf-2-classes-texture-0.png"
nom1 <- "img/rdf-2-classes-texture-1.png"
nom2 <- "img/rdf-2-classes-texture-2.png"
nom3 <- "img/rdf-2-classes-texture-3.png"
nom4 <- "img/rdf-2-classes-texture-4.png"

image0 <- rdfReadGreyImage (nom0)
image1 <- rdfReadGreyImage (nom1)
image2 <- rdfReadGreyImage (nom2)
image3 <- rdfReadGreyImage (nom3)
image4 <- rdfReadGreyImage (nom4)

# Calcul et affichage de son histogramme
nbins <- 256
h <- hist (as.vector (image), breaks = seq (0, 1, 1 / nbins))


# Segmentation par binarisation
seuil0 <- 0.5
binaire0 <- (image0 - seuil0) >= 0
display (binaire0, "image binaire 0")

seuil1 <- 0.5
binaire1 <- (image1 - seuil1) >= 0
display (binaire1, "image binaire 1")

seuil2 <- 0.51
binaire2 <- (image2 - seuil2) >= 0
display (binaire2, "image binaire 2")

seuil3 <- 0.5
binaire3 <- (image3 - seuil3) <= 0
display (binaire3, "image binaire 3")

seuil4 <- 0.35
binaire4 <- (image4 - seuil4) >= 0
display (binaire4, "image binaire 4")

# Difference par rapport a celle attendue
nom <- "img/rdf-masque-ronds.png"
image <- rdfReadGreyImage (nom)

nomB <- "img/histo2D/classificateur2d-text4.png"
imageB <- rdfReadGreyImage (nomB)
a <-abs(imageB-image)

# Affichage des deux images
if (interactive ()) {
#  display (binaire, "image binaire")
  display (a, "image binaire diff?rence")
  
}

#Pourcentage diff?rence 
nom <- "img/histo2D/diff-text4.png"
image <- rdfReadGreyImage(nom)
h <- hist (as.vector (image), breaks = seq (0, 1, 1 / 256))

nbPixel <- h$counts[256]

#Affichage du pourcentage
print(nbPixel)
print(nbPixel*100/sum(h$counts))

#nbins <- 256
#nom <- "rdf-masque-ronds.png"
#ref <- rdfReadGreyImage (nom)

#nom <- "rdf-2-classes-texture-0.png"
#image <- rdfReadGreyImage (nom)
#image <- rdfTextureEcartType(image, 2)
#h <- hist (as.vector (image), breaks = seq (0, 1, 1 / nbins))
#seuil <- 0.5
#binaire <- (seuil-image)<= 0
#display (binaire, "image binaire texture 0")
#print(sum(abs(ref-binaire))/16384*100)

#nom <- "rdf-2-classes-texture-1.png"
#image <- rdfReadGreyImage (nom)
#image <- rdfTextureEcartType(image, 2)
#h <- hist (as.vector (image), breaks = seq (0, 1, 1 / nbins))
#seuil <- 0.3
#binaire <- (seuil-image)>= 0
#display (binaire, "image binaire texture 1")
#print(sum(abs(ref-binaire))/16384*100)

#nom <- "rdf-2-classes-texture-2.png"
#image <- rdfReadGreyImage (nom)
#image <- rdfTextureEcartType(image, 2)
#h <- hist (as.vector (image), breaks = seq (0, 1, 1 / nbins))
#seuil <- 0.46
#binaire <- (seuil-image)>= 0
#display (binaire, "image binaire texture 2")
#print(sum(abs(ref-binaire))/16384*100)

#nom <- "rdf-2-classes-texture-3.png"
#image <- rdfReadGreyImage (nom)
#image <- rdfTextureEcartType(image, 2)
#h <- hist (as.vector (image), breaks = seq (0, 1, 1 / nbins))
#seuil <- 0.35
#binaire <- (seuil-image)>= 0
#display (binaire, "image binaire texture 3")
#print(sum(abs(ref-binaire))/16384*100)

#nom <- "rdf-2-classes-texture-4.png"
#image <- rdfReadGreyImage (nom)
#image <- rdfTextureEcartType(image, 2)
#h <- hist (as.vector (image), breaks = seq (0, 1, 1 / nbins))
#seuil <- 0.3
#binaire <- (seuil-image)>= 0
#display (binaire, "image binaire texture 4")
#print(sum(abs(ref-binaire))/16384*100)

#nom <- "rdf-2-classes-texture-0.png"
#image <- rdfReadGreyImage (nom)

#image2 <- rdfTextureEcartType (image, 2)
# Calcul et affichage de son histogramme
#nbins <- 256
#histo <- rdfCalculeHistogramme2D(image, nbins, image2, nbins)

#display(histo, "histogramme 2D - texture 0 ")

#nom <- "rdf-2-classes-texture-1.png"
#image <- rdfReadGreyImage (nom)

#image2 <- rdfTextureEcartType (image, 2)
# Calcul et affichage de son histogramme
#nbins <- 256
#histo <- rdfCalculeHistogramme2D(image, nbins, image2, nbins)

#display(histo, "histogramme 2D - texture 1 ")

#nom <- "rdf-2-classes-texture-2.png"
#image <- rdfReadGreyImage (nom)

#image2 <- rdfTextureEcartType (image, 2)
# Calcul et affichage de son histogramme
#nbins <- 256
#histo <- rdfCalculeHistogramme2D(image, nbins, image2, nbins)

#display(histo, "histogramme 2D - texture 2 ")

nom <- "img/rdf-2-classes-texture-3.png"
image <- rdfReadGreyImage (nom)

image2 <- rdfTextureEcartType (image, 2)
# Calcul et affichage de son histogramme
nbins <- 256
histo <- rdfCalculeHistogramme2D(image, nbins, image2, 256)

#display(histo, "histogramme 2D - texture 3 ")

#nom <- "rdf-2-classes-texture-4.png"
#image <- rdfReadGreyImage (nom)

#image2 <- rdfTextureEcartType (image, 2)
# Calcul et affichage de son histogramme
#nbins <- 256
#histo <- rdfCalculeHistogramme2D(image, nbins, image2, nbins)

#display(histo, "histogramme 2D - texture 4 ")


 nom <- "img/rdf-2-classes-texture-0.png"
 image <- rdfReadGreyImage (nom)
 image2 <- rdfTextureEcartType (image, 2)
 res <- rdfClassificateur2D(image,image2, 1,-1, -0.1)
 display(res,"texture 0 binarise")

## nom <- "rdf-2-classes-texture-1.png"
## image <- rdfReadGreyImage (nom)
## image2 <- rdfTextureEcartType (image, 2)
## res <- rdfClassificateur2D(image,image2, 1,-1, -0.139)
## display(res,"texture 1 binarise")

## nom <- "rdf-2-classes-texture-2.png"
## image <- rdfReadGreyImage (nom)
## image2 <- rdfTextureEcartType (image, 2)
## res <- rdfClassificateur2D(image,image2, -1,-1, 0.65)
## display(res,"texture 2 binarise")

## nom <- "rdf-2-classes-texture-3.png"
## image <- rdfReadGreyImage (nom)
## image2 <- rdfTextureEcartType (image, 2)
## res <- rdfClassificateur2D(image,image2, -1,-1, 0.68)
## display(res,"texture 3 binarise")

## nom <- "rdf-2-classes-texture-4.png"
## image <- rdfReadGreyImage (nom)
## image2 <- rdfTextureEcartType (image, 2)
## res <- rdfClassificateur2D(image,image2, -1,-1, 0.79)
## display(res,"texture 4 binarise")
