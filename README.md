****************
* Lab 1
* CS 321
* 9/17/2021
* Michael Alberda
**************** 

OVERVIEW:

Created a cache using a DLLnode class and applied it using two test documents.


INCLUDED FILES:

 * Test.java - source file
 * Cache.java - source file
 * README.txt - this file


COMPILING AND RUNNING:

 From the directory containing all source files, compile using the command:
 $ javac *.java

 Run the compiled Test  class with the command:
 $ java Test #1 #2 #3 [filename.txt]

 Where #1 is the number of caches, #2 is the size of cache 1, and #3 is the size of cache2 if it exists. if #1 is one then dont put anything for #3

 Terminal will output using the same style as the result1k2k file


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

I wrote a new Cache and DLLnode class.

The test class is simple, reading input parsing using java's built in text reader. 


TESTING:

For testing I used the premade result files for various cache sizes with two files. My testing output was reading one too few or one too many words from the example file, and I never figured out why.
I believe its either reading too much whitespace as a word or not recognizing it, but any small scale testing I did never prompted an error.

	


