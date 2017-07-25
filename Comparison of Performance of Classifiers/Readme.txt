# ABOUT THE PROJECT:

1) Dataset: 
	i.	Name of Dataset: Ionosphere Dataset (UCI Machine Learning Repository (http://archive.ics.uci.edu/ml/datasets.html))
	ii.	Number of Instances: 351
	iii.	Number of Attributes: 34 plus the class attribute

2) Performed Preproessing

3) Classifiers implemented:

	• Decision Trees
	• Perceptron (Single Linear Classifier)
	• Neural Net
	• Support Vector Machines
	• Naïve Bayes Classifiers
	
4) Trained the classifiers using different set of parameters

5) Compared performance of different classifiers using Accuracy and Other performance metrics (Cross validation, ROC, Precison , Recall)

---------------------------------------------------------------------------------------------

HOW TO RUN AMD COMPILE CODE:

1.	You should have Rstudio installed
2.	Import the dataset in the Rstudio using URL: http://archive.ics.uci.edu/ml/machine-learning-databases/ionosphere/ionosphere.data
3.	Open the R code files in the Rstudio
4.	Run each file in the given order – 
a.	Preprocess.R,
b.	decisionTree.R 
c.	NEURAL NET.R 
d.	Perceptron.R
e.	NAÏVE BAYES.R
f.	SVM.R

------------------------------------------------------------------------------------------------

PACKAGES USED-

1.	RPART
install.packages("rpart", dependencies = T)
2.	CARET
install.packages("caret")
3.	RANN
install.packages("RANN")
4.	NEURALNET
install.packages("neuralnet", dependencies = T)
5.	E1071
install.packages("e1071", dependencies = T)
6.	RPART.PLOT

install.packages("rpart.plot",  dependencies = T)

------------------------------------------------------------------------------------------------------
