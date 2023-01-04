package prQuadTree;

public class NodePrQuadTree {
	
	private int keyX;
	private int keyY;
	private NodePrQuadTree nw;
	private NodePrQuadTree ne;
	private NodePrQuadTree sw;
	private NodePrQuadTree se;
	private int xMin;
	private int xMax;
	private int yMin;
	private int yMax;
	
	
	public NodePrQuadTree(int itemX,int itemY,int xMin,int xMax,int yMin,int yMax) {
		this.keyX = itemX;
		this.keyY = itemY;
		 int midX = (xMax+xMin)/2 ;
		 int midY = (yMax+yMin)/2 ;
		this.nw = new NodePrQuadTree(xMin,midX,yMin,midY);
		this.ne = new NodePrQuadTree(midX,xMax,yMin,midY);
		this.sw = new NodePrQuadTree(xMin,midX,midY,yMax);
		this.se = new NodePrQuadTree(midX,xMax,midY,yMax);	
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		
	}
	
	public NodePrQuadTree(int xMin,int xMax,int yMin,int yMax) {
		this.keyX = -1;
		this.keyY = -1;
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		
		
	}
	
	
	

	public NodePrQuadTree getNw() {
		return nw;
	}

	public void setNw(NodePrQuadTree nw) {
		this.nw = nw;
	}

	public NodePrQuadTree getNe() {
		return ne;
	}

	public void setNe(NodePrQuadTree ne) {
		this.ne = ne;
	}

	public NodePrQuadTree getSw() {
		return sw;
	}

	public void setSw(NodePrQuadTree sw) {
		this.sw = sw;
	}

	public NodePrQuadTree getSe() {
		return se;
	}

	public void setSe(NodePrQuadTree se) {
		this.se = se;
	}

	public int getKeyX() {
		return keyX;
	}

	public int getKeyY() {
		return keyY;
	}
	
	public void setKeyX(int keyX) {
		this.keyX = keyX;
	}

	public void setKeyY(int keyY) {
		this.keyY = keyY;
	}

	public int getxMin() {
		return xMin;
	}

	public void setxMin(int xMin) {
		this.xMin = xMin;
	}

	public int getxMax() {
		return xMax;
	}

	public void setxMax(int xMax) {
		this.xMax = xMax;
	}

	public int getyMin() {
		return yMin;
	}

	public void setyMin(int yMin) {
		this.yMin = yMin;
	}

	public int getyMax() {
		return yMax;
	}

	public void setyMax(int yMax) {
		this.yMax = yMax;
	}

	//Method to check whether is a leaf or internal node 
	public boolean isLeaf() {
	
		if(nw != null && ne != null && se != null && sw != null) {
		if((nw.getKeyX() == -1 && nw.getKeyY() == -1) && (ne.getKeyX() == -1 && ne.getKeyY() == -1) && (sw.getKeyX() == -1 && sw.getKeyY() == -1) && (se.getKeyX() == -1 && se.getKeyY() == -1) ) {
			return true;
		}
		}
		return false;
	}
	
	public void changeLeafToNode() {
		
		this.keyX = -1;
		this.keyY = -1;
	}
	
	
	public boolean isEmptyNode() {
		if(nw == null && ne == null && se == null & sw == null ) {
			return true;
		}
		return false;
	}
	
	

}
