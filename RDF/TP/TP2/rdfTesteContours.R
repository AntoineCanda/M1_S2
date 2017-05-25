## -----------------------------------------------------------------------
## Extraction d'attributs de contours,
## Module RdF, reconnaissance de formes
## Copyleft (C) 2014, Universite Lille 1
##
## This program is free software: you can redistribute it and/or modify
## it under the terms of the GNU General Public License as published by
## the Free Software Foundation, either version 3 of the License, or
## (at your option) any later version.
##
## This program is distributed in the hope that it will be useful,
## but WITHOUT ANY WARRANTY; without even the implied warranty of
## MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
## GNU General Public License for more details.
##
## You should have received a copy of the GNU General Public License
## along with this program.  If not, see <http://www.gnu.org/licenses/>.
## -----------------------------------------------------------------------

## Chargement des fonctions externes
library ("EBImage")
setwd("C://Users//antoine//Documents//Cours//M1//S2//RDF//TP//RDF//TP2//")
source ("rdfContours.R")

## Chargement d'un contour
nom <- "img/rdf-cercle-80.txt"
cont <- rdfChargeFichierContour (nom)

# Affichage de cont 
print(cont)
print(fft(cont))
# Afficher le contour
plot (cont, main = nom, type = "o", asp = 1, col = "red", ylim =  rev (range (Im (cont))))
lines(cont[seq(0,80, 4)], col="blue")
lines(cont[seq(0,80, 8)], col="green")



## Chargement d'un contour
nom <- "img/rdf-carre-80.txt"
cont <- rdfChargeFichierContour (nom)

# Affichage de cont 
print(cont)

## Afficher le contour
plot (cont, main = nom, type = "o", asp = 1, col = "black", ylim = rev (range (Im (cont))))

y <- fft(cont)
##y[1] <-y[1] + complex(real = 50, imaginary = 50)
##lines(y, main = nom, type = "o", asp=1, col = "red", ylim = rev (range(Im(cont))))
lines(fft(y,inverse=TRUE)/80,  main = nom, type = "o", asp=1, col = "red", ylim = rev (range(Im(cont))))
lines(fft(rdfAnnuleDescFourrier(fft(cont),0.0),inverse=TRUE)/80,  main = nom, type = "o", asp=1,col="orange")
lines(fft(rdfAnnuleDescFourrier(fft(cont),1.0),inverse=TRUE)/80,  main = nom, type = "o", asp=1,col="green")
lines(fft(rdfAnnuleDescFourrier(fft(cont),0.5),inverse=TRUE)/80,  main = nom, type = "o", asp=1,col="blue")
lines(fft(rdfAnnuleDescFourrier(fft(cont),0.8),inverse=TRUE)/80,  main = nom, type = "o", asp=1,col="red")
lines(fft(rdfAnnuleDescFourrier(fft(cont),0.2),inverse=TRUE)/80,  main = nom, type = "o", asp=1,col="yellow")



## Chargement d'un contour

nom <- "img/rdf-cercle-80.txt"
cont <- rdfChargeFichierContour (nom)

# Affichage de cont 
print(cont)

# Afficher le contour
plot (cont, main = nom, type = "o", asp = 1, col = "black", ylim = rev (range (Im (cont))))
rdfAlgorithmeCorde(cont, dmax=1)
lines(rdfAlgorithmeCorde(cont, dmax=1),col="blue")
lines(rdfAlgorithmeCorde(cont,dmax=0.5), col="green")

nom <- "img/rdf-carre-20.png"
img <- rdfReadGreyImage(nom)
cont <- rdfContour(img)
plot (cont, main = nom, type = "o", asp = 1, col = "black", ylim = rev (range (Im (cont))))
lines(rdfAlgorithmeCorde(cont, dmax=1),col="blue")
lines(rdfAlgorithmeCorde(cont,dmax=0.5), col="green")
lines(fft(rdfAnnuleDescFourrier(fft(cont),0.5),inverse=TRUE)/length(cont),  main = nom, type = "o", asp=1,col="red")

nom <- "img/rdf-croix.png"
img <- rdfReadGreyImage(nom)
cont <- rdfContour(img)
plot (cont, main = nom, type = "o", asp = 1, col = "black", ylim = rev (range (Im (cont))))
lines(rdfAlgorithmeCorde(cont, dmax=1),col="blue")
lines(rdfAlgorithmeCorde(cont,dmax=0.5), col="green")
lines(fft(rdfAnnuleDescFourrier(fft(cont),0.5),inverse=TRUE)/length(cont),  main = nom, type = "o", asp=1,col="red")

nom <- "img/rdf-patate.png"
img <- rdfReadGreyImage(nom)
cont <- rdfContour(img)
plot (cont, main = nom, type = "o", asp = 1, col = "black", ylim = rev (range (Im (cont))))
lines(rdfAlgorithmeCorde(cont, dmax=1),col="blue")
lines(rdfAlgorithmeCorde(cont,dmax=0.5), col="green")
lines(rdfAlgorithmeCorde(cont,dmax=0.25), col="yellow")
lines(fft(rdfAnnuleDescFourrier(fft(cont),1.0),inverse=TRUE)/length(cont),  main = nom, type = "o", asp=1,col="red")
lines(fft(rdfAnnuleDescFourrier(fft(cont),0.8),inverse=TRUE)/length(cont),  main = nom, type = "o", asp=1,col="blue")

nom <- "img/rdf-triangle-20.png"
img <- rdfReadGreyImage(nom)
cont <- rdfContour(img)
plot (cont, main = nom, type = "o", asp = 1, col = "black", ylim = rev (range (Im (cont))))
lines(rdfAlgorithmeCorde(cont, dmax=1),col="blue")
lines(rdfAlgorithmeCorde(cont,dmax=0.5), col="green")
lines(fft(rdfAnnuleDescFourrier(fft(cont),0.5),inverse=TRUE)/length(cont),  main = nom, type = "o", asp=1,col="red")

nom <- "img/rdf-rectangle-horizontal.png"
img <- rdfReadGreyImage(nom)
cont <- rdfContour(img)
plot (cont, main = nom, type = "o", asp = 1, col = "black", ylim = rev (range (Im (cont))))
lines(rdfAlgorithmeCorde(cont, dmax=1),col="blue")
lines(rdfAlgorithmeCorde(cont,dmax=0.5), col="green")
lines(fft(rdfAnnuleDescFourrier(fft(cont),0.5),inverse=TRUE)/length(cont),  main = nom, type = "o", asp=1,col="red")
