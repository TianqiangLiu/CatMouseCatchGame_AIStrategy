package Utility;
import java.io.IOException;
import java.util.ArrayList;

public class Utility {
	public static final int Free = 1;
	public static final int Catched = 0;
	public static void clearScreen() {  
		try {
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();		
			} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static String[][] initialStrings(){
		String s[][] = new String[12][12];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				s[i][j] = "*";
			}
		}
		
		return s;
	}
	public static ArrayList<ArrayList<Integer>> getMousePath(ArrayList<Integer>mouseEntry, ArrayList<ArrayList<Integer>>cheesesMap) {
		ArrayList<ArrayList<Integer>> arrayList = new ArrayList<ArrayList<Integer>>();
		while(cheesesMap.size()>0) {
			int targetCheese = findClosestCheese(mouseEntry,cheesesMap);
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
			ArrayList<Integer> arr = new ArrayList<Integer>();
			arr.addAll(mouseEntry);
			arrayList.add(arr);
		}
		return arrayList;
	}


	public static int findClosestCheese(ArrayList<Integer>mouseEntry, ArrayList<ArrayList<Integer>>cheesesMap) {
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

	
}
