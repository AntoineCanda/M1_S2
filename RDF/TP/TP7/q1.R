source('load.R')
source('lib.R')

visualisation(x_test, classe_test)
dev.copy(jpeg,'q1_test.jpg')
dev.off
