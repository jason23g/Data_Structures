package binarySearchTree;

public class BinarySearchTreeNode {

	
	private int data;
	private BinarySearchTreeNode left;
	private BinarySearchTreeNode right;
	
	public BinarySearchTreeNode(int data) {
		this.data = data;
		left = null;
		right = null;
		
		
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public BinarySearchTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinarySearchTreeNode left) {
		this.left = left;
	}

	public BinarySearchTreeNode getRight() {
		return right;
	}

	public void setRight(BinarySearchTreeNode right) {
		this.right = right;
	}
	
	
	
	
	
	
}
