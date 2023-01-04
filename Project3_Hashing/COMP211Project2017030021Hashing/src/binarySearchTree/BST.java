package binarySearchTree;

public class BST {
	
	   protected BinarySearchTreeNode root;  
	   protected int height;  
	   protected int nodes;  
	   private int numberOfcomparisons;
	   
	   public BST() {    
		   root = null;
		   height = -1; 
		   nodes = 0;  
		   } 
	   
	   
	   public int getNumberOfComparisons() {
		return numberOfcomparisons;
	}


	public void setNumberOfComparisons(int numberOfcomparisons) {
		this.numberOfcomparisons = numberOfcomparisons;
	}


	public BinarySearchTreeNode search(int a) {    
		   return search(a, root); // call recursive func 
		   } 
	   
	   
	   public BinarySearchTreeNode search(int a, BinarySearchTreeNode n) {   
		   if (n == null)                     // failure     
			   return null;   
		   if ( a == n.getData())      // found    
			   return n;  
		   else if ( a < n.getData())  {
			   numberOfcomparisons++;
			   return search(a, n.getLeft()); 
		   }// return!   
		   else     
		   {    
			   numberOfcomparisons++;
			   return search(a, n.getRight());  // return! 
			   } 
	   }

	   
	   public void insert(int a) {
		   insert(a, root); 
		   }
	   
	   public void insert(int a, BinarySearchTreeNode n) { // recursive  
		   if (n == null) {                       // empty tree      
			   root = new BinarySearchTreeNode(a);
			   return;     // fresh root   
			   }        
		   BinarySearchTreeNode left  = n.getLeft();          // left child  
		   BinarySearchTreeNode right = n.getRight();         // right child   
		   if ( a < n.getData())  {          // check      
			   if (left == null)                   // missing?         
				   n.setLeft( new BinarySearchTreeNode(a) );    // insert     
			   else        
				   insert(a, left);   // recursion 
		   }  
			   else {      
				   if (right == null)                  // missing?         
				   
			   n.setRight( new BinarySearchTreeNode(a) );   // insert      
			   else          
				   insert(a, right);                // recursion  
		   } 
		   
		   
		   
	   }
	   
	   
}
