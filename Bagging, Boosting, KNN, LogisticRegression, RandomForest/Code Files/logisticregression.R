## these librarys should be loaded
library(caret)
library(RANN)
library(stats)
library(glmnet)
library(class)
# Load the dataset
ionosphere <- read.csv("http://archive.ics.uci.edu/ml/machine-learning-databases/ionosphere/ionosphere.data", header=FALSE)
ionosphere$V35 <- ifelse(ionosphere$V35 == "g", 1, ifelse(ionosphere$V35 == "b", 0, ""))
trainIndex <- sample(1:nrow(ionosphere), 0.8 * nrow(ionosphere))
train <- ionosphere[trainIndex, ]
test <- ionosphere[-trainIndex, ]
train_label <-train[1:280,35]
test_label <-test[1:71,35]
# used package to create 10 folds crodd validation
ctrl <- trainControl(method="cv", number=300)
# create LOGISTIC REGRESSION model
mod_fit <- train(V35~., data=train, method="glm", family="binomial",trControl = ctrl)
pred = predict(mod_fit, test)
confusionMatrix(pred, test$V35)
cm<-table(pred,test[,35]) 
y<-(diag(cm))/(sum(cm))
Z<-sum(y)
print(Z*100)
library(pROC)
f1 = roc(V35~V34, data=train) 
f1
plot(f1, col="red")
