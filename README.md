# MergeFiles
MergeFiles

Build and execute code(Make sure java is installed):
1. Download code from Github to specific durectory
2. go to src folder
3. Compile code using following command
javac com/merge/*.java
4. Build executable jar file using following command 
jar cfe MergeFiles.jar com.merge.FileMerge com/merge/*.class
5. Copy the MergeFiles.jar wherever you want to execute and create input folder (e.g.files). Copy all input text files(*.txt) under that input folder
6. Execute the jar file using following command.
java -jar MergeFiles.jar files output.dat

Validattion:
If no argument passed then you will get error as below
"Add valid arguments: inputFolder outputfile"

If any files are not sorted, following message will display
<Filename> is not sorted

Code flow:
1. Used merge sort technique to merge 2 files which gives O(N) performance
2. Used ArrayList data structure to store content of the file to make sure data is ordered(sorted). 
3. Merging happens in memory to improve performance




