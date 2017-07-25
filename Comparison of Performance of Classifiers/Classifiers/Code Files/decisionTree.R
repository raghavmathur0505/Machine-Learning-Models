install.packages("rpart", dependencies = T)
install.packages("rpart.plot",  dependencies = T)
library(rpart)
library(rpart.plot)
require(rpart)
#sampling data for traing and testing
iiii<-scaled
trainIndex <- sample(1:nrow(iiii),0.8*nrow(iiii))
train <- iiii[trainIndex, ]
test <- iiii[-trainIndex, ]
#creating decision tree
dtm<-rpart(V35~.,train,method = "class")
print(dtm)
rpart.plot(dtm, type=4, extra=101)
#predicting testdata on the decision tree
p<-predict(dtm,test,type = "class")
plot(test[,35], p)
#using ConfusionMatrix to calculate accuracy
d<-table(p,test[,35]) 
print(d)
y<-(diag(d))/(sum(d))
Z<-sum(y)
print(Z*100)