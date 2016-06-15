//Pablo Marti

import java.io.*;

public class HeapBag
{
    private BinaryTreeNode[] h; // array of nodes
    private int next; // pointer or cursor of the HEAP

    public HeapBag()
    {
       h = new BinaryTreeNode[100];
       next = 0;
    }
  
    public BinaryTreeNode getNode(int i)
    {
       return h[i];
    }
   
    public void setNode(BinaryTreeNode z,int i)
    {
       h[i] = z;
    }

    public int getLast()
    {
      return next;
    }
 
    public int count(int key)
    {
        int count = 0;
        
        int search = 0;
        while(search != next)
        {
            if(h[search].getData() == key)
            {
                while(h[search].getData() == key)
                {
                    count++;
                    search++;
                }
                return count;
            }
                
            search++;
        }
        
        
        return 0;
    }

    public BinaryTreeNode search(int key)
    {   
        int search = 0;
        while(search != next)
        {
            if(h[search].getData() == key)
                return h[search];
            search++;
        }
        return null;
    }

    public void add(BinaryTreeNode z)
    {
        
        if(next > 0) {
             
             int i = next;
             h[i] = z;
             
             while (i >= 1 && (h[i-1].getData() < h[i].getData())) {
                 BinaryTreeNode tempParent = h[i-1];
                 h[i-1] = h[i];
                 h[i] = tempParent;
                 i--;
             } 
             
             next++;
             
        }
        else {  
          h[next] = z;
          next++;
        }
        
    }
      
    public BinaryTreeNode remove()
    {
        if(next == 0){
            System.out.println("No nodes");
            return null;
        }
        else
        {
            h[0] = h[next - 1];
            next--;
            
            int i = 0, left, right, min, temp;
 
            left = (2*i) + 1;
            right = (2*i) + 2;
            
            if(right >= next)
            {
                if(left >= next)
                {
                    return null;
                }
                else
                {
                    min = left;
                }
            }
            else
            {
                if(h[left].getData() <= h[right].getData())
                {
                    min = left;
                }
                else
                {
                    min = right;
                }
            }
            if(h[i].getData() > h[min].getData())
            {
                temp = h[min].getData();
                h[min] = h[i];
                h[i].setData(temp);
                
            }
            
            return h[0];
        }
    }  
    

    public void connectNodes()
    {   
        BinaryTreeNode current;
        for (int i = 0; i < next; i++){
            current = h[i];
            
            current.setLeft(h[(2*i) + 1]);
            current.setRight(h[(2*i) + 2]);
        }
     }
     
}
    
