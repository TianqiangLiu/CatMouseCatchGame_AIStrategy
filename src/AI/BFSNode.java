package AI;

import java.util.ArrayList;

public class BFSNode {
	BFSNode preNode;
	ArrayList<Integer> catEntry;
	Integer roundInteger;
	ArrayList<BFSNode>children;
	int AIScore;
	public BFSNode(BFSNode pre,int roundInteget, ArrayList<Integer> cat) {
		preNode = pre;
		this.roundInteger = roundInteget;
		catEntry = new ArrayList<Integer>(cat);
		children = new ArrayList<BFSNode>();
		AIScore = 0;
	}

	public int getAIScore() {
		return AIScore;
	}

	public void setAIScore(int aIScore) {
		AIScore = aIScore;
	}

	public Integer getRoundInteger() {
		return roundInteger;
	}

	public void setRoundInteger(Integer roundInteger) {
		this.roundInteger = roundInteger;
	}

	public ArrayList<BFSNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<BFSNode> children) {
		this.children = children;
	}
	public void addChildren(BFSNode children) {
		this.children.add(children);
	}

	public BFSNode getPreNode() {
		return preNode;
	}
	public void setPreNode(BFSNode preNode) {
		this.preNode = preNode;
	}
	public ArrayList<Integer> getCatEntry() {
		return catEntry;
	}
	public void setCatEntry(ArrayList<Integer> catEntry) {
		this.catEntry = catEntry;
	}

}
