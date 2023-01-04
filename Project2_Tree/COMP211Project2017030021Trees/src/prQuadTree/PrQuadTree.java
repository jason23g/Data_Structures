

package prQuadTree;

public class PrQuadTree {
	
	private NodePrQuadTree root;
	private int depth;
	public static int N = 100;//the dimensions of a range NxN
	//remember to change it to the value 2^16(Math.pow(2,16) )
	
	public PrQuadTree() {
		root = null;
	}

	//Getters and setters  methods
	public NodePrQuadTree getRoot() {
		return root;
	}
	
	
	public int getDepth() {
		return depth;
	}
	
	

	public void setDepth(int depth) {
		this.depth = depth;
	}

	//Method to insert element in a prQuadTree
	private NodePrQuadTree insertElement(int keyX,int keyY,NodePrQuadTree node,int xMin,int xMax,int yMin, int yMax) {
		
		int midX = (xMax + xMin)/2; 
		int midY = (yMax + yMin)/2; 
		int valueXofLeaf;
		int valueYofLeaf;
		
		

		
		
		
		if(node == null || node.isEmptyNode() || (node.getKeyX() == keyX && node.getKeyY() == keyY)) {
			node = new NodePrQuadTree(keyX,keyY,xMin,xMax,yMin,yMax);
			
		}
		else{
			
	
			if(node.isLeaf() && (node.getKeyX() != -1 && node.getKeyY()!= -1)) {
				valueXofLeaf = node.getKeyX();
				valueYofLeaf = node.getKeyY();
				node.changeLeafToNode();
				root = insertElement(valueXofLeaf, valueYofLeaf, node, xMin, xMax, yMin, yMax);
			}
			
			
			
			
			if(keyX < midX) {
				
				if(keyY < midY) {
					
					
					node.setNw(insertElement(keyX,keyY,node.getNw(),node.getxMin(),midX,node.getyMin(),midY));
					
				}
				else {
					
			
					
					node.setSw(insertElement(keyX,keyY,node.getSw(),node.getxMin(),midX,midY,node.getyMax()));
					
				}
				
			}
			
			else {
				
				if(keyY < midY) {
					

					node.setNe(insertElement(keyX,keyY,node.getNe(),midX,node.getxMax(),node.getyMin(),midY));
				
					
				}
				else {
					

				
					node.setSe(insertElement(keyX,keyY,node.getSe(),midX,node.getxMax(),midY,node.getyMax()));
					
					
				}
				
				
			}
			
			
		
		}
		
		return node;
	}
	
	
	public void insertElementInTree(int keyX,int keyY,int min,int max) {
		root = this.insertElement(keyX,keyY,root,min,max - 1,min,max - 1);
	}
	
	
	/*
	//It is just for testing
	public void insertElements() {
		
	     this.insertElementInTree(40,45,0,N);
		 this.insertElementInTree(15,70,0,N);
		 this.insertElementInTree(70,10,0,N);
		 this.insertElementInTree(68,50,0,N);
		 this.insertElementInTree(66,85,0,N);
	     this.insertElementInTree(85,90,0,N);
		 this.insertElementInTree(90,95,0,N);
	     this.insertElementInTree(90,95,0,N);
	     this.insertElementInTree(90,95,0,N);
	}
	*/
	
