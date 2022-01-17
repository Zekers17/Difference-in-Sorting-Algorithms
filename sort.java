package BonusCoding;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;
import java.io.*;


public class sort
{
	static int SIZE;
//  static Scanner number = new Scanner(System.in);
//  static int num = number.nextInt();
//  static final int SIZE = num;            // size of array to be sorted
    static int[] values; //= new int[SIZE];  // values to be sorted
    static long time = 0;
    public void setValues(int b) {
    	values = new int[b];
    }
	public void setSIZE(int a) {
		SIZE = a;
	}
  
  static void initValues()
  // Initializes the values array with random integers from 0 to 99.
  {
    Random rand = new Random();
    for (int index = 0; index < SIZE; index++)
      values[index] = Math.abs(rand.nextInt());// % 100);
  }

  static public boolean isSorted()
  // Returns true if the array values are sorted and false otherwise.
  {
    for (int index = 0; index < (SIZE - 1); index++)
      if (values[index] > values[index + 1])
        return false;
    return true;
  }

  static public void swap(int index1, int index2)
  // Precondition: index1 and index2 are >= 0 and < SIZE.
  //
  // Swaps the integers at locations index1 and index2 of the values array. 
  {
    int temp = values[index1];
    values[index1] = values[index2];
    values[index2] = temp;
  }

  static public void printValues()
  // Prints all the values integers.
  {
    int value;
    DecimalFormat fmt = new DecimalFormat("00");
    System.out.println("The values array is:");
    for (int index = 0; index < SIZE; index++)
    {
      value = values[index];
      if (((index + 1) % 10) == 0)
        System.out.println(fmt.format(value));
      else
        System.out.print(fmt.format(value) + " ");
    }
    System.out.println();
  }


  /////////////////////////////////////////////////////////////////
  //
  //  Selection Sort

  static int minIndex(int startIndex, int endIndex)
  // Returns the index of the smallest value in
  // values[startIndex]..values[endIndex].
  {

    int indexOfMin = startIndex;
    for (int index = startIndex + 1; index <= endIndex; index++)
      if (values[index] < values[indexOfMin])
        indexOfMin = index;
    return indexOfMin;
  }

  static void selectionSort()
  // Sorts the values array using the selection sort algorithm.
  {
	long startselection = System.nanoTime();
    int endIndex = SIZE - 1;
    for (int current = 0; current < endIndex; current++)
      swap(current, minIndex(current, endIndex));
    long endselection = System.nanoTime();
    long TimeSelection = TimeUnit.MILLISECONDS.convert(endselection - startselection, TimeUnit.NANOSECONDS);
    time = TimeSelection;
    //System.out.println("Time spent in ms: " + TimeSelection);

  }

  /////////////////////////////////////////////////////////////////
  //
  //  Bubble Sort

  static void bubbleUp(int startIndex, int endIndex)
  // Switches adjacent pairs that are out of order 
  // between values[startIndex]..values[endIndex] 
  // beginning at values[endIndex].
  {
    for (int index = endIndex; index > startIndex; index--)
      if (values[index] < values[index - 1])
        swap(index, index - 1);
  }
 
  static void bubbleSort()
  // Sorts the values array using the bubble sort algorithm.
  {
	long startbubble = System.nanoTime();

    int current = 0;
 
    while (current < (SIZE - 1))
    {
      bubbleUp(current, SIZE - 1);
      current++;
    }
    long endbubble = System.nanoTime();
    long Timebubble = TimeUnit.MILLISECONDS.convert(endbubble - startbubble, TimeUnit.NANOSECONDS);
    time = Timebubble;
    //System.out.println("Time spent in ms: " + Timebubble);
  }


  /////////////////////////////////////////////////////////////////
  //
  //  Short Bubble Sort

  static boolean bubbleUp2(int startIndex, int endIndex)
  // Switches adjacent pairs that are out of order 
  // between values[startIndex]..values[endIndex] 
  // beginning at values[endIndex].
  //
  // Returns false if a swap was made; otherwise, returns true.
  {
    boolean sorted = true;
    for (int index = endIndex; index > startIndex; index--)
      if (values[index] < values[index - 1])
      {
        swap(index, index - 1);
        sorted = false;
      }
    return sorted;
  }
 
