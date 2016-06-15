//Pablo Marti
/*
using the BinaryTreeNode.java which is given to us we have created a heapBag that is 
randomly generated at first the heaps are written to a file HeapX and HeapY

where they are read back into the program later on to edit

the user then adds a node 
deletes a node and searches for a key
*/

import java.io.*;
import java.util.*;

public class HeapTest
{  
   
   public static HeapBag GenHeap(int L, int H, int M){
      
        Random random = new Random();
        HeapBag newHeap = new HeapBag();
            
        System.out.print("Generate random Integers: ");
            
        for (int i = 0; i < (M+1); i++){
            int num = random.nextInt((H - L) + 1) + L;
            System.out.print(num + " ");
            newHeap.add(new BinaryTreeNode(num, null, null));
        }
        
        System.out.println("\n");
        
        return newHeap;
   }
  
   public static HeapBag MakeHeap(int L, int H){
      Scanner kb = new Scanner(System.in);
      HeapBag newHeap = new HeapBag();
      
      System.out.println("USER make a HEAP. Node values are between " + L + " and " + H);
      System.out.println("Please enter # of nodes: ");
      
      int nodeNumber = kb.nextInt();
      int newNumber = 0;

      for(int i = 0; i < nodeNumber; i++) {
         System.out.print("node # " + (i + 1) + ": ");
         newNumber = kb.nextInt();
         
         newHeap.add(new BinaryTreeNode(newNumber, null, null));
      }
      
      System.out.println();
      
      return newHeap;
   }
      
   public static void DisplayHeapData(HeapBag heapG){
      System.out.println("DATA of this HEAP with ARRAY Implementation:");
      
      for (int i = 0; i < heapG.getLast(); i++) {
           System.out.print(heapG.getNode(i).getData() + " ");
        }
        
        System.out.println("\n");
   }

   public static void WriteHeapToFile(HeapBag heapG, String fileName) {
      
      System.out.println("WRITE this heap to TEXT file " + fileName);
      
      try {
            FileWriter output = new FileWriter(fileName);
        
            for (int i = 0; i < heapG.getLast(); i++) {
                output.write(String.valueOf(heapG.getNode(i).getData() + " "));
            }
            
            output.close();
            
            System.out.println("DONE.\n");
        } catch(IOException e) {}
   }   
   
   public static HeapBag ReadHeapFromFile(String fileName) {
       
       try {
           HeapBag temp = new HeapBag();
           BinaryTreeNode number = new BinaryTreeNode();
           Reader inp = new FileReader(fileName);
           StreamTokenizer tstream = new StreamTokenizer(inp);
           tstream.parseNumbers();
           
           int token = tstream.nextToken();
           System.out.println("Reading a Heap from " + fileName + " file: ");
           
           while (token != StreamTokenizer.TT_EOF)
           {
               temp.add(new BinaryTreeNode((int) tstream.nval, null, null));
               System.out.print((int) tstream.nval + " ");
               token = tstream.nextToken();
           }
           
           inp.close();
           return temp;
       }
       catch(IOException e) {
           return null;
       }
   }   
   
   public static void PrintHeapTree(HeapBag a) {
      
      System.out.println("Tree form of this heap: ");
      
      a.connectNodes();
      System.out.println();
      a.getNode(0).print(1);
   }
   
   
   public static void main(String[] args) {
        // 1) GENERATE a random HEAP:
        
        HeapBag heap = GenHeap(1, 100, 10);
        
        //Display DATA of the HEAP with ARRAY Implementation order

         DisplayHeapData(heap);

        //Write heap DATA to a TEXT file

         WriteHeapToFile(heap, "HeapX.txt");
        
        // DISPLAY a HEAP in a tree form on the screen
        
        PrintHeapTree(heap);
 
        
        // 2) Let the USER make a HEAP:
        
        HeapBag userHeap = MakeHeap(1, 100);
 
        
        //Display DATA of the HEAP with ARRAY Implementation order
        
        DisplayHeapData(userHeap);
 
        // DISPLAY a HEAP in a tree form on the screen
        
        PrintHeapTree(userHeap);
       
       
        //Write heap DATA to a TEXT file
        
        WriteHeapToFile(userHeap, "HeapY.txt");
     
             
        // 3) READ a heap from a TEXT file:
        
        HeapBag toFile = ReadHeapFromFile("HeapX.txt");

        // DISPLAY a HEAP in a tree form on the screen     
        
        PrintHeapTree(toFile);

        // 4) ADD a new NODE
        //Enter the Value of a node Z to add
        Scanner kb = new Scanner(System.in);
        
        int newNode = 0;
        
        System.out.println("Enter node to be added: ");
        newNode = kb.nextInt();

        // create the NEW NODE
        BinaryTreeNode temp = new BinaryTreeNode(newNode, null, null);
        
        //Adding the New Node
        toFile.add(temp);

        
        //Display DATA of the NEW HEAP (with ARRAY Implementation order)
        System.out.println("New Heap with node " + newNode + " added: ");
        DisplayHeapData(toFile);


        // DISPLAY a HEAP in a tree form on the screen
        PrintHeapTree(toFile);

        
        // 6) REMOVE a NODE(ROOT)
        toFile.remove();

        
        //Display the removed node
        //Display DATA of the NEW HEAP (with ARRAY Implementation order)
        System.out.println("New Heap: ");
        DisplayHeapData(toFile);
        
        // 6) SEARCH for a KEY)
        int searchNode;
        
        System.out.print("Enter the node to be searched: ");
        //Enter the KEY to search
        searchNode = kb.nextInt();
        
        //Search for KEY, count how many times if it's found
        BinaryTreeNode found = new BinaryTreeNode();
        found = toFile.search(searchNode);
        
        System.out.println("Found this key: " + toFile.count(searchNode) + " time(s)");
    }
}

