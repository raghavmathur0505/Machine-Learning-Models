## rpart,caret,adabag,ippred library should be loaded
library(rpart)
library(caret)
library(adabag)
library(ipred)

# Load the dataset
Ionosphere <- read.csv("http://archive.ics.uci.edu/ml/machine-learning-databases/ionosphere/ionosphere.data", header=FALSE)
dataset <- Ionosphere
dataset <- dataset[,-2]
dataset$V1 <- as.numeric(as.character(dataset$V1))
dataset$V35 <- ifelse(dataset$V35 == "g", 1, ifelse(dataset$V35 == "b", 0, ""))
# used package to create 10 folds crodd validation
training_set <- trainControl(method="cv", number=10)
Grid <- expand.grid(maxdepth=25,mfinal=10, coeflearn="Zhu")
# create boosting model
train_aboost <- train(V35~ ., data=dataset, method = "AdaBoost.M1", trControl = training_set,tuneGrid=Grid)
ADABOOST_ACCURACY <- 100*(train_aboost$results$Accuracy)
ADABOOST_ACCURACY
library(pROC)
f1 = roc(V35~V34, data=dataset) 
f1
plot(f1, col="red")