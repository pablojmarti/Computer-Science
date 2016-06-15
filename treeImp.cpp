//Pablo Marti
/*
this program will get input of a tree
as a linked list and perform operations on the tree.
Operations are
1. add node
2. delete node.
3. search for node.
4. display tree in an ordered manner.
*/

#include<iostream>

using namespace std;

//structure declaration
struct node;			//forward declaration of strcture 
typedef node * pNode;	//using the forward declaration we can
						//define a pointer node

struct node {			//node structure
	double info;		//holds the information of the node
	pNode left;			//pointer to the left child
	pNode right;		//pointer to the right child
};

pNode root = NULL;		//the root pointer

//function
//prototypes
void fillNode(node*);	//function prototype to fill node
void insertNode();		//function prototype to insert node
bool searchNode(int);	//function prototype for searching node
void deleteNode();		//function prototype for deleting node
void printTree( pNode , int );	//function prototype for displaying tree
//void displayTree(node*);		unused function

//main
int main ()
{
	int choice = 0, 		//int variable to hold users choice
		spaces = 0,		//variable used in the print function
		num;			//variable used in search function
		
	//output for menu
	cout << "Operations on a Binary Tree" << endl;
	cout << "----------------------------------" << endl;
	cout << "What would you like to do?" << endl;
	cout << "1. Add Node" << endl;
	cout << "2. Delete Node" << endl;
	cout << "3. Search Node" << endl;
	cout << "4. Display Tree" << endl;
	cout << "5. Exit" << endl;
	
	//while loop that exists when the user wants to exit
	while (choice <= 5 )
	{
		//use enters choice for the menu
		cout << "Enter Choice:  "; 
		cin >> choice;
		
		//switch statement for the actual menu
		//correlates to the menu options pictured above.
		switch (choice)
		{
			case 1: 
				insertNode();
				break;
			case 2:
				deleteNode();
				break;
			case 3:
				cout << "what are you searching for?" << endl;
				cin >> num;
				cout << searchNode(num) << endl;
				break;
			case 4:
				if (root != NULL)
					printTree(root, spaces);
				else
					cout << "The Tree is empty" << endl;
				break;
			case 5:
				cout <<"Exiting..." << endl;;
				choice ++;
				break;
			//if the user does not enter one of the designated options
			default:
				cout << "You did not enter a correct Operation" << endl;
				choice = 0;
				break;
		}
	}
	
	system("pause");
	return 0;
}

/*---------------------------------------------------------------------------\
|							functions 										 |
|---------------------------------------------------------------------------*/
//function used to fill node
void fillNode(node* temp)
{
	//user inputs the node they would like to enter
	cout << "What is the Node?" << endl;
	cin >> temp->info;		//sets the node to the info
	temp->left = NULL;		//makes left child null
	temp->right = NULL;		//makes right child null
	
}

//functino to insert node
void insertNode()
{
	//node declarations for the function
    node *addN, *current, *parent;
    addN = new node;			//makes the new node a NEW variable
    fillNode(addN);				//call to the fill node function
    parent = NULL;				//sets parent to null
    
    //if the root is null set the root pointer to that null
    if (root == NULL)
    	root = addN;
    else 
    {
    	
		current = root;				//set the current node to the root
    	while (current)				//while the current node is not null 
    	{
    		
    		parent = current;		//the parent is set to the current
    		if (addN->info > current->info)		// if the new node is greater than the current node
    			current = current->right;		//move through to the next node
    		else
    			current = current->left;		//other wise the new node is less than the current
    	}
    	
    	if (addN->info < parent->info)			//is the information less than the parent node?
    		parent->left = addN;				//if so make the left child equal to the new node
    	else
    		parent->right = addN;				//otherwise set it to the right child
    }

}


//function to delete a node
void deleteNode()
{
	//node declarations for the function
    node *current, *parent, *delParent, *delNode;
	
	//use enters the element they want to delete
	int ele;
	cout << "what element would you like to delete?" << endl;
	cin >> ele;
	
    parent = root;					//set the parent node equal to the root
    current = NULL;					//set the current to null

    //this checks to see where the element is in the tree
    while((parent != NULL) && (ele != parent->info))
    {
        current = parent;
        if(ele < parent->info)
            parent = parent->left; 	//moves parent to find the elememt
        else
            parent = parent->right;
    }
    
	//if the tree is empty
    if(parent == NULL) 
    {
        cout << "Key not found. Nothing deleted.\n";
        return;
    }
    else
    {
    	//if the parent is the root
        if(parent == root) 
        {
            delNode = root; 	//deletes the node
            delParent = NULL; 	//sets delete parent to null
        }                
        //otherwise moves delnode and delparent
        else
        {
            delNode = parent;
            delParent = current;
        }
    }

	//if the right of the node is null 
    if(delNode->right == NULL)
    {
    	//and the parent is null
        if(delParent == NULL)  
        {
        	//delete that node
            root = delNode->left;
            delete delNode;
            
        }
        else
        {
        	//otherwise if the left is current
            if(delParent->left == delNode)
                delParent->left = delNode->left;	//move it ot the right
            else
                delParent->right = delNode->left;	//move and then delete 
                delete delNode;
            
        }
    }
    //if the right is not null
    else 
    {
    	//if the left is null
        if(delNode->left == NULL)    
        {
        	//and delete parent is null
            if(delParent == NULL)      
            {
            	//move the root and delete the node
                root = delNode->right;
                delete delNode;
             
            }
            //otherwise the parent is not null
            else
            {
            	//if the left is equal to the delete node
                if(delParent->left == delNode)
                    delParent->left = delNode->right;	//move the left to the right 
                else
                    delParent->right = delNode->right;	//move the right up one
                delete delNode;							//delete the node
                
            }
        }
        else 
        {   
			//make parent the left node           
            parent = delNode->left;
            current = delNode;
            //while the right of parent is not null
            while(parent->right != NULL)
            {	
            	//move parent down and to the right one
                current = parent;
                parent = parent->right;
            }
            
            delNode->info = parent->info;

            //if they are the same move them and then delete the node
            if(current == delNode)
                current->left = parent->left;
            else
                current->right = parent->left;
            delete parent;
           
        }
    }
}

//function to search for a node
bool searchNode(int data)
{
	node *search;
	search = root;
	
	//while the tree is not empty
	//step through the tree until the information is found
	while(search != NULL)
	{
		if(search->info == data)
			return true;
		else if (data < search->info)
			search = search->left;
		else 
			search = search->right;
	}
	//if the node is not found
	return false;
}

//function to print node in an organized manner

void printTree( pNode  leaf, int spaces )
{
  int i;


	//if the tree is not empty
  if (leaf != NULL)
  {
    printTree( leaf->right, spaces + 3 );
    for( i = 0; i < spaces; i++ )
      cout <<' ';
    cout << leaf->info << endl;
    printTree( leaf->left, spaces + 3 );
  }


  }



 
