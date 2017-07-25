install.packages("caret")
library(caret)
install.packages("RANN")
library(RANN)
ionosphere <- read.csv("http://archive.ics.uci.edu/ml/machine-learning-databases/ionosphere/ionosphere.data", header=FALSE)
ionosphere$V35 <- ifelse(ionosphere$V35 == "g", 1, ifelse(ionosphere$V35 == "b", 0, ""))
maxs = apply(ionosphere, MARGIN = 2, max)
mins = apply(ionosphere, MARGIN = 2, min)
scaled = as.data.frame(scale(ionosphere, center = TRUE, scale = TRUE))
scaled[is.na(scaled)] <- 0
iiii<-scaled

