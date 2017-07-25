library(e1071)
args <- commandArgs(trailingOnly = TRUE)

if (length(args) != 2) {
  stop("Please give two arguments the Training Set and the Test Set", call. = FALSE)
}

train.win <- read.delim(args[1])
train <- as.data.frame(train.win)
train[is.na(train)] <- 0

test.win <- read.delim(args[2])
test <- as.data.frame(test.win)
test[is.na(test)] <- 0

colNames <- colnames(train, do.NULL = TRUE, prefix = "col")
colnames(train)[length(colNames)] <- "class"
colnames(test)[length(colNames)] <- "class"
NBmodel <- naiveBayes(as.factor(class)~., data = train, threshold=0.01, laplace = 1)
NBPrediction <- predict(NBmodel, train)
accuracy <- 100*sum(NBPrediction == train$class)/length(NBPrediction)

NBPrediction_test <- predict(NBmodel, test)
accuracy_test <- 100*sum(NBPrediction_test == test$class)/length(NBPrediction_test)

D <- list()
for(i in 1:length(colNames)) {
  D[[i]] <- unique(train[i])
}
for(j in 1:length(D[[length(colNames)]][[1]])) {
  cat("P(",colNames[length(colNames)],"=",D[[length(colNames)]][[1]][j],")=",NBmodel$apriori[j]/nrow(train),"\t")
  for(k in 1:length(NBmodel$tables)) {
    for(l in 1:length(D[[1]][[1]])) {
      cat("P(",colNames[k],"=",rev(D[[1]][[1]])[l],"|",colNames[length(colNames)],"=",D[[length(colNames)]][[1]][j],")=")
      cat(NBmodel$tables[[k]][j,l][[1]],"\t")
    }
  }
  cat("\n")
  cat("\n")
}
cat("Accuracy on training set(",nrow(train),"instances)=",accuracy,"\n")
cat("Accuracy on test set(",nrow(test),"instances)=",accuracy_test)