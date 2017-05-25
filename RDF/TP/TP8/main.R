library(EBImage)

rdfReadGreyImage <- function (nom) {
  image <- readImage (nom)
  if (length (dim (image)) == 2) {
    image
  } else {
    channel (image, 'red')
  }
}

# Conversion en une matrice des donnees de taille 16384 x 2
nom1 <- "rdf-2-classes-texture-1.png"
nom2 <- "rdf-2-classes-texture-1-text.png"
image1 <- rdfReadGreyImage(nom1)
image2 <- rdfReadGreyImage(nom2)

imagev<-cbind(as.vector (image1),as.vector (image2))


# Conversion en une matrice des donnees de taille 16384 x 2
ikm <- kmeans(imagev, 2, iter.max = 30)


couleur<-rep('green', length(ikm$cluster))
couleur[ikm$cluster==2]='blue'

centers_aff <- cbind(ikm$centers[,1],ikm$centers[,2])
##plot(ikm$centers, col=couleur)
plot(imagev, col=couleur)
points(centers_aff, col="red")

distance_cdg1 <- ((image1- ikm$centers[1,1])*(image1- ikm$centers[1,1])) + ((image2- ikm$centers[2,1])*(image2- ikm$centers[2,1]))
distance_cdg2 <- ((image1- ikm$centers[1,2])*(image1- ikm$centers[1,2])) + ((image2- ikm$centers[2,2])*(image2- ikm$centers[2,2]))

#<-matrix(0,nrow=128,ncol=128)
#for(i in 1:128){
#  for(j in 1:128){
#    if(distance_cdg1[i,j] > distance_cdg2[i,j]){
#      m[i,j] <- 1
#    }
#  }
#}
#display(as.Image(m))

nomMasque <- "img/rdf-masque-ronds.png"
masque <- rdfReadGreyImage(nomMasque)

nomSegmente <- "img/rdf-image-segmente.png"
segmente <- rdfReadGreyImage(nomSegmente)

imageDiffe <- segmente - masque

hDiff <- hist(as.vector(imageDiffe))
nbPixelDiff <- hDiff$counts[256]
print(nbPixelDiff)
print(nbPixelDiff*100/sum(hDiff$counts))

nomDiff <- "img/rdf-diff.png"
imageDiff <- rdfReadGreyImage(nomDiff)
h <- hist (as.vector (imageDiff), breaks = seq (0, 1, 1 / 256))

nbPixel <- h$counts[256]

#Affichage du pourcentage
print(nbPixel)
print(nbPixel*100/sum(h$counts))
