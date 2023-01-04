package multiwaySearchTree;

public class NodeBTree {

	private static final int T = 50;

	public int mNumKeys = 0;
	public int[] mKeys = new int[2 * T - 1];
	public Object[] mObjects = new Object[2 * T - 1];
	public NodeBTree[] mChildNodes = new NodeBTree[2 * T];
	public boolean mIsLeafNode;

	public int binarySearch(int key) {
		int leftIndex = 0;
		int rightIndex = mNumKeys - 1;

		while (leftIndex <= rightIndex) {
			final int middleIndex = leftIndex + ((rightIndex - leftIndex) / 2);
			if (mKeys[middleIndex] < key) {
				leftIndex = middleIndex + 1;
			} else if (mKeys[middleIndex] > key) {
				rightIndex = middleIndex - 1;
			} else {
				return middleIndex;
			}
		}

		return -1;
	}

	public boolean contains(int key) {
		return binarySearch(key) != -1;
	}

	// Remove an element from a node and also the left (0) or right (+1) child.
	public void remove(int index, int leftOrRightChild) {
		if (index >= 0) {
			int i;
			for (i = index; i < mNumKeys - 1; i++) {
				mKeys[i] = mKeys[i + 1];
				mObjects[i] = mObjects[i + 1];
				if (!mIsLeafNode) {
					if (i >= index + leftOrRightChild) {
						mChildNodes[i] = mChildNodes[i + 1];
					}
				}
			}
			mKeys[i] = 0;
			mObjects[i] = null;
			if (!mIsLeafNode) {
				if (i >= index + leftOrRightChild) {
					mChildNodes[i] = mChildNodes[i + 1];
				}
				mChildNodes[i + 1] = null;
			}
			mNumKeys--;
		}
	}

	public void shiftRightByOne() {
		if (!mIsLeafNode) {
			mChildNodes[mNumKeys + 1] = mChildNodes[mNumKeys];
		}
		for (int i = mNumKeys - 1; i >= 0; i--) {
			mKeys[i + 1] = mKeys[i];
			mObjects[i + 1] = mObjects[i];
			if (!mIsLeafNode) {
				mChildNodes[i + 1] = mChildNodes[i];
			}
		}
	}

	public int subtreeRootNodeIndex(int key) {
		for (int i = 0; i < mNumKeys; i++) {
			if (key < mKeys[i]) {
				return i;
			}
		}
		return mNumKeys;
	}
}