  static void shortBubble()
  // Sorts the values array using the bubble sort algorithm.
  // The process stops as soon as values is sorted.
  {
    int current = 0;
    boolean sorted = false;
    while ((current < (SIZE - 1)) && !sorted)
    {
      sorted = bubbleUp2(current, SIZE - 1);
      current++;
    }
    long startShort = System.nanoTime();
    long endShort = System.nanoTime();
    long TimeShort = TimeUnit.MILLISECONDS.convert(endShort - startShort, TimeUnit.NANOSECONDS);
    time = TimeShort;
    System.out.println("Time spent in ms: " + TimeShort);
  }


  /////////////////////////////////////////////////////////////////
  //
  //  Insertion Sort

  static void insertItem(int startIndex, int endIndex)
  // Upon completion, values[0]..values[endIndex] are sorted.
  {
    boolean finished = false;
    int current = endIndex;
    boolean moreToSearch = true;
    while (moreToSearch && !finished)
    {
      if (values[current] < values[current - 1])
      {
        swap(current, current - 1);
        current--;
        moreToSearch = (current != startIndex);
      }
      else
        finished = true;
    }
  }
 
  static void insertionSort()
  // Sorts the values array using the insertion sort algorithm.
  {
	  long startInsert = System.nanoTime();
   for (int count = 1; count < SIZE; count++)
      insertItem(0, count);
   
   long endInsert = System.nanoTime();
   long TimeInsert = TimeUnit.MILLISECONDS.convert(endInsert - startInsert, TimeUnit.NANOSECONDS);
   time = TimeInsert;
   System.out.println("Time spent in ms: " + TimeInsert);
  }
  


  /////////////////////////////////////////////////////////////////
  //
  //  Merge Sort

  static void merge (int leftFirst, int leftLast, int rightFirst, int rightLast)
  // Preconditions: values[leftFirst]..values[leftLast] are sorted.
  //                values[rightFirst]..values[rightLast] are sorted.
  // 
  // Sorts values[leftFirst]..values[rightLast] by merging the two subarrays.
  {
    int[] tempArray = new int [SIZE];
    int index = leftFirst;
    int saveFirst = leftFirst;  // to remember where to copy back
 
    while ((leftFirst <= leftLast) && (rightFirst <= rightLast))
    {
      if (values[leftFirst] < values[rightFirst])
      {
        tempArray[index] = values[leftFirst];
        leftFirst++;
      }
      else
      {
        tempArray[index] = values[rightFirst];
        rightFirst++;
      }
      index++;
    }
 
    while (leftFirst <= leftLast)
    // Copy remaining items from left half.
 
    {
      tempArray[index] = values[leftFirst];
      leftFirst++;
      index++;
    }
 
    while (rightFirst <= rightLast)
    // Copy remaining items from right half.
    {
      tempArray[index] = values[rightFirst];
      rightFirst++;
      index++;
    }
 
    for (index = saveFirst; index <= rightLast; index++)
      values[index] = tempArray[index];
  }

  static void mergeSort(int first, int last)
  // Sorts the values array using the merge sort algorithm.
  {
	  long startMerge = System.nanoTime();
    if (first < last)
    {
      int middle = (first + last) / 2;
      mergeSort(first, middle);
      mergeSort(middle + 1, last);
      merge(first, middle, middle + 1, last);
    }
    long endMerge = System.nanoTime();
    long TimeMerge = TimeUnit.MILLISECONDS.convert(endMerge - startMerge, TimeUnit.NANOSECONDS);
    time = TimeMerge;
    //System.out.println("Time spent in ms: " + TimeMerge);
  }


  /////////////////////////////////////////////////////////////////
  //
  //  Quick Sort

  static int split(int first, int last)
  {
    int splitVal = values[first];
    int saveF = first;
    boolean onCorrectSide;
 
    first++;
    do
    {
      onCorrectSide = true;
      while (onCorrectSide)             // move first toward last
        if (values[first] > splitVal)
          onCorrectSide = false;
        else
        {
          first++;
          onCorrectSide = (first <= last);
        }
 
      onCorrectSide = (first <= last);
      while (onCorrectSide)             // move last toward first
        if (values[last] <= splitVal)
          onCorrectSide = false;
        else
         {
          last--;
          onCorrectSide = (first <= last);
         }
   
      if (first < last)                
      {
        swap(first, last);
        first++;
        last--;
      }
    } while (first <= last);
 
    swap(saveF, last);
    return last;
  }

  static void quickSort(int first, int last)
  {
	  long startQuick = System.nanoTime();
    if (first < last)
    {
      int splitPoint;
 
      splitPoint = split(first, last);
      // values[first]..values[splitPoint - 1] <= splitVal
      // values[splitPoint] = splitVal
      // values[splitPoint+1]..values[last] > splitVal
 
      quickSort(first, splitPoint - 1);
      quickSort(splitPoint + 1, last);
    }
    long endQuick = System.nanoTime();
    long TimeQuick = TimeUnit.MILLISECONDS.convert(endQuick - startQuick, TimeUnit.NANOSECONDS);
    time = TimeQuick;
    //System.out.println("Time spent in ms: " + TimeQuick);
  }


