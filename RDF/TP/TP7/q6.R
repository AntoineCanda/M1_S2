library("MASS")
source('load.R')
source('lib.R')
source('q5.R')

## Résolution equation
invSw= solve(Sw)
invSw_by_Sb= invSw %*% Sb
VecteurPropre<- eigen(invSw_by_Sb)

## Affichage de la droite correspondant au vecteur propre
## dont la valeur propre la plus élevée

couleur<-rep('red',length(x_app) / 2)
couleur[classe_app==2]='green'
couleur[classe_app==3]='blue'
plot(x_app, col=couleur)

pente <- VecteurPropre$vectors[2,1]/VecteurPropre$vectors[1,1]
abline(a = 0, b = pente, col = "blue")


ScalarProduct_app_ACP <- x_app %*% (VecteurPropre$vectors[,1]) / sqrt(sum(VecteurPropre$vectors[,1]*VecteurPropre$vectors[,1]))

XP <- matrix(x_app, ncol=2)
XP[,1] <- ScalarProduct_app_ACP * VecteurPropre$vectors[1,1]
XP[,2] <- ScalarProduct_app_ACP * VecteurPropre$vectors[2,1]

points(XP[classe_app==1,], col="red")
points(XP[classe_app==2,], col="green")
points(XP[classe_app==3,], col="blue")

dev.copy(jpeg,'q6.jpg')
dev.off
