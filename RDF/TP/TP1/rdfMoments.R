## -----------------------------------------------------------------------
## Extraction d'attributs de forme,
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

## Chargement d'une image en niveaux de gris
rdfReadGreyImage <- function (nom) {
    image <- readImage (nom)
    if (length (dim (image)) == 2) {
        image
    } else {
        channel (image, 'red')
    }
}

## Calcul de la surface d'une forme
rdfSurface <- function (im) {
    sum (im)
}

## Calcul d'un moment geometrique (Mpq)
rdfMoment <- function (im, p, q) {

    ## génère la liste des entier de 1 à dim (im)[1] (
    ## chaque élément est ensuite passé à la puissance 3 (^ applicable sur un tableau)
    ## dim renvoi un tableau contenant les dimensions de l'objet donné en parametre
    ## ici, dim(im)[1] renvoi le nombre de ligne (longueur) de l'image
    x <- (1 : (dim (im)[1])) ^ p
    y <- (1 : (dim (im)[2])) ^ q

    ## as.numeric(x) convertie tout les élém de x en numeric
    ## %*% multiplication de matrice
    ## rbind et cbind : ???
    as.numeric (rbind (x) %*% im %*% cbind (y))
}

## Calcul d'un moment centre
rdfMomentCentre <- function (im, p, q) {
    ## Barycentre
    s <- rdfSurface (im)
    cx <- rdfMoment (im, 1, 0) / s
    cy <- rdfMoment (im, 0, 1) / s
    ## Initialiser les vecteurs x et y
    x <- (1 : (dim (im)[1]) - cx) ^ p
    y <- (1 : (dim (im)[2]) - cy) ^ q
    ## Calcul du moment centre
    as.numeric (rbind (x) %*% im %*% cbind (y))
}


## calcul le moment centré avec la surface et les moments géométrique de l'image pré-calculé et passé en argument de la fonction
rdfMomentCentre2 <- function(im, p, q, s, cx, cy){
    ## Initialiser les vecteurs x et y
    x <- (1 : (dim (im)[1]) - cx) ^ p
    y <- (1 : (dim (im)[2]) - cy) ^ q
    ## Calcul du moment centre
    as.numeric (rbind (x) %*% im %*% cbind (y))
}

rdfMatriceInertie <- function (im){
    ## Barycentre
    s <- rdfSurface (im)
    cx <- rdfMoment (im, 1, 0) / s
    cy <- rdfMoment (im, 0, 1) / s

    m <- matrix(0, nrow=2, ncol=2)
    m[1,1] <- rdfMomentCentre2(im, 2, 0, s, cx, cy)
    m[1,2] <- rdfMomentCentre2(im, 1, 1, s, cx, cy)
    m[2,1] <- rdfMomentCentre2(im, 1, 1, s, cx, cy)
    m[2,2] <- rdfMomentCentre2(im, 0, 2, s, cx, cy)
    m
}
rdfMomentCentreNormalise <- function(im, p, q){
    s <- rdfSurface (im)
    cx <- rdfMoment (im, 1, 0) / s
    cy <- rdfMoment (im, 0, 1) / s
    
    rdfMomentCentre2(im, p, q, s, cx, cy) / ( rdfMomentCentre2(im, 0, 0, s, cx, cy)) ^ (1 + (p + q)/2)
}
## Calcul du moment centré Normalisé en passant la surface et les moments géométrique de l'image pré-calculé et passé en argument de la fonction
rdfMomentCentreNormalise2 <- function(im, p, q, s, cx, cy){
    rdfMomentCentre2(im, p, q, s, cx, cy) / ( rdfMomentCentre2(im, 0, 0, s, cx, cy)) ^ (1 + (p + q)/2)
}

rdfMomentCentreNormaliseOrdre2 <- function (im){
    ## Barycentre
    s <- rdfSurface (im)
    cx <- rdfMoment (im, 1, 0) / s
    cy <- rdfMoment (im, 0, 1) / s

    m <- matrix(0, nrow=2, ncol=2)
    m[1,1] <- rdfMomentCentreNormalise2(im, 2, 0, s, cx, cy)
    m[1,2] <- rdfMomentCentreNormalise2(im, 1, 1, s, cx, cy)
    m[2,1] <- rdfMomentCentreNormalise2(im, 1, 1, s, cx, cy)
    m[2,2] <- rdfMomentCentreNormalise2(im, 0, 2, s, cx, cy)
    ## eigen(m)
    m
}



