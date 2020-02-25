package AI;

import java.util.ArrayList;

import Game.Board;
import Game.Cat;
import Game.Mouse;
import Utility.Utility;

public class ASTARAI3 implements AI{

	private ArrayList<ArrayList<Integer>>mousePath;
	private ArrayList<ArrayList<Integer>> cheesesMap;
	@Override
	public ArrayList<Integer> getResult(Board board) {
		cheesesMap = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> process = new ArrayList<Integer>();
		BFSNode bfsNode = getAStar3ResultNode(board);
		if(bfsNode==null) {
			return null;
		}
		while(bfsNode.getPreNode()!=null) {
			if(bfsNode.getPreNode().getCatEntry().get(0)-bfsNode.getCatEntry().get(0)==-2) {
				if(bfsNode.getPreNode().getCatEntry().get(1)-bfsNode.getCatEntry().get(1)==1)
					process.add(0,1);
				else {
					process.add(0,8);
				}
			}
			if(bfsNode.getPreNode().getCatEntry().get(0)-bfsNode.getCatEntry().get(0)==-1) {
				if(bfsNode.getPreNode().getCatEntry().get(1)-bfsNode.getCatEntry().get(1)==2)
					process.add(0,2);
				else {
					process.add(0,7);
				}
			}
			if(bfsNode.getPreNode().getCatEntry().get(0)-bfsNode.getCatEntry().get(0)==1) {
				if(bfsNode.getPreNode().getCatEntry().get(1)-bfsNode.getCatEntry().get(1)==2)
					process.add(0,3);
				else {
					process.add(0,6);
				}
			}
			if(bfsNode.getPreNode().getCatEntry().get(0)-bfsNode.getCatEntry().get(0)==2) {
				if(bfsNode.getPreNode().getCatEntry().get(1)-bfsNode.getCatEntry().get(1)==1)
					process.add(0,4);
				else {
					process.add(0,5);
				}
			}
			bfsNode = bfsNode.getPreNode();
		}
		
		return process;
	}
	public BFSNode getAStar3ResultNode(Board board){
		Cat cat= board.getCat();
		Mouse mouse = board.getMouse();
		
		ArrayList<Integer> mouseEntry = new ArrayList<Integer>();
		ArrayList<BFSNode> searchedNodes = new ArrayList<BFSNode>();
		cheesesMap.addAll(mouse.getCheesesMap());
		mouseEntry.addAll(mouse.getMouseEntry());
		
		mousePath = Utility.getMousePath(mouseEntry, new ArrayList<ArrayList<Integer>>(cheesesMap));
		mousePath.add(0,new ArrayList<Integer>(mouse.getMouseEntry()));
		
		ArrayList<BFSNode> nodeList = new ArrayList<BFSNode>();
		BFSNode bfsNode = new BFSNode(null,0,cat.getCatEntry());
		bfsNode.setAIScore(getNodeScore(bfsNode));
		
		nodeList.add(bfsNode);
		while(nodeList.size()>0) {
			BFSNode bestNode= getBestNode(nodeList);
			if(successed(bestNode)) {
				return bestNode;
			}
			ArrayList<BFSNode> children =getChildren(bestNode);
			searchedNodes.add(bestNode);
			nodeList.remove(bestNode);
			
			for (int i = 0; i < children.size(); i++) {
				if(successed(children.get(i))) {
					return children.get(i);
				}
				boolean tembool = true;
				for (int j = 0; j < searchedNodes.size(); j++) {
					if(searchedNodes.get(j).getCatEntry().get(0)==children.get(i).getCatEntry().get(0)
							&&searchedNodes.get(j).getCatEntry().get(1)==children.get(i).getCatEntry().get(1)
							&&searchedNodes.get(j).getRoundInteger()==children.get(i).getRoundInteger()) {
						tembool = false;
						if(searchedNodes.get(j).getAIScore()>children.get(i).getAIScore()) {
							int different = searchedNodes.get(j).getAIScore()-children.get(i).getAIScore();
							searchedNodes.get(j).setAIScore(children.get(i).getAIScore());
							decreaseChildrenScore(different, searchedNodes.get(j));
							searchedNodes.get(j).setPreNode(children.get(i).getPreNode());
						}
						
					}
				}
				if(tembool) {
					nodeList.add(children.get(i));
					searchedNodes.add(children.get(i));
					bestNode.addChildren(children.get(i));
				}
			}
		
		}
		
		return null;
	}
	public void decreaseChildrenScore(int num, BFSNode bfsNode) {
			ArrayList<BFSNode> childrensArrayList = bfsNode.getChildren();
			
			for (int i = 0; i < childrensArrayList.size(); i++) {
				childrensArrayList.get(i).setAIScore(childrensArrayList.get(i).getAIScore()-num);
				decreaseChildrenScore(num, childrensArrayList.get(i));
			}
	}
	public BFSNode getBestNode(ArrayList<BFSNode> arrayList) {
		int position = -1;
		int score = Integer.MAX_VALUE;
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getAIScore()<score) {
				position = i;
				score = arrayList.get(i).getAIScore();
			}
		}
		return arrayList.get(position);
	}
	public Integer getNodeScore(BFSNode bfsNode) {
		int sum = 0;
		sum = sum+bfsNode.getRoundInteger();
		sum = sum + heuristic1(bfsNode);
		return sum;
	}
	public Integer heuristic1(BFSNode bfsNode) {
		//distance to the future mouse
		int distance = 0;
		int tem = Utility.findClosestCheese(mousePath.get(bfsNode.getRoundInteger()), cheesesMap);
		ArrayList<Integer> closestCheese= cheesesMap.get(tem);
		distance = Math.abs(bfsNode.getCatEntry().get(0)-closestCheese.get(0))
					+Math.abs(bfsNode.getCatEntry().get(1)-closestCheese.get(1));
		int distance2 =distance = Math.abs(bfsNode.getCatEntry().get(0)-mousePath.get(bfsNode.getRoundInteger()).get(0))
				+Math.abs(bfsNode.getCatEntry().get(1)-mousePath.get(bfsNode.getRoundInteger()).get(1));
		distance = (int) ((distance+distance2)/2);
		return distance;
	}
	public boolean successed(BFSNode bfs) {
		return mousePath.get(bfs.getRoundInteger()).get(0)==bfs.getCatEntry().get(0)
				&&mousePath.get(bfs.getRoundInteger()).get(1)==bfs.getCatEntry().get(1);
	}
	public ArrayList<BFSNode> getChildren(BFSNode bfs){
		ArrayList<BFSNode> arr = new ArrayList<BFSNode>();
		ArrayList<ArrayList<Integer>> catLocations = new ArrayList<ArrayList<Integer>>();
		catLocations.add(addEntry(bfs.getCatEntry(), 2, -1));
		catLocations.add(addEntry(bfs.getCatEntry(), 1, -2));
		catLocations.add(addEntry(bfs.getCatEntry(), -1, -2));
		catLocations.add(addEntry(bfs.getCatEntry(), -2, -1));
		catLocations.add(addEntry(bfs.getCatEntry(), -2, 1));
		catLocations.add(addEntry(bfs.getCatEntry(), -1, 2));
		catLocations.add(addEntry(bfs.getCatEntry(), 1, 2));
		catLocations.add(addEntry(bfs.getCatEntry(), 2, 1));

		for (int i = 0; i < catLocations.size(); i++) {
			if ((catLocations.get(i).get(0)<0)||(catLocations.get(i).get(1)<0)||(catLocations.get(i).get(0)>11)||(catLocations.get(i).get(1)>11)) {
				catLocations.set(i,null);

			}
		}
		if (bfs.getRoundInteger()<mousePath.size()-1) {
			for (ArrayList<Integer> arrayList : catLocations) {
				if(arrayList!=null) {
					BFSNode tembfs = new BFSNode(bfs, bfs.getRoundInteger()+1, arrayList);
					tembfs.setAIScore(getNodeScore(tembfs));
					arr.add(tembfs);
				}
			}
		}
		
		
		return arr;
	}
	public ArrayList<Integer> addEntry(ArrayList<Integer> location, int X, int Y){
		ArrayList<Integer> newLocation = new ArrayList<Integer>(location);
		newLocation.set(0, newLocation.get(0)+X);
		newLocation.set(1, newLocation.get(1)+Y);
		return newLocation;
	}

}
