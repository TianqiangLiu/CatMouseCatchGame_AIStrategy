package Game;
import Utility.Utility;

public class BoardView {
	Board board;
	
	public BoardView(Board board) {	
		this.board = board;
	}
	public void printBoard() {
		//Utility.clearScreen();
		System.out.println(" ");
		String boardString[][] = Utility.initialStrings();
		boardString[board.getMouseEntry().get(0)][board.getMouseEntry().get(1)] = "M";
		boardString[board.getCatEntry().get(0)][board.getCatEntry().get(1)] = "C";
		for (int i = 0; i < board.getCheesesMap().size(); i++) {
			boardString[board.getCheesesMap().get(i).get(0)][board.getCheesesMap().get(i).get(1)] = "E";
		}
		for (int i = 0; i < boardString.length; i++) {
			for (int j = 0; j < boardString.length; j++) {
				System.out.print(boardString[j][i]+ " ");
			}
			System.out.println(" ");
		}
		System.out.println("M: Mouse; C: Cat; E: Cheese");
	}


}
