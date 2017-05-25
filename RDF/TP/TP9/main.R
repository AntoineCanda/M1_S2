#setwd("//home//m1//canda//Cours//M1//S2//RDF//TP//TP9")
library(igraph)

# Chargement de la base de noms d'animaux
source ("rdfAnimaux.txt")

print(noms)

matLettre <- function(noms){
  n <- length(noms)
 
  str2int <- function(x) { strtoi(charToRaw(x),16L)-96 }
  
  mat = matrix(rep(0,26*n),nrow=26, ncol=n);
  for (i in 1:n)
  {
    c = str2int(noms[i]);
    mat[c,i] <- 1;
  }
  
  mat
}

mat <- matLettre(noms)
mat
# Somme des lignes permettant de savoir combien de mots possedent la lettre en position k
rowSums(mat)


entropie <- function(indiceLettre, noms){
  n <- length(noms)
  mat <- matLettre(noms)
  occurence <- rowSums(mat)
  n1 <- occurence[indiceLettre]

  res <- log2((n1/n)^(-n1/n))-log2(((n-n1)/n)^((n-n1)/n))
  res
}

entropies <- function(noms){
  res <- matrix(0,nrow = 1, ncol = 26)
  for(i in 1:26){
    res[1,i] <- entropie(i, noms)
  }
  res
}

res <- entropies(noms)
res

indiceLettre <- function(resEntropies){
  indice <- which.max(resEntropies)
  indice
}

indice <- indiceLettre(res)
indice

partage <- function(noms){
  mat <- matLettre(noms)
  entropies <- entropies(noms)
  indice <- indiceLettre(entropies)
  part1 <- c()
  part2 <- c()
  
  for(i in 1: length(noms)){
   if(mat[indice,i] == 1){
     part1<-c(part1,noms[i])
   }
   else{
     part2 <- c(part2,noms[i])
   }
  }
  
  return(list('indice_lettre'=indice,'partition1'=part1,'partition2'=part2))
}

test <- partage(noms)
test

partageIt <- function(noms, val1=1, val2=0.01){
  ens <- noms
  ss_ens <- partage(noms)
  if(((length(noms))>val1) && (entropie(ss_ens$'indice_lettre', noms)>val2)){
    part1 <- partageIt(ss_ens$partition1,val1,val2)
    part2 <- partageIt(ss_ens$partition2,val1,val2)
    ens<-list(ss_ens$'indice_lettre',part1,part2)
  }
  return (ens);
}

ens <- partageIt(noms,1,0.01)
ens

pendu <- function(ens){
  if(length(ens) == 1)
    cat("reponse : ", ens[1])
  else{
    cat("est ce que le mot contient la lettre : ", intToUtf8(ens[[1]] + 96))
    reponse <- scan(file="", what="character", nmax=1)
    if(reponse == "o")
      pendu(ens[[2]])
    else
      pendu(ens[[3]])
  }
}

aux_graph <- function(ens, g){
  
  origine = vcount(g)
  
  ## Cas ou l'on est au bout de l'arbre
  if(length(ens) == 1){
    g2 = add.vertices(g, 1, label=ens[1])
    g3 = add.edges(g2, list(origine, vcount(g2)))
    return(g3)
  }
  g2 = add.vertices(g, 1, label=intToUtf8(ens[[1]] + 96))
  g3 = add.edges(g2, list(origine, vcount(g2)))
  g4 = aux_graph(ens[[2]], g3)
  g5 = aux_graph(ens[[3]], g4)
  return(g5)
}

display_graph <- function(ens){
  g <- graph.empty(1,2)
  g2 = aux_graph(ens, g)
  layout <- layout_as_tree(g2, root = 1, rootlevel = c(0,0), mode = "out")

  plot(g2, layout ,vertex.color = "red", vertex.size = 10, vertex.label.dist=0)
}

display_graph(ens)
