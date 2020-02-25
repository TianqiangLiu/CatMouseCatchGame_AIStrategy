package Game;

public class Game {
	public static void main(String args[]) {
		GameControl game;
		if(args.length>1) {
			 game= new GameControl(args);
		}else {
			game = new GameControl();
		}
		
		game.start(args);
	}
}