rdfMomentHu1 <- function(im, s, cx, cy){
    rdfMomentCentreNormalise2(im, 2, 0, s, cx, cy) + rdfMomentCentreNormalise2(im, 0, 2, s, cx, cy)
}

rdfMomentHu2 <- function(im, s, cx, cy){
    (rdfMomentCentreNormalise2(im, 2, 0, s, cx, cy) - rdfMomentCentreNormalise2(im, 0, 2, s, cx, cy)) ^2 - (2 * rdfMomentCentreNormalise2(im, 1, 1, s, cx, cy)) ^ 2
}

rdfMomentHu3 <- function(im, s, cx, cy){
    (rdfMomentCentreNormalise2(im, 3, 0, s, cx, cy) - rdfMomentCentreNormalise2(im, 1, 2, s, cx, cy))^2 + (rdfMomentCentreNormalise2(im, 2, 1, s, cx, cy) - rdfMomentCentreNormalise2(im, 0, 3, s, cx, cy))^2
}

rdfMomentHu4 <- function(im, s, cx, cy){
    (rdfMomentCentreNormalise2(im,3,0,s,cx,cy) + rdfMomentCentreNormalise2(im,1,2,s,cx,cy))^2  + (rdfMomentCentreNormalise2(im,2,1,s,cx,cy) + rdfMomentCentreNormalise2(im,0,3,s,cx,cy))^2
}

rdfMomentHu5 <- function(im, s, cx, cy){
    m1 <- rdfMomentCentreNormalise2(im,3,0,s,cx,cy) - 3*rdfMomentCentreNormalise2(im,1,2,s,cx,cy)
    m2 <-  (rdfMomentCentreNormalise2(im,3,0,s,cx,cy) + rdfMomentCentreNormalise2(im,1,2,s,cx,cy))
    m3 <- (rdfMomentCentreNormalise2(im,3,0,s,cx,cy) + rdfMomentCentreNormalise2(im,1,2,s,cx,cy))^2
    m4 <- 3*(rdfMomentCentreNormalise2(im,2,1,s,cx,cy) + rdfMomentCentreNormalise2(im,0,3,s,cx,cy))^2 
    m5 <- 3*rdfMomentCentreNormalise2(im,2,1,s,cx,cy) - rdfMomentCentreNormalise2(im,0,3,s,cx,cy)
    m6 <- rdfMomentCentreNormalise2(im,2,1,s,cx,cy) + rdfMomentCentreNormalise2(im,0,3,s,cx,cy) 
    m7 <- 3*(rdfMomentCentreNormalise2(im,3,0,s,cx,cy) + rdfMomentCentreNormalise2(im,1,2,s,cx,cy))^2  
    m8 <- (rdfMomentCentreNormalise2(im,2,1,s,cx,cy) + rdfMomentCentreNormalise2(im,0,3,s,cx,cy))^2

    m1*m2*(m3-m4)+(m5*m6*(m7-m8))

}

rdfMomentsInvariants <- function(im){
    s <- rdfSurface (im)
    cx <- rdfMoment (im, 1, 0) / s
    cy <- rdfMoment (im, 0, 1) / s

    h1 <- rdfMomentHu1(im, s, cx, cy)
    h2 <- rdfMomentHu2(im, s, cx, cy)
    h3 <- rdfMomentHu4(im, s, cx, cy)
    h4 <- rdfMomentHu4(im, s, cx, cy)
    h5 <- rdfMomentHu5(im, s, cx, cy)

    
    H <- array(0, dim=5)   
    H[1] <- h1
    H[2] <- h2
    H[3] <- h3
    H[4] <- h4
    H[5] <- h5
    H
}




## renvoi le pourcentage de différence entre 2 matrices
cmpMatrice <- function(m1, m2){
    round((abs(m1 - m2) *100) / m1, 2)
} 

cmpBarycentre <- function(b1, b2){
    cbind(cmpMatrice(b1[1],b2[1]), cmpMatrice(b1[2],b2[2]))
}

## renvoi le pourcentage de différence entre les moments principaux d'inertie (valeurs propres) et les axes principaux d'inertie (vecteurs propres)
cmpEigen <- function(e1, e2){
    valeursPropres <- round(((abs(eigen1$values - eigen2$values)) *100) / eigen1$values, 2)
    vecteursPropres <- round(((eigen1$vectors - eigen2$vectors) *100) / eigen1$vectors, 2)
    cbind(valeursPropres, vecteursPropres)
}
