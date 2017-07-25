install.packages("e1071", dependencies = T)
library(e1071) 

options(echo=TRUE) # if you want see commands in output file
args <- commandArgs(trailingOnly = TRUE)
Rscript --vanilla NAIVEBAYES.R train-win.dat
print(args)

train <- train.win
test <- test.win
x.train=train[,1:6]
y.train=train[,7]
x.test= test[,1:6]
y.test=test[,7]


#creating NaiveBayes model on training data
nav.model<-naiveBayes(as.factor(V7)~. ,data=train, threshold=0)
#predicting traindata on the Model
pred_train<-predict(nav.model,x.train)
#calculating accuracy (predicted data VS actual data) using confusionMatrix
d1=table(pred_train,y.train)
accuracy1<-(sum(diag(d1))/sum(d1))*100
accuracy1
#predicting testdata on the Model
predicted<-predict(nav.model,x.test)
#calculating accuracy (predicted data VS actual data) using confusionMatrix
d= table(predicted, y.test)
accuracy<-(sum(diag(d))/sum(d))*100
accuracy



