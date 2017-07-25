install.packages("e1071", dependencies = T)
library(e1071)
require(e1071)
iiii<-scaled
#sampling data for traing and testing
trainIndex <- sample(1:nrow(iiii),0.8*nrow(iiii))
train <- iiii[trainIndex, ]
test <- iiii[-trainIndex, ]
#creating SVM model
f <- V35 ~ V34 + V33 + V32 + V31 + V30 + V29 + V28 + V27 + V26 + V25 + V24 + V23 + V22 + V21 + V20 + V19 + V18 + V17 + V16 + V15 + V14 + V13 + V12 + V11 + V10 + V9 + V8 + V7 + V6 + V5 + V4 + V3  + V1
svm.model <- svm(f, data = trainset, cost=5, gamma=0.2020, type= "C-classification")
summary(svm.model)
#predicting testdata on the SVM model
svm.pred <- predict(svm.model, test[,-35])
table(predicted= svm.pred,test[,35])
#using ConfusionMatrix to calculate accuracy
d<-table(predicted= svm.pred,test[,35])
print(d)
accuracy<-(sum(diag(d))/sum(d))*100
accuracy
h5<-accuracy

         