  /////////////////////////////////////////////////////////////////
  //
  //  Heap Sort

  static int newHole(int hole, int lastIndex, int item)
  // If either child of hole is larger than item this returns the index
  // of the larger child; otherwise it returns the index of hole.
  {
    int left = (hole * 2) + 1;
    int right = (hole * 2) + 2;
    if (left > lastIndex)
      // hole has no children
      return hole;         
    else
    if (left == lastIndex)
      // hole has left child only
      if (item < values[left])             
        // item < left child
        return left;
      else
        // item >= left child
        return hole;
    else
    // hole has two children 
    if (values[left] < values[right])
      // left child < right child
      if (values[right] <= item)
        // right child <= item
        return hole;
      else
        // item < right child
        return right;
    else
    // left child >= right child
    if (values[left] <= item)
      // left child <= item
      return hole;
    else
      // item < left child
      return left;
  }

  static void reheapDown(int item, int root, int lastIndex)
  // Precondition: Current root position is "empty".
  //
  // Inserts item into the tree and ensures shape and order properties.
  {
    int hole = root;   // current index of hole
    int newhole;       // index where hole should move to

    newhole = newHole(hole, lastIndex, item);   // find next hole
    while (newhole != hole)
    {
      values[hole] = values[newhole];      // move value up
      hole = newhole;                      // move hole down
      newhole = newHole(hole, lastIndex, item);     // find next hole
    }
    values[hole] = item;           // fill in the final hole
  }

  static void heapSort()
  // Sorts the values array using the heap sort algorithm.
  {
	  long startHeap = System.nanoTime();
	  int index;
	  // Convert the array of values into a heap.
	  for (index = SIZE/2 - 1; index >= 0; index--)
      reheapDown(values[index], index, SIZE - 1);
 
    // Sort the array.
    for (index = SIZE - 1; index >=1; index--)
    {
      swap(0, index);
      reheapDown(values[0], 0, index - 1);
    }
    long endHeap = System.nanoTime();
    long TimeHeap = TimeUnit.MILLISECONDS.convert(endHeap - startHeap, TimeUnit.NANOSECONDS);
    time = TimeHeap;
    //System.out.println("Time spent in ms: " + TimeHeap);
  }

  /////////////////////////////////////////////////////////////////
  //
  //  Main

  public static void main(String[] args) throws Exception
  {
	  /*File file = new File("SortedInsertion.txt");
	  BufferedReader br = new BufferedReader(new FileReader(file));
      String st;
      while ((st = br.readLine()) != null) {
    	  sort obj = new sort();
    	  obj.setSIZE(10000);       
    	  obj.setValues(SIZE);// size of array to be sorted
    	  int[] values = new int [SIZE];
      } Allows me to input the sorted data set*/
    	  
	  System.out.println("Input a numerical value for the data size > ");
	  Scanner number = new Scanner(System.in);
	  int num = number.nextInt();
	  sort obj = new sort();
	  obj.setSIZE(num);       
	  obj.setValues(SIZE);// size of array to be sorted
	  int[] values = new int[SIZE];  // values to be sorted*/
	  
	  ///////////////////////////
	Scanner sc = new Scanner(System.in);
	System.out.println("What sort would you like to use");
	String name = sc.nextLine().toLowerCase();
	  /////////////////////////////
    initValues();
    printValues();
    System.out.println("values is sorted: " + isSorted());
    System.out.println();
    
    // make call to sorting method here (just remove //)
    if (name.equals( "selectionsort")) {
    	//
    	selectionSort();


    }
    else if(name.equals("bubblesort")){
    	//
    	bubbleSort();

    }
    else if(name.equals( "shortbubble")) {
    	//
        shortBubble();

    }
    else if(name.equals( "insertionsort")) {
    	//
    	insertionSort();
    
    }
    else if(name.equals( "mergesort")) {
    	//
        mergeSort(0, SIZE - 1);

    }
    else if(name.equals( "quicksort")) {
    	//
        quickSort(0, SIZE - 1);

    }
    else if(name.equals( "heapsort")) {
    	//
        heapSort();

    }

    printValues();
    System.out.println("values is sorted: " + isSorted());
    System.out.println();
    System.out.println("Time spent in ms: " + time);
  }
}