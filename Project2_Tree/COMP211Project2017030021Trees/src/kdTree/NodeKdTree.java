package kdTree;

public class NodeKdTree {
	
	private int keyX;
	private int keyY;
	private NodeKdTree left, right;
	
	
	public NodeKdTree(int itemX, int itemY) {
		keyX = itemX;
		keyY = itemY;
		left = null;
		right = null;
		
	}
	
	// Getters and setters methods 
	public NodeKdTree getLeft() {
		return left;
	}

	public void setLeft(NodeKdTree left) {
		this.left = left;
	}

	public NodeKdTree getRight() {
		return right;
	}

	public void setRight(NodeKdTree right) {
		this.right = right;
	}


	public int getKeyX() {
		return keyX;
	}

	public void setKeyX(int keyX) {
		this.keyX = keyX;
	}

	public int getKeyY() {
		return keyY;
	}

	public void setKeyY(int keyY) {
		this.keyY = keyY;
	}
	
	
	
	
	

}
