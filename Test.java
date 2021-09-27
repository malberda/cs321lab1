import java.util.Scanner;
import java.io.*;

/*
Michael Alberda
cs321
Min Long

quick explanation:
to compile, 
javac Test.java Cache.java
to run, either 
java Test 1 a b where a is the number of elements possible in the cache, and b is the name of the file being read in
java Test 2 a b c where a and b are the sizes of the two caches, and c is the file name


Quick disclaimer.
My answer is very similar to the results1k2k and results1k5k files, only off by a few total.
i believe this is due to no reading a specific word somewhere but im not sure whats causing the difference.
i believe this because my total references is one shorter than the example results. i tried using the delimiter that was shown on the assignment sheet,
but that gave me around 30,000 more references than the example. i tried several different methods to read from a file, but they all gave me the same answers.

i am hitting one too many times in cache 1, which leads to 1 too few times in cache2.
i think both of these are due to having one too few words, but i cant figure out why
thats happening.



*/

public class Test
{
	
	public static void main(String args[])
	{

/*
java Cache 1 <cache size> <input textfile name> or
java Cache 2 <cache 1 size> <cache 2 size> <input textfile name>

everything else should pop an error

*/

int option;
int cache1Size=0;
int cache2Size=0;
String inputTextFileName="";
											//setting up variables for command line stuff
		if(Integer.parseInt(args[0])==1)
			{option=1;}
		else if(Integer.parseInt(args[0])==2)
			{option=2;}
		else
		{
			System.out.println("incorrect command line arguments");
			return;
		}


		if(option==1)					//option 1 single cache system
		{
			if(args.length!=3)
			{
				System.out.println("incorrect input");
				return;
			}
			cache1Size=Integer.parseInt(args[1]);
			inputTextFileName=args[2];
		//	System.out.println("your options are "+option+" "+cache1Size+" "+inputTextFileName);
		}
		else         					//option 2 double cache system
		{
			if(args.length!=4)
			{
				System.out.println("incorrect input");
				return;
			}
			cache1Size=Integer.parseInt(args[1]);
			cache2Size=Integer.parseInt(args[2]);
			inputTextFileName=args[3];			
			//System.out.println("your options are "+option+" "+cache1Size+" "+cache2Size+" "+inputTextFileName);
		}

		if(inputTextFileName.equals(""))
		{
			System.out.println("something went wrong line 64");
			return;
		}

int cache1Hits=0;
int cache2Hits=0;		//tracks number of htis per cache
int cache1References=0;
int cache2References=0;


		Cache cache1=new Cache();//initialize LinkedList list
		Cache cache2=new Cache();//initialize LinkedList list
System.out.println("First level cache with "+cache1Size+" entries has been created");
if(option==2)
{
	System.out.println("Second level cache with "+cache2Size+" entries has been created");
}
System.out.println("....................................");
										



		File file=new File(inputTextFileName);
		try{
			String word;
			Scanner input = new Scanner(file);
			//input.useDelimiter(" +");   //using the delimiter shown in the assignment gives me way
								//too many references, but not using it gives me a couple too few.
			int count=0;
			int extrarun=0;
			while(input.hasNext()) 				//main code that checks each word in queue or double queue
			{
				word=input.next();
				cache1References=cache1References+1;
		//		System.out.println("looking at "+word);

								//if word exists in first cache
				if(cache1.getObject(cache1,word)==true)
				{
					//increment cache1hits by one
					//remove the object from cache1 and add it to front
					cache1Hits=cache1Hits+1;
					cache1=cache1.removeObject(cache1, word);
					cache1=cache1.addObject(cache1,word);


							// if there is a second cache
					if(option==2)
					{
						//if it exists in second cache
						if(cache2.getObject(cache2,word)==true)
						{
								//remove from second cache and add it to front
							cache2=cache2.removeObject(cache2, word);
							cache2=cache2.addObject(cache2, word);


						}
						else
						{
							//if it does not exist in second cache
							if(cache2.getCount(cache2)==cache2Size)
							{		//if the number of elements in cache2 are => total allowed 
									//in cache2, remove last then add to cache2


								cache2=cache2.removeLast(cache2);

							}
							cache2=cache2.addObject(cache2,word);
						}
					}

				}
				else    		//if word does not exist in first cache
				{	
							//if cache1 is at max capacity, remove last elemtn then add to front
					if(cache1.getCount(cache1)==cache1Size)
					{
						cache1=cache1.removeLast(cache1);
					}
					cache1=cache1.addObject(cache1,word);	



						//if there is a second cache
					if(option==2)
					{
						//increment number of cache2 references
						cache2References=cache2References+1;

							//if word exists in cache2 
						if(cache2.getObject(cache2,word)==true)
						{
									//increment cache2hits, then remove and 
									//add word to cache2
								//remove from second cache and add it to front
							cache2Hits=cache2Hits+1;
							cache2=cache2.removeObject(cache2, word);
							cache2=cache2.addObject(cache2, word);

						}
						else      		// does not exist in either cache add to second since first was already added
						{
							//if word does not exist in cache2
							//remove last element if necessary
							if(cache2.getCount(cache2)==cache2Size)
							{
							cache2=cache2.removeLast(cache2);

							}
							cache2=cache2.addObject(cache2,word);
						}
					} 

				}

				count=count+1;					//in case of errors with adding too many things to cache, happened a couple times due to an error with removeObject
				if(cache1.getCount(cache1)>cache1Size)
				{
					System.out.println("something bad happened cache1.............................. ");

					return;
				}
				if(cache2.getCount(cache2)>cache2Size)
				{
					System.out.println("something bad happened cache2.............................. ");
					return;
				}

			}

		}
		catch(FileNotFoundException e)
		{
			System.out.println("that file does not exist");
			return;
		}

		double hr=0;
		double hr1=0;
		double hr2=0;




					//printing the results of the work


		if(option==1)
		{
			hr=(double)cache1Hits/(double)cache1References;
		}
		else
		{
			hr1=(double)cache1Hits/(double)cache1References;
			hr2=(double)cache2Hits/(double)cache2References;
			hr=((double)cache1Hits+(double)cache2Hits)/(double)cache1References;
		}	

System.out.println("The number of global references     : "+cache1References);
System.out.println("The number of global cache hits     : "+(cache1Hits+cache2Hits));
System.out.println("The global hit ratio                : "+hr+"\n");

if(option==2)
{
System.out.println("The number of 1st-level references  : "+cache1References);
System.out.println("The number of 1st-level cache hits  : "+cache1Hits);
System.out.println("The 1st-level cache hit ratio       : "+hr1+"\n");

System.out.println("The number of 2nd-level references  : "+cache2References);
System.out.println("The number of 2nd-level cache hits  : "+cache2Hits);
System.out.println("The 2nd-level cache hit ratio       : "+hr2+"\n");
}


//cache1.printList(cache1);
//cache2.printList(cache2);

	//	cache1.printList(cache1);
	}



}