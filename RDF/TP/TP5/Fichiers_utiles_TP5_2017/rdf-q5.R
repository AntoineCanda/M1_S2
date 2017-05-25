# Chargement des fonctions externes
library ("EBImage")
# Répertoire de travail
#setwd("//home//m1//canda//Cours//M1//S2//RDF//TP//TP5//Fichiers_utiles_TP5_2017")
setwd("C://Users//antoine//Documents//Cours//M1//S2//RDF//TP//RDF//TP5//Fichiers_utiles_TP5_2017")

source ("rdfSegmentation.R")

# Chargement de l'image 1
nom <- "rdf-chiffre-1-8bits-seuillage-auto.png"
image1 <- rdfReadGreyImage (nom)

nom <- "rdf-chiffre-1-8bits_classe_a_trouver.png" 
image2 <- rdfReadGreyImage (nom)


# Calcul et affichage de son histogramme
nbins <- 256
h1 <- hist (as.vector (image1), freq=FALSE, breaks = seq (0, 1, 1 / nbins))

h2 <- hist (as.vector (image2), freq=FALSE, breaks = seq (0, 1, 1 / nbins))

N <- sum(h1$counts[0:256])
N1 <- h1$counts[0:256] 
N2 <- h2$counts[0:256]
print(N)
print(N1)
print(N2)

Erreur <- (h1$density[1]/h2$density[1])+(h1$density[256]/h2$density[256])
print(Erreur)
