install.packages("neuralnet", dependencies = T)
library(neuralnet)
scaled[is.na(scaled)] <- 0
iiii<-scaled
#sampling data for traing and testing
trainIndex <- sample(1:nrow(scaled), 0.8 * nrow(scaled))
train <- scaled[trainIndex, ]
test <- scaled[-trainIndex, ]
#creating Perceptron model on training data
f <- V35 ~ V34 + V33 + V32 + V31 + V30 + V29 + V28 + V27 + V26 + V25 + V24 + V23 + V22 + V21 + V20 + V19 + V18 + V17 + V16 + V15 + V14 + V13 + V12 + V11 + V10 + V9+V8+V7+V6+V5+V4+V3+V2+V1
nn <- neuralnet(f,data=train,hidden =0, threshold = 0.002, stepmax = 1e+5)
plot(nn)
#predicting testdata on the Perceptron
pred <- compute(nn,test[,1:34])
pred.scaled <- pred$net.result *(max(iiii$V35)-min(iiii$V35))+min(iiii$V35)
real.values <- (test$V35)*(max(iiii$V35)-min(iiii$V35))+min(iiii$V35)
#calcultaing Mean Square Error to find accuracy
MSE.nn<-sum((real.values - pred.scaled)^2)/nrow(test)
MSE.nn
accuracy<-(100-MSE.nn)
accuracy
plot(real.values, pred.scaled, col='red',main='Real vs predicted NN')
