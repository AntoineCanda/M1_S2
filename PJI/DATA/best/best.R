args <- commandArgs(trailingOnly = TRUE)
fileName <- args[1]
threshold <- args[2]

data <- read.table(fileName, header=FALSE,sep=" ")
size <- nrow(data)
windowSize <- size / 10


conv <- function(){
    for (i in 2:(size - windowSize)){
        ## cat(i, ":", i+windowSize, " = ")
        if(IQR(data.matrix(data[i:(i+windowSize), 2])) < threshold){
            return(i)
        }
    }
    return(size)
}

specify_decimal <- function(x, k) format(round(x, k), nsmall=k)

beginConvergence <- conv()
accuracy <- specify_decimal(mean(sort(data.matrix(data[, 2]), TRUE)[1:10]), 5)
cat(fileName,";",beginConvergence,";",accuracy,"\n", sep="")
