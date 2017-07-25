Assignment 5- Image segmentation Part 3


The code uses python 2.7 version SDK and interpreter, OpenCV 3.0.0 and NumPy. 
-------------------------------------------------------------------
The zip file submitted contains:
image-segmentation.py (code file)
image1.jpg, image2.jpg, image3.jpg, image4.jpg, image5.jpg (input images)
Report_PartIII- Image segmentation.doc
Readme.txt
k=20 and k=5 folder contains some experiment results

-------------------------------------------------------------------
To Compile and run the program:
====================================
The values of k is assigned to 20. The folder Experimental results consists of output of images when value of k was 20.

SETUP:
Ensure that PyCharm 2.7, OpenCV 3.0.0 and NumPy 1.6.1 are installed on the system. 

For ubuntu terminal, follow thses steps:
http://www.pyimagesearch.com/2015/06/22/install-opencv-3-0-and-python-2-7-on-ubuntu/
Installation steps was followed as above. Post installation type as follows:

$workon cv
$python
>>> import cv2
>>> cv2.__version__'3.0.0'

For windows terminal, follow thses steps:
http://stackoverflow.com/questions/32660114/how-to-install-opencv-on-windows-and-enable-it-for-pycharm-without-using-the-pac/32664411#32664411
Steps to follow:

	Install Python 2.7.10
	Install Pycharm(If you have not done it already)
	Download and install the OpenCV executable.
	Add OpenCV in the system path(%OPENCV_DIR% = /path/of/opencv/directory)
	Goto C:\opencv\build\python\2.7\x86 folder and copy cv2.pyd file.
	Goto C:\Python27\DLLs directory and paste the cv2.pyd file.
	Goto C:\Python27\Lib\site-packages directory and paste the cv2.pyd file.
	Goto PyCharm IDE and goto DefaultSettings>PythonInterpreter.
	Select the Python which you have installed on Step1.
	Install the packages numpy,matplotlib and pip in pycharm.
	Restart your PyCharm.
	PyCharm now has OpenCV library installed and working.


EXECUTION:
1. Once all the setup is complete, do the below step for executing the code.
2. Open the terminal and set the directory to the one containing the python code and input files.
3. Type the following command on the comman line to execute the python code:
	--> python image-segmentation.py <clustering k value> <input image name with file extension> <output file name with extension>
	
	example: python image-segmentation.py 20 image4.jpg output_image4.png

_______________________________________________________________________________________________________
_______________________________________________________________________________________________________
