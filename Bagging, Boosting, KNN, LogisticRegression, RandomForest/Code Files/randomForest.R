## these librarys should be loaded
library(class)
library(caret)
library(randomForest)
# Load the dataset
ionosphere <- read.csv("http://archive.ics.uci.edu/ml/machine-learning-databases/ionosphere/ionosphere.data", header=FALSE)
trainIndex <- sample(1:nrow(ionosphere), 0.8 * nrow(ionosphere))
train <- ionosphere[trainIndex, ]
test <- ionosphere[-trainIndex, ]
train_label <-train[1:280,35]
test_label <-test[1:71,35]
# used package to create 10 folds crodd validation
# create RANDOM FOREST model
rf_model<-train(V35~.,data=train, method="rf", trControl=trainControl(method="cv",number=5), prox=TRUE, allowParallel=TRUE)
print(rf_model)
pred = predict(rf_model, test)
confusionMatrix(data=pred, test_label)
cm<-table(pred,test_label) 
y<-(diag(cm))/(sum(cm))
Z<-sum(y)
print(Z*100)
library(pROC)
f1 = roc(V35~V34, data=train) 
f1
plot(f1, col="red")