## these librarys should be loaded
library(rpart)
library(caret)
library(adabag)
library(ipred)
  
train<-digit_train
test<-digit_test

# used package to create 10 folds crodd validation
training_set <- trainControl(method="cv", number=2)

# create bagging model
x<-train[1:10000,]
train_bag <- train(label~ ., data=train, method="treebag", trControl=training_set, trace(TRUE))
BAG_ACCURACY <-  100*(train_bag$results$Accuracy)

predictions <-data.frame(ImageId=1:nrow(test), Label=levels(labels)[train_bag$train$predicted])
table(predictions)
write.csv(predictions, file="rf_benchmark4.csv", row.names=F)
library(pROC)
f1 = roc(V35~V34, data=dataset) 
f1
plot(f1, col="red")

   "pred <- prediction(Class1,pred)
    perf <- performance(pred, "tpr", "fpr")
    plot(perf, colorize=T, add=TRUE)
    abline(0,1)"



2
3
4
5
6
7
8
9
10
11
12
13
14
## rpart library should be loaded
library(rpart)
#data(iris)
train<-digit_train
test<-digit_test
digit.adaboost <- boosting(label~., data=train, boos=TRUE, mfinal=6)
importanceplot(iris.adaboost)

sub <- c(sample(1:50, 35), sample(51:100, 35), sample(101:150, 35))
iris.bagging <- bagging(Species ~ ., data=iris[sub,], mfinal=10)
#Predicting with labeled data
iris.predbagging<-predict.bagging(iris.bagging, newdata=iris[-sub,])
iris.predbagging
#Predicting with unlabeled data
iris.predbagging<- predict.bagging(iris.bagging, newdata=iris[-sub,-5])
iris.predbagging