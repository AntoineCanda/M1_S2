library("MASS")
source('load.R')
source('lib.R')

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
