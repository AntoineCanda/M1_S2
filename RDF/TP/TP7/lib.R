visualisation <- function(x, classes){
    couleur<-rep('red',length(x) / 2)
    couleur[classes==2]='green'
    couleur[classes==3]='blue'
    plot(x, col=couleur)
}
