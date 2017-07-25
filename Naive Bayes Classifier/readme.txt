Running the code:
_____________________

1. open command prompt
2. change working directory: cd C:\................\R-2.13.0\bin\i386 <-- to the R bin path
			OR
    set the $path environment variable to execute RScript.exe(this path will be where your r.exe is present ie the bin path)
Make sure the files are all in the same location

3. type the following command once the setup is complete:
	Rscript Assignment3.R <training data file> <test data file>

	example: Rscript Assignment3.R train-win.dat test-win.dat

---------------------------------------------------------------------
Output for the train-win.dat and test-win.dat was observed to be:

P( abc = 0 )= 0.7225 P( wesley = 0 | abc = 0 )=0.5432526 P( wesley = 1 | abc = 0 )=0.4985572 P( romulan = 0 | abc = 0 )=0.4982699 P( romulan = 1 | abc = 0 )=0.5004301 P( poetry = 0 | abc = 0 )=0.4515571 P( poetry = 1 | abc = 0 )=0.4980788 P( honor = 0 | abc = 0 )=0.4636678 P( honor = 1 | abc = 0 )=0.4991102 P( tea = 0 | abc = 0 )=0.4256055 P( tea = 1 | abc = 0 )=0.4948628 P( barclay = 0 | abc = 0 )=0.5242215 P( barclay = 1 | abc = 0 )=0.4998456 

P( abc = 1 )= 0.2775 P( wesley = 0 | abc = 1 )=0.3513514 P( wesley = 1 | abc = 1 )=0.4784713 P( romulan = 0 | abc = 1 )=0.4864865 P( romulan = 1 | abc = 1 )=0.5009469 P( poetry = 0 | abc = 1 )=0.6126126 P( poetry = 1 | abc = 1 )=0.4882543 P( honor = 0 | abc = 1 )=0.6576577 P( honor = 1 | abc = 1 )=0.4755658 P( tea = 0 | abc = 1 )=0.7072072 P( tea = 1 | abc = 1 )=0.4560725 P( barclay = 0 | abc = 1 )=0.3603604 P( barclay = 1 | abc = 1 )=0.4811899 

Accuracy on training set( 800 instances)= 81.125 
Accuracy on test set( 203 instances)= 77.83251