	//Method to search element in a prQuadTree
	private boolean searchElement(int keyX,int keyY,NodePrQuadTree root,int xMin,int xMax,int yMin, int yMax) {
		int midX = (xMax + xMin)/2; 
		int midY = (yMax + yMin)/2; 
		boolean existElement = false;
		
		
		if(root != null) {
			
			if(keyX == root.getKeyX() && keyY == root.getKeyY()) {
				existElement =  true;
			}
			else {
				
				if(keyX < midX) {
					
					if(keyY < midY) {
						
					if(!root.isEmptyNode()) {
						depth++;
						
						existElement = searchElement(keyX,keyY,root.getNw(),root.getxMin(),midX,root.getyMin(),midY);
						}
					
						
						
					}
					else {
						
						if(!root.isEmptyNode()) {
				        depth++;   
						
						existElement = searchElement(keyX,keyY,root.getSw(),root.getxMin(),midX,midY,root.getyMax());
						}
						
					}
					
				}
				
				else {
					
					if(keyY < midY) {
						
						if(!root.isEmptyNode()) {
						depth++; 
						
						existElement = searchElement(keyX,keyY,root.getNe(),midX,root.getxMax(),root.getyMin(),midY);
						}
						
					}
					else {
						
						if(!root.isEmptyNode()) {
						depth++; 
						
						existElement = searchElement(keyX,keyY,root.getSe(),midX,root.getxMax(),midY,root.getyMax());
						}
						
						
					}
					
					
				}
				
				
				}
			
	     
		}
		else {
		
			existElement =  false;
			
		}
		
		
		
		return existElement;
	}
	
	
	public void searchElementExistance(int keyX, int keyY,NodePrQuadTree root,int min,int max) {
		boolean existElement;
		existElement = searchElement(keyX,keyY,root,min,max - 1,min,max - 1);
		
		if(existElement) {
			//System.out.println("The element ("+keyX+","+keyY+") exists in the pr QuadTree");
		}
		else {
			//System.out.println("The element ("+keyX+","+keyY+") does not exist in the pr QuadTree");
		}
		
		
	}
	
	
	
	/*
	//It is just for testing
	public static void main(String[] args) {
		
		PrQuadTree prQuadTree = new PrQuadTree();
		prQuadTree.insertElements();
		prQuadTree.setDepth(0);
		prQuadTree.searchElementExistance(90, 95, prQuadTree.getRoot(),0,N);
		System.out.println("For the search of this element is needed "+prQuadTree.getDepth());
		prQuadTree.setDepth(0);
		prQuadTree.searchElementExistance(90, 94, prQuadTree.getRoot(),0,N);
		System.out.println("For the search of this element is needed "+prQuadTree.getDepth());
		prQuadTree.setDepth(0);
		prQuadTree.searchElementExistance(68, 50, prQuadTree.getRoot(),0,N);
		System.out.println("For the search of this element is needed "+prQuadTree.getDepth());
		prQuadTree.setDepth(0);
		prQuadTree.searchElementExistance(50, 68, prQuadTree.getRoot(),0,N);
		System.out.println("For the search of this element is needed "+prQuadTree.getDepth());
		prQuadTree.setDepth(0);
		prQuadTree.searchElementExistance(40, 45, prQuadTree.getRoot(),0,N);
		System.out.println("For the search of this element is needed "+prQuadTree.getDepth());
		prQuadTree.setDepth(0);
		prQuadTree.searchElementExistance(15, 70, prQuadTree.getRoot(),0,N);
		System.out.println("For the search of this element is needed "+prQuadTree.getDepth());
		prQuadTree.setDepth(0);
		prQuadTree.searchElementExistance(70, 10, prQuadTree.getRoot(),0,N);
		System.out.println("For the search of this element is needed "+prQuadTree.getDepth());
		prQuadTree.setDepth(0);
		prQuadTree.searchElementExistance(66, 85, prQuadTree.getRoot(),0,N);
		System.out.println("For the search of this element is needed "+prQuadTree.getDepth());
		prQuadTree.setDepth(0);
		prQuadTree.searchElementExistance(85, 90, prQuadTree.getRoot(),0,N);
		System.out.println("For the search of this element is needed "+prQuadTree.getDepth());
		prQuadTree.setDepth(0);
		prQuadTree.searchElementExistance(86, 90, prQuadTree.getRoot(),0,N);
		System.out.println("For the search of this element is needed "+prQuadTree.getDepth());
		prQuadTree.setDepth(0);
		prQuadTree.searchElementExistance(86, 91, prQuadTree.getRoot(),0,N);
		System.out.println("For the search of this element is needed "+prQuadTree.getDepth());
		
	}
	*/
	

}
