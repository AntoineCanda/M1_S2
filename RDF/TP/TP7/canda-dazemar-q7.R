library("MASS")
source('q5.R')

###########################
## CHARGEMENT DES DONNÉS ##
###########################
(load('x_app.data'))
(load('x_test.data'))
(load('classe_test.data'))
(load('classe_app.data'))

##########################################
## Caclul des dispersions + covariances ##
##########################################

## moyenne classe1
mean1 <- colMeans(x_app[classe_app==1,])
## covariance intra-classe classe 1
S1 <- cov(x_app[classe_app==1,])

## moyenne classe2
mean2 <- colMeans(x_app[classe_app==2,])
## covariance intra-classe classe 2
S2 <- cov(x_app[classe_app==2,])

## moyenne classe3
mean3 <- colMeans(x_app[classe_app==3,])
## covariance intra-classe classe 3
S3 <- cov(x_app[classe_app==3,])

# moyenne de tous les points 
mean_total <- colMeans(x_app)

#dispersion intra-classe
Sw = S1+S2+S3
## covariance inter-classe
Sb = (mean1-mean_total) %*% t(mean1-mean_total) + (mean2-mean_total) %*% t(mean2-mean_total)+ (mean3-mean_total) %*% t(mean3-mean_total)

################
## QUESTION 7 ##
################

## Résolution equation et obtention de l'axe sur lequel on doit effectuer la projection

invSw= solve(Sw)
invSw_by_Sb= invSw %*% Sb
VecteurPropre<- eigen(invSw_by_Sb)
ScalarProduct_app_ACP <- x_app %*% (VecteurPropre$vectors[,1]) / sqrt(sum(VecteurPropre$vectors[,1]*VecteurPropre$vectors[,1]))


## On projette les points tests sur l'axe trouvé par les données d'apprentissage
ScalarProduct_test_ACP <- x_test%*% (VecteurPropre$vectors[,1]) / sqrt(sum(VecteurPropre$vectors[,1]*VecteurPropre$vectors[,1]))

## On effectue la classification
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

dev.copy(jpeg,'q7.jpg')
dev.off
