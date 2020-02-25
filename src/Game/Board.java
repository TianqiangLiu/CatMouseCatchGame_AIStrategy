package Game;
import java.util.ArrayList;

public class Board {
	private Mouse mouse;
	private Cat cat;

	public Board() {
		mouse = new Mouse();
		cat = new Cat(mouse);
	}
	public Board(String[] args) {
		mouse = new Mouse(args);
		cat = new Cat(mouse,args);
	}
	public void mouseMove() {
		mouse.move();
	}
	public Mouse getMouse() {
		return mouse;
	}
	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}
	public Cat getCat() {
		return cat;
	}
	public void setCat(Cat cat) {
		this.cat = cat;
	}
	public void catMove(int s) {
		cat.move(s);
	}
	public Boolean noCheeseLeft() {
		return mouse.getCheesesMap().size()>0;
	}
	public Boolean mouseCatched() {
		return cat.getCatEntry().get(0)==mouse.getMouseEntry().get(0)&&cat.getCatEntry().get(1)==mouse.getMouseEntry().get(1);
	}
	public ArrayList<Integer> getMouseEntry() {
		return mouse.getMouseEntry();
	}

	public void setMouseEntry(ArrayList<Integer> mouseEntry) {
		mouse.setMouseEntry(mouseEntry);
	}
	public void setMouseState(int state) {
		mouse.setState(state);
	}

	public ArrayList<Integer> getCatEntry() {
		return cat.getCatEntry();
	}

	public void setCatEntry(ArrayList<Integer> catEntry) {
		cat.setCatEntry(catEntry);
	}

	public ArrayList<ArrayList<Integer>> getCheesesMap() {
		return mouse.getCheesesMap();
	}

	public void setCheesesMap(ArrayList<ArrayList<Integer>> cheesesMap) {
		this.mouse.setCheesesMap(cheesesMap);
	}
	
	@Override
	public String toString() {
		return "Board [mouseEntry=" + getMouseEntry() + ", catEntry=" + getCatEntry() + ", cheesesMap=" + mouse.getCheesesMap() + "]";
	}
}
