library("MASS")
library("lattice")

load(file='simul-2017.Rdata')

def repartition <-  function() {
    res <- c(length(classe_app[classe_app == 1])/n_app,
             length(classe_app[classe_app == 2])/n_app,
             length(classe_app[classe_app == 3])/n_app)
    res
}

def moyenne <- function(classe, nb_attr){
    M <- seq(1,2)
    for( attr in 1 : nb_attr){
        M[attr] = mean(x_app[classe_app==classe,attr])
    }
    return(M)
}

def covariance <- function(classe, nb_attr){
    Sigma <- matrix(1,2,2)

    for( i in 1 : nb_attr){
        for( j in 1 : nb_attr){
            Sigma[i,j]=cov(as.vector(x_app[classe_app==classe,i]),
                           as.vector(x_app[classe_app==classe,j]))
        }
    }
    return(Sigma)
}

def visualisation <- function(x, classes){
    couleur<-rep('red',length(x) / 2)
    ## couleur[classe_app==1]='red'
    couleur[classes==2]='blue'
    couleur[classes==3]='green'
    plot(x, col=couleur)

    m <- moyenne(1,2)
    points(m[1], m[2], ,pch = 10, col='red')

    m <-  moyenne(2,2)
    points(m[1], m[2], ,pch = 10, col='blue')

    m <-  moyenne(3,2)
    points(m[1], m[2], ,pch = 10, col='green')
}


def comparaison_covariance <- function(){

    result <- matrix(1,3,2)
    ecart_type<- matrix(c(s1, s2, s3), 2, 3)
    for(classe in seq(1,3)){
        cov = covariance(classe, 2)
        s1_calcul = c(cov[1,1], cov[2,2])
        
        diff = abs((ecart_type[,classe] ^ 2) - s1_calcul)
        t <- ((100 * diff)/ (ecart_type[,classe] ^ 2))
        result[classe, 1] <- t[1]
        result[classe, 2] <- t[2]
    }
    return(result)
}

## Affiche la séparation entre la classe donné en argument grace à la fonction f sur le plot donné en argument. la couleur de la séparation est color
def separation <- function(x, classe, plot, color, f){
    replayPlot(plot)
    if(classe == 1){
        c1 <- 2
        c2 <- 3
    }
    if(classe == 2){
        c1 <- 1
        c2 <- 3
    }
    if(classe == 3){
        c1 <- 1
        c2 <- 2
    }

    xp1<-seq(min(x[,1]),max(x[,1]),length=50)
    xp2<-seq(min(x[,2]),max(x[,2]),length=50)
    
    grille<-expand.grid(x1=xp1,x2=xp2)
    grille=cbind(grille[,1],grille[,2])
    classifier <- f(x_app,classe_app)
        
    Zp<-predict(classifier,grille)
    zp<-Zp$post[,classe]-pmax(Zp$post[,c1],Zp$post[,c2])
    contour(xp1,xp2,matrix(zp,50),add=TRUE,levels=0,drawlabels=FALSE, col=color)
}

## Affiche la séparation entre la les classes de l'ensemble x grâce à la fonction f sur le plot donné en argument. la couleur de la séparation est color
def affichage_separation<- function(x, f, color, plot){
    separation(x, 1, plot, color, f)
    plot = recordPlot()
    separation(x, 2, plot, color, f)
}

## Affiche les lignes de séparation entre les classes sur l'ensemble x, dont les classes de chaque points sont passées en arguments.
## Les lignes de séparations sont tracées en bleu pour l'analyse linéaire et en rouge pour l'analyse quadratique
def double_separation <- function(x, classes){
    visualisation(x, classes)
    plot = recordPlot()

    affichage_separation(x, lda, 'blue', plot)
    plot = recordPlot()
    affichage_separation(x, qda, 'red', plot)
}

def taux_classification <- function(x, f){
    classifieur <- f(x_app,classe_app)
    assigne_test<-predict(classifieur, newdata=x)
    table_classification_test <-table(classe_test, assigne_test$class)
    ## table of correct class vs. classification
    diag(prop.table(table_classification_test, 1))
    ## total percent correct
    taux_bonne_classif_test <-sum(diag(prop.table(table_classification_test)))
    taux_bonne_classif_test
}

def tracer_classification <- function(x, f){
    couleur<-rep('red',length(x) / 2)
    classifieur <- f(x_app,classe_app)
    assigne_test<-predict(classifieur, newdata=x)
    shape<-rep(1,n_test) ;
    shape[assigne_test$class==2]=2;
    shape[assigne_test$class==3]=3;

    plot(x,col=couleur,pch=shape,xlab = "X1", ylab = "X2")
}
