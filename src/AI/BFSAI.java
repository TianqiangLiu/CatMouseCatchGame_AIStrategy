package AI;

import java.util.ArrayList;

import Game.Board;
import Game.Cat;
import Game.Mouse;
import Utility.Utility;



public class BFSAI implements AI{
	private ArrayList<ArrayList<Integer>>mousePath;
	
	public ArrayList<Integer> getResult(Board board){
		ArrayList<Integer> process = new ArrayList<Integer>();
		BFSNode bfsNode = getBFSResultNode(board);
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
	
	public BFSNode getBFSResultNode(Board board){
		Cat cat= board.getCat();
		Mouse mouse = board.getMouse();
		ArrayList<ArrayList<Integer>> cheesesMap = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> mouseEntry = new ArrayList<Integer>();
		ArrayList<BFSNode> searchedNodes = new ArrayList<BFSNode>();
		cheesesMap.addAll(mouse.getCheesesMap());
		mouseEntry.addAll(mouse.getMouseEntry());
		mousePath = Utility.getMousePath(mouseEntry, cheesesMap);
		mousePath.add(0,new ArrayList<Integer>(mouse.getMouseEntry()));
		ArrayList<BFSNode> nodeList = new ArrayList<BFSNode>();
		BFSNode bfsNode = new BFSNode(null,0,cat.getCatEntry());
		nodeList.add(bfsNode);
		while(nodeList.size()>0) {
			if(successed(nodeList.get(0))) {
				return nodeList.get(0);
			}
			ArrayList<BFSNode> children =getChildren(nodeList.get(0));
			BFSNode bestNode = nodeList.get(0);
			searchedNodes.add(bestNode);
			nodeList.remove(0);
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
					}
				}
				if(tembool) {
					nodeList.add(children.get(i));
					searchedNodes.add(children.get(i));
				}
			}
		
		}
		
		return null;
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
				if(arrayList!=null)
					arr.add(new BFSNode(bfs, bfs.getRoundInteger()+1, arrayList));
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
