package kdTree;

public class KdTree {
	
	
	private NodeKdTree root;
	private int depth;
	
	public KdTree() {
		root = null;
		
	}
	


//Getters and setters methods
public NodeKdTree getRoot() {
		return root;
	}




public int getDepth() {
	return depth;
}




public void setDepth(int depth) {
	this.depth = depth;
}




//Method to insert element in a kdTree with 2 dimensions
private  NodeKdTree insertElement(int keyX,int keyY, NodeKdTree node, int countLevelOfTree) {
	
	
	
		if(node == null) {
			
			node = new NodeKdTree(keyX, keyY);
			
			
		}
		
		else {
			
		
			
		if(countLevelOfTree%2 == 0) //it  means i compare based on the coordinate x 
		{
			if(keyX< node.getKeyX()) {
				countLevelOfTree++;
			node.setLeft(insertElement(keyX,keyY,node.getLeft(),countLevelOfTree));
			
			}
			else {
				countLevelOfTree++;
			 node.setRight(insertElement(keyX,keyY,node.getRight(),countLevelOfTree));
			 
			}
			
			
		}
		else  //it means i compare based on the coordinate y
		{
			
			if(keyY< node.getKeyY()) {
				countLevelOfTree++;
				node.setLeft(insertElement(keyX,keyY,node.getLeft(),countLevelOfTree));
				
				}
			else {
				countLevelOfTree++;
				node.setRight(insertElement(keyX,keyY,node.getRight(),countLevelOfTree));
				
			}
		
			
		}
		
		}
		
		return node;
		
	}

public void insertElementInKdTree(int keyX,int keyY) {
	root = this.insertElement(keyX, keyY, root, 0);
}


/*
//this is just for testing
public void insertElements() {
	
	this.insertElementInKdTree(40, 45);
	this.insertElementInKdTree(15, 70);
	this.insertElementInKdTree(70, 10);
	this.insertElementInKdTree(68, 50);
	this.insertElementInKdTree(85, 90);
	this.insertElementInKdTree(90, 95);
	this.insertElementInKdTree(66, 85);
	
	}
*/

	
	//Method to search element in a kdTree with 2 dimensions
public  boolean searchElement(int keyX,int keyY,NodeKdTree root,int countLevelOfTree) {
	
	boolean existElement = false;

	
	if(root != null) {
	
	if(keyX == root.getKeyX() && keyY == root.getKeyY()) {
		
		existElement = true;
		
	}
	else {
		if(countLevelOfTree%2 == 0) {
			if(keyX< root.getKeyX()) {
				countLevelOfTree++;
				depth++;
				existElement =	searchElement(keyX,keyY,root.getLeft(),countLevelOfTree);
			}
			else {
				countLevelOfTree++;
				depth++;
				existElement = searchElement(keyX,keyY,root.getRight(),countLevelOfTree);
			}
			
			
		}
		else {
			if(keyY< root.getKeyY()) {
				countLevelOfTree++;
				depth++;
				existElement = searchElement(keyX,keyY,root.getLeft(),countLevelOfTree);
			}
			else {
				countLevelOfTree++;
				depth++;
				existElement = searchElement(keyX,keyY,root.getRight(),countLevelOfTree);
			}
			
			
			
			
		}
		
		
	}
	}
	else {
		existElement = false;
	}
	return existElement;
	
}


public void searchElementExistance(int keyX,int keyY,NodeKdTree root) {
	
	boolean existElement;
	
	existElement = this.searchElement(keyX, keyY, root, 0);
	
	if(existElement) {
		//System.out.println("The element ("+keyX+","+keyY+") exists in the kd Tree");
	}
	else {
		//System.out.println("The element ("+keyX+","+keyY+")  does not exist in the kd Tree");
	}
	
}

/*
//this is just for testing
	public static void main(String args[]) {
		
		
		KdTree kdTree = new KdTree();
		kdTree.insertElements();
		kdTree.setDepth(0);
		kdTree.searchElementExistance(85, 90, kdTree.getRoot());
		System.out.println(kdTree.getDepth());
		kdTree.setDepth(0);
		kdTree.searchElementExistance(90, 95, kdTree.getRoot());
		System.out.println(kdTree.getDepth());
		kdTree.searchElementExistance(10, 70, kdTree.getRoot());
		kdTree.searchElementExistance(11, 71, kdTree.getRoot());
		
		
	}
	*/
	
	
	
	
	
	
	
	
	
	

}
