Environment Setup

1) Download the current OpenCV sdk: http://sourceforge.net/projects/opencvlibrary/files/opencv-android/
2) Unpack the SDK to a directory of your choosing.
3) Download the provided EyeTracking and OpenCV_Library_Files and place
   them in a directory of your choosing. It is important that 
   they are in the same directory.
4) Open Eclipse. Select File->Import...->Android->Existing
	Android Code Into Workspace->Next->Browse
	Navigate to the directory where the two folders from Step 3
	are stored. Two options "EdActivity" and "OpenCV" should appear
	in the "Projects" pane. Select them both and click finish.
5) In the Package Explorer window in Eclipse, right click the EdActivity 
   project and select "Properties". 
6) Select Resource -> Android. In the "Library" pane click the "Add..." button and 
	"OpenCV" should appear in the popup window. Select it and press "OK".
7) Navigate to the jni folder in the EdActivity Project. Open Android.mk.
   Under the line "#OPENCV_LIB_TYPE:=SHARED" there will be a line that
   reads "include ...native\jni\OpenCV.mk". The location of this file
   will depend on the folder that you placed the folders from Step 3 in and
   the path following "include" must be changed accordingly. 
   Example: include your-path\OpenCV_Library_Files\native\jni\OpenCV.mk
8) At this point, the EdActivity project should be ready to run.