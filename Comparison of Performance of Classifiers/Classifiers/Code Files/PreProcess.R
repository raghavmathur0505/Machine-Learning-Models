install.packages("caret")
library(caret)
install.packages("RANN")
library(RANN)
#loading the dataset
ionosphere <- read.csv("http://archive.ics.uci.edu/ml/machine-learning-databases/ionosphere/ionosphere.data", header=FALSE)
#Replacing class value "g" with "1" and "b" with "0" for scaling and ease of use
ionosphere$V35 <- ifelse(ionosphere$V35 == "g", 1, ifelse(ionosphere$V35 == "b", 0, ""))
maxs = apply(ionosphere, MARGIN = 2, max)
mins = apply(ionosphere, MARGIN = 2, min)
#scaling data
scaled = as.data.frame(scale(ionosphere, center = TRUE, scale = TRUE))
scaled[is.na(scaled)] <- 0
iiii<-scaled
