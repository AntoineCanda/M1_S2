                                        #Covariance

source('load.R')
source('lib.R')

## Calcul de la matrice de covariance et des vecteurs propres de l'ensemble d'apprentissage
Covariance <- cov(x_app)
VecteurPropre<- eigen(Covariance)

couleur<-rep('red',length(x_app) / 2)
couleur[classe_app==2]='green'
couleur[classe_app==3]='blue'
plot(x_app, col=couleur)

## # Affichage de la droite correspondant au vecteur propre
## # dont la valeur propre la plus elevee

pente <- VecteurPropre$vectors[2,1]/VecteurPropre$vectors[1,1]
abline(a = 0, b = pente, col = "blue")


## Projection des points sur la droite
ScalarProduct_app_ACP <- x_app %*% (VecteurPropre$vectors[,1]) / sqrt(sum(VecteurPropre$vectors[,1]*VecteurPropre$vectors[,1]))

XP <- matrix(x_app, ncol=2)
XP[,1] <- ScalarProduct_app_ACP * VecteurPropre$vectors[1,1]
XP[,2] <- ScalarProduct_app_ACP * VecteurPropre$vectors[2,1]


points(XP[classe_app==1,], col="red")
points(XP[classe_app==2,], col="green")
points(XP[classe_app==3,], col="blue")

dev.copy(jpeg,'q2.jpg')
dev.off
