package Game;
import java.util.ArrayList;

import Utility.Utility;

public class Mouse {

	private ArrayList<Integer> mouseEntry;
	private ArrayList<ArrayList<Integer>> cheesesMap;
	public ArrayList<ArrayList<Integer>> locationsArrayList;
	private int state;
	public Mouse() {
		state = Utility.Free;
		locationsArrayList = new ArrayList<ArrayList<Integer>>();
		mouseEntry = new ArrayList<Integer>();
		int randomX = (int) (Math.random()*12);
		int randomY = (int) (Math.random()*12);
		mouseEntry.add(randomX);
		mouseEntry.add(randomY);
		locationsArrayList.add(mouseEntry);
		cheesesMap = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 3; i++) {
			while(true) {
				boolean tembool = true;
				randomX = (int) (Math.random()*12);
				randomY = (int) (Math.random()*12);
				for (int j = 0; j < locationsArrayList.size(); j++) {
					if(randomX==locationsArrayList.get(i).get(0)&&randomY==locationsArrayList.get(i).get(1)) {
						tembool = false;
					}
				}
				if (tembool) {
					break;
				}
			}
			ArrayList<Integer> arrIntegers = new ArrayList<Integer>();
			arrIntegers.add((int) (Math.random()*12));
			arrIntegers.add((int) (Math.random()*12));
			cheesesMap.add(arrIntegers);
			locationsArrayList.add(arrIntegers);
		}
	}
	public Mouse(String[] args) {
		state = Utility.Free;
		locationsArrayList = new ArrayList<ArrayList<Integer>>();
		mouseEntry = new ArrayList<Integer>();
		mouseEntry.add(Integer.parseInt(args[3]));
		mouseEntry.add(Integer.parseInt(args[4]));
		
		cheesesMap = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> arrIntegers = new ArrayList<Integer>();
		arrIntegers.add(Integer.parseInt(args[5]));
		arrIntegers.add(Integer.parseInt(args[6]));
		cheesesMap.add(arrIntegers);
		arrIntegers = new ArrayList<Integer>();
		arrIntegers.add(Integer.parseInt(args[7]));
		arrIntegers.add(Integer.parseInt(args[8]));
		cheesesMap.add(arrIntegers);
		arrIntegers = new ArrayList<Integer>();
		arrIntegers.add(Integer.parseInt(args[9]));
		arrIntegers.add(Integer.parseInt(args[10]));
		cheesesMap.add(arrIntegers);


	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void move() {
		int targetCheese = findClosestCheese();
		ArrayList<Integer> cheeselocation = cheesesMap.get(targetCheese);
		if(cheeselocation.get(0)!=mouseEntry.get(0)) {
			int movement = cheeselocation.get(0)>mouseEntry.get(0)?1:-1;
			mouseEntry.set(0, mouseEntry.get(0)+movement);
		}else if(cheeselocation.get(1)!=mouseEntry.get(1)){
			int movement = cheeselocation.get(1)>mouseEntry.get(1)?1:-1;
			mouseEntry.set(1, mouseEntry.get(1)+movement);
		}
		if(cheeselocation.get(0)==mouseEntry.get(0)&&cheeselocation.get(1)==mouseEntry.get(1)) {
			cheesesMap.remove(targetCheese);
		}
	}
	public int findClosestCheese() {
		int distance = Integer.MAX_VALUE;
		int position = -1;
		for (int i = 0; i < cheesesMap.size(); i++) {
			int temdis = Math.abs(cheesesMap.get(i).get(0)-mouseEntry.get(0))+Math.abs(cheesesMap.get(i).get(1)-mouseEntry.get(1));
			if(temdis<distance) {
				distance = temdis;
				position = i;
			}else if(temdis == distance) {
				boolean tembool = Math.abs(cheesesMap.get(i).get(0)-12)+Math.abs(cheesesMap.get(i).get(1)-12)
									> Math.abs(cheesesMap.get(position).get(0)-12)+Math.abs(cheesesMap.get(position).get(1)-12);
				position = tembool? i : position;
			}
		}
		return position;
	}
	public ArrayList<ArrayList<Integer>> getCheesesMap() {
		return cheesesMap;
	}
	public void setCheesesMap(ArrayList<ArrayList<Integer>> cheesesMap) {
		this.cheesesMap = cheesesMap;
	}
	public ArrayList<Integer> getMouseEntry() {
		return mouseEntry;
	}
	public void setMouseEntry(ArrayList<Integer> mouseEntry) {
		this.mouseEntry = mouseEntry;
	}

}
