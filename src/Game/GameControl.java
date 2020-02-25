package Game;
import java.util.ArrayList;
import java.util.Scanner;

import AI.AI;
import AI.ASTARAI1;
import AI.ASTARAI2;
import AI.ASTARAI3;
import AI.BFSAI;
import AI.DFSAI;
import Utility.Utility;

public class GameControl {
	private Board borad;
	private BoardView boardView;
	
	
	public GameControl() {
		borad = new Board();
		boardView = new BoardView(borad);
	}
	public GameControl(String[] args) {
		borad = new Board(args);
		boardView = new BoardView(borad);
	}
	public void initialBoard() {
		System.out.println(borad.toString());
		boardView.printBoard();
	}
	public AI getAIStrateg(String aitype) {
		
		AI ai;
		if (aitype.equalsIgnoreCase("BFS")) {
			ai = new BFSAI();
			return ai;
		}else if(aitype.equalsIgnoreCase("DFS")){
			ai = new DFSAI();
			return ai;
		}else if(aitype.equalsIgnoreCase("AI1")) {
			ai = new ASTARAI1();
			return ai;
		}else if(aitype.equalsIgnoreCase("AI2")) {
			ai = new ASTARAI2();
			return ai;
		}else if(aitype.equalsIgnoreCase("AI3")) {
			ai = new ASTARAI3();
			return ai;
		}
		
		return null;
	}
	public void start(String[] args) {
		String aiType = args[0];
//		if (args.length>1) {
//			ArrayList<Integer>arrayList = new ArrayList<Integer>();
//			arrayList.add(Integer.parseInt(args[1]));
//			arrayList.add(Integer.parseInt(args[2]));
//			borad.setCatEntry(arrayList);
//			
//			arrayList = new ArrayList<Integer>();
//			arrayList.add(Integer.parseInt(args[3]));
//			arrayList.add(Integer.parseInt(args[4]));
//			borad.setMouseEntry(arrayList);
//			
//			
//			ArrayList<ArrayList<Integer>>arr = new ArrayList<ArrayList<Integer>>();
//			arrayList = new ArrayList<Integer>();
//			arrayList.add(Integer.parseInt(args[5]));
//			arrayList.add(Integer.parseInt(args[6]));
//			arr.add(arrayList);
//			arrayList = new ArrayList<Integer>();
//			arrayList.add(Integer.parseInt(args[7]));
//			arrayList.add(Integer.parseInt(args[8]));
//			arr.add(arrayList);
//			arrayList = new ArrayList<Integer>();
//			arrayList.add(Integer.parseInt(args[9]));
//			arrayList.add(Integer.parseInt(args[10]));
//			arr.add(arrayList);
//		}
		initialBoard();
		
		Scanner scan = new Scanner(System.in);
		AI ai= getAIStrateg(aiType);
		if(ai==null) {
			System.err.println("Input is not correct!");
			System.exit(0);
		}
		long startTime=System.nanoTime();
		ArrayList<Integer>resultArrayList = ai.getResult(borad);
		long endTime=System.nanoTime();
		if(resultArrayList==null) {
			System.out.println("Cat Cannot win!");
		}else {
			while(borad.noCheeseLeft()) {
				int s = resultArrayList.get(0);
				borad.catMove(s);
				resultArrayList.remove(0);
				System.out.println("\n\n----------------------------------------------");
				System.out.println(borad.toString());
				borad.mouseMove();
				boardView.printBoard();
				if(borad.mouseCatched()) {
					borad.setMouseState(Utility.Catched);
					break;
				}
				
//				try {
//					Thread.sleep(2000);;
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
			if(!borad.noCheeseLeft()){
				System.out.println("Mouse Win!!!");
			}
			else {
				System.out.println("Cat Win!!!");
			}
		}
		System.out.println("Spend Time: " + (endTime-startTime));
		scan.close();
	}
}
