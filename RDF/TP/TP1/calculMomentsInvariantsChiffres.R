## Chargement des fonctions externes
library ("EBImage")
setwd("~/Cours/M1/S2/RDF/TP/RDF/TP1/")

source ("rdfMoments.R")

calcul <- function(path){
    img <- rdfReadGreyImage(path)
    rdfMomentsInvariants(img)
}

cat("0 : ", calcul("img/rdf-chiffre-0.png"), "\n")
cat("1 : ", calcul("img/rdf-chiffre-1.png"), "\n")
cat("2 : ", calcul("img/rdf-chiffre-2.png"), "\n")
cat("3 : ", calcul("img/rdf-chiffre-3.png"), "\n")
cat("4 : ", calcul("img/rdf-chiffre-4.png"), "\n")
cat("5 : ", calcul("img/rdf-chiffre-5.png"), "\n")
cat("6 : ", calcul("img/rdf-chiffre-6.png"), "\n")
cat("7 : ", calcul("img/rdf-chiffre-7.png"), "\n")
cat("8 : ", calcul("img/rdf-chiffre-8.png"), "\n")
cat("9 : ", calcul("img/rdf-chiffre-9.png"), "\n")
