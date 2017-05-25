x <- seq(0,100)
y <- 1/(x + (-1)^x + sqrt(x))
y[34] <- 0
y[60] <- 0
y[67] <- 0.05
y[50] <- 0.1
df = data.frame(x,y) 
write.table(df, "data.csv", sep=";", row.names=FALSE)
plot(x,y)
dev.copy(png,'data.png')
dev.off()
