library("EBImage")
## setwd("//home//m1//canda//Cours//M1//S2//RDF//TP//TP10")

## Chargement d'une image en niveaux de gris
rdfReadGreyImage <- function (nom) {
    image <- readImage (nom)
    if (length (dim (image)) == 2) {
        image
    } else {
        channel (image, 'red')
    }
}

nom <- "allFaces.png"
nomGrey <- "allFacesGrey.png"

image <- rdfReadGreyImage(nom)
stackedFaces <- array(0, dim=c(33,40,400))
classes <- array(0, dim=c(400))

nbImages <- length(stackedFaces[1,1,])

for(i in 0:19){
    for(j in 0:19){
        stackedFaces[,,(i * 20 + j + 1)] <- image[(1 + i*33):((i+1)*33), (1+j*40):((1+j)*40)]
    }
}

## Anotation des image par leur classe
for(i in 1:nbImages){
    classes[i] <- (i - 1) %/% 10 + 1
}
nbClasses = max(classes)
##Calcul de l'entropie d'un ensemble
## un ensemble est représenté par un tableau de booléen qui indique les images présente dans le sous ensemble de StackedFaces

entropie <- function(boolTab){

    ens_classes <- classes[boolTab]
    nbElements <- length(ens_classes)
    if(nbElements == 0)
        return(0)
    ## On stock le nombre d'élément par classe dans ce tableau
    nbElementParClasses <- array(0, dim=40)
    ## on compte le nombre d'élément de chaque classe
    for(i in 0:length(ens_classes)){
        nbElementParClasses[ens_classes[i]] <- nbElementParClasses[ens_classes[i]] + 1
    }
    return(-sum(apply(nbElementParClasses/nbElements, 1, FUN=function(x) log2(x^x))))
    
}
## Partage le vecteur de booléen en 2 sous ensemble l'un qui ne contient que des images dont le pixel (x,y) est noir, et l'autre l'inverse
partage <- function(boolTab, x, y){
    boolTab0 <- array(FALSE, length(boolTab))
    boolTab1 <- array(FALSE, length(boolTab))
    
    for(i in 1:length(boolTab)){
        if(boolTab[i] == TRUE){
            if(stackedFaces[x,y,i] == 1){
                boolTab1[i] <- TRUE
            }
            else {
                boolTab0[i] <- TRUE
            }
        }
    }
    
    return(matrix(c(boolTab0, boolTab1), ncol=2, nrow=length(boolTab)))
}

                                        # Fonction qui calcule le gain d'information en fonction du pixel considéré en position [x,y]
gain <- function(boolTab, x, y, H_parent, nb_elem){
    couple <- partage(boolTab, x, y)
    boolTab0 <- couple[,1]
    boolTab1 <- couple[,2]
    
    p0 <- sum(boolTab0 == TRUE)/nb_elem
    p1 <- sum(boolTab1 == TRUE)/nb_elem
    H0 <-  entropie(boolTab0)
    H1 <- entropie(boolTab1)

    return(H_parent - (p0 * H0) - (p1 * H1))
}

## Calcul les gain d'information pour chaque position
genDeltaH <- function(boolTab, coordDejaTest){
    H_parent <- entropie(boolTab)
    nbElement <- sum(boolTab == TRUE)
    deltaH <- matrix(0, nrow = 33, ncol = 40)
    
    for(x in 1:33)
        for(y in 1:40)
            ## si la coordonée a déjà été testé, le gain est mis a 0
            if(coordDejaTest[x,y])
                deltaH[x,y] <- 0
            else
                deltaH[x,y] <- gain(boolTab, x, y, H_parent, nbElement)
    return(deltaH)
}

## renvoi la coordonée du pixel permetant le meilleur partage
findPartagePosition <- function(boolTab, coordDejaTest){
    Dh <- genDeltaH(boolTab, coordDejaTest)
    c <- which(Dh == max(Dh), arr.ind = T)
    return(which(Dh == max(Dh), arr.ind = T))
}

## Vérifie si l'ensemble est pur
isPure <- function(boolTab){
    ens_classes <- classes[boolTab]
    classeRef <-  ens_classes[1]
    for(classe in ens_classes){
        if(classe != classeRef)
            return(FALSE)
    }
    return(TRUE)
    
}

## Crée l'arbre de décision avec l'algorithme ID3
## CoordDejaTest : matrice 33 x 40 (nb pixels) -> la valeur est TRUE si la coord a déjà été testée
## Représentation du Graphe : un noeud = une liste
## Noeud non terminal = (x, y, fils0, fils1)
## Noeud terminal = (classe)

id3 <- function(boolTab, coordDejaTest){
    
    if(length(classes[boolTab])==0){
        return(-1)
    }
    
    ## Si toute les coordonée ont déjà été testé
    if(!(FALSE %in% coordDejaTest)){
        return(classes[boolTab][1])
    }
    ## Si le noeud est pur on crée un noeud terminal
    if(isPure(boolTab)){
        return(classes[boolTab][1])
    }
    
    ## On cherche la position du pixel qui permet la meilleur séparation
    c <- findPartagePosition(boolTab, coordDejaTest)
    x <- c[1,1]
    y <- c[1,2]

    ## On marque que la position du pixel est maintenant utilisé
    coordDejaTest[x,y] <- TRUE
    
    ## Séparation des deux nouveaux ensembles
    c <- partage(boolTab, x, y)
    boolTab0 <- c[,1]
    boolTab1 <- c[,2]

    ##calculer les noeuds fils
    fils0 <- id3(boolTab0, coordDejaTest)
    fils1 <- id3(boolTab1, coordDejaTest)
    
    ## On renvoi le nouveau noeud
    return(list(x, y, fils0, fils1))
}

partition <- function(taux){
    testIdVector <- array(-1, 40 * taux)
    learnBoolTab <- array(TRUE, 400)
    
    k <- 1
    for(i in 0:39){
        for(j in sample(1:10, taux)){
            testIdVector[k] <- i * 10 + j
            learnBoolTab[i * 10 + j] <- FALSE
            k <- k + 1
        }
    }

    return(list(testIdVector, learnBoolTab))
}



## Predis la classe de l'image d'indice image en utilisant l'arbre de décision tree
predict <- function(image, tree){

    ## Si le noeud est terminal, renvoyer la classe
    if(length(tree) == 1)
        return(tree)

    x <- tree[[1]]
    y <- tree[[2]]
    if(stackedFaces[x,y,image] == 0)
        return(predict(image, tree[[3]]))
    return(predict(image, tree[[4]]))
}

validation <- function(tree, testIdVector){
    bad <- 0
    good <- 0
    for(i in testIdVector){
        if(predict(i, tree) == classes[i])
            good <- good + 1
        else
            bad <- bad + 1
    }
    t <- good * 100 / (good + bad)
    return(t)
}

l <- partition(3)
testIdVector <- l[[1]]
boolTab <- l[[2]]
tree <- id3(boolTab, matrix(FALSE, nrow=33, ncol=40))
taux <- validation(tree, testIdVector)
