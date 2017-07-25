## these librarys should be loaded
library(class)
# Load the dataset
ionosphere <- read.csv("http://archive.ics.uci.edu/ml/machine-learning-databases/ionosphere/ionosphere.data", header=FALSE)
trainIndex <- sample(1:nrow(ionosphere), 0.8 * nrow(ionosphere))
train <- ionosphere[trainIndex, ]
test <- ionosphere[-trainIndex, ]
train_label <-train[1:280,35]
test_label <-test[1:71,35]
library(caret)
# used package to create 10 folds crodd validation
train_control <- trainControl(method="repeatedcv", number=10, repeats=3)
# create KNN model
model <- train(V35~V34 + V33 + V32 + V31 + V30 + V29 + V28 + V27 + V26 + V25 + V24 + V23 + V22 + V21 + V20 + V19 + V18 + V17 + V16 + V15 + V14 + V13 + V12 + V11 + V10+ V9 + V8 + V7 + V6, data=train, trControl=train_control, method="knn")
print(model)
pred = predict(model, test)
confusionMatrix(data=pred, test_label)
cm<-table(pred,test[,35]) 
y<-(diag(cm))/(sum(cm))
Z<-sum(y)
print(Z*100)
library(pROC)
f1 = roc(V35~V34, data=train) 
f1
plot(f1, col="red")

