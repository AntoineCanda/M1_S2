library("MASS")
source('load.R')
source('lib.R')


# Axe le plus discriminant déterminé par l'ensemble d'apprentissage
Covariance <- cov(x_app)
VecteurPropre<- eigen(Covariance)

ScalarProduct_app_ACP <- x_app %*% (VecteurPropre$vectors[,1]) / sqrt(sum(VecteurPropre$vectors[,1]*VecteurPropre$vectors[,1]))

# Données de tests que l'on projette selon l'axe le plus discriminant trouvé 
ScalarProduct_test_ACP <- x_test%*% (VecteurPropre$vectors[,1]) / sqrt(sum(VecteurPropre$vectors[,1]*VecteurPropre$vectors[,1]))

# Classification via lda
x_app_ACP.lda<-lda(ScalarProduct_app_ACP,classe_app)
assigne_test<-predict(x_app_ACP.lda, newdata=ScalarProduct_test_ACP)

# Estimation des taux de bonnes classifications
table_classification_test <-table(classe_test, assigne_test$class)

# table of correct class vs. classification
diag(prop.table(table_classification_test, 1))
# total percent correct
taux_bonne_classif_test <-sum(diag(prop.table(table_classification_test)))
# couleur de la classe 1 LABEL ORIGINAL
couleur<-rep("red",length(x_test)) ;
couleur[classe_test==2]='green'
couleur[classe_test==3]='blue'
# forme de la classe 1 LABEL ASSIGNATION
shape<-rep(1,length(x_test)) ;
shape[assigne_test$class==2]=2
shape[assigne_test$class==3]=3
# Affichage des projections apprentissage classés
plot(x_test,col=couleur,pch=shape,xlab = "X1", ylab = "X2")

dev.copy(jpeg,'q3.jpg')
dev.off
