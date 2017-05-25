source('readUSPS.R')
source('freeman.R')

#setwd("//home//m1//canda//Cours//M1//S2//RDF//TP//TP11")
## setwd("C://Users//antoine//Documents//Cours//M1//S2//RDF//TP//RDF//TP11")

# Fonction pour binariser les images de la base USPS
# Les paramètres sont le dataset contenant les images data et le seuil qui est un flottant compris entre 0.0 et 1.0
# Si la valeur du pixel est >= au seuil , on passe à 1 sinon à 0
binariser <- function(data, seuil){
  data[data >= seuil] <- 1
  data[data < seuil] <- 0
  return(data)
}

# Fonction permettant de calculer les contours de chaque image selon le codage de Freeman pour chaque image de notre ensemble data
# Parametre : data l'ensemble des images binarisées
getCodageFreeman <- function(data) {
  res <- list()
  for(i in 1:length(data[1,1,])){
    res[[i]] <- freeman(data[ , , i])
  }
  
  res
}

# Fonction calculant la distance edit entre les deux parametres data1 et data2
# data1 et data2 ici sont deux contours de freeman obtenus à partir des images de la base USPS binarisées.
# Le résultat est un entier qui est supérieur ou égale à 0
distance_levenshtein <- function(data1, data2){
  #creation de la matrice resultat
  res <- matrix(0, nrow=length(data1)+1,ncol=length(data2)+1)
  
  #remplissage de la premiere ligne et de la premiere colonne
  for(x in 1:(length(data1)+1)){
    res[x,1] <- x-1
  }
  
  for(x in 1:(length(data2)+1)){
    res[1,x] <- x-1
  }
  
  #remplissage de la matrice qui est le min entre les valeurs suivantes :
  #valeur de la case à gauche de la case courante +1 
  #valeur de la case au dessus de la case courante +1 
  #valeur de la case de la diagonale en haut à gauche de la case courante +1 auxquel on soustrait 1 si les valeurs du contour que l'on teste sont égales (0 sinon) 
  
  for(i in 2:(length(data1)+1)){
    for(j in 2:(length(data2)+1)){
      tmp <- res[i-1,j-1]+1 
      if( data1[i-1] == data2[j-1]){
        tmp <- tmp - 1
      }
      
      val <- c(res[i-1,j]+1, res[i,j-1]+1, tmp)
      res[i,j] <- min(val)
    }
  }
  
  #on retourne la valeur finale qui est : res[m,n] avec m = |data1| + 1 et n = |data2| + 1
  res[length(data1)+1,length(data2)+1]
}

## classifieur knn
knn <- function(test, data, label, k){
  #creation tableau des distances de levenstein
  distance <- array(dim=c(1,1,length(data[1,1,])))
  
  #calcul des distances entre le contour de freeman de test et tout les contours de la base d'apprentissage
  for(i in 1:length(data)){
    distance[i] <- distance_levenshtein(test,data[[i]])
  }
  
  #creation de la table des labels resultat
  labels <- array(dim=c(1,1,k))
  #calcul des k classes corresppondants aux k contour les plus proches
  for(i in 1:k){
    #obtention de la position de la distance la plus faible 
    pos_min <- which(distance == min(distance), arr.ind = T)
    #obtention du label correspondant a l'image 
    lab <- label[pos_min]
    # on ajoute le label trouve a la table des labels les plus proche
    labels[i] <- lab
    # on modifie la valeur de la distance de facon arbitraire avec une valeur arbitrairement grande pour ne pas ravoir la meme
    distance[pos_min] <- max(distance)
  }
  
  doublon <- duplicated(labels)
  
  set <- c(doublon[1])
  for(i in 2:k){
    if(doublon[i] == FALSE){
      set <- c(set,doublon[i])
    }
  }
  res <- array(dim=c(1,1,length(set)))
  for(i in 1:length(res)){
    res[i] <- sum(set[i] == doublon)
  }
  
  set[which(res == max(res), arr.ind = T)]
}

reductionSet <- function(data, pourcentage){
    nb_image <- 1100 * pourcentage 
    image <- array(dim=c(16, 16, 10*nb_image));
    labels <- vector(length=0 );  
    for(i in 0:9){
        for(j in 1: nb_image){
          image[,,j] <- data[[1]][,,((i*1100)+j)]
        }
        labels <- c(labels, rep(i, nb_image));
    }
    return(list(image,labels))
}
#################### Main ###################

## Recuperer les donnees
listeImage <- readUSPSdata("usps")

## Separation image / label
data <- listeImage[[1]]
label <- listeImage[[2]] 

## Binarisation des images
img_bin <- binariser(data , 0.2)

## Obtention des codages de freeman de chaque image
codageF <- getCodageFreeman(img_bin)

