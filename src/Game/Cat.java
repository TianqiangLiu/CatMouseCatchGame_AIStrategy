package Game;
import java.util.ArrayList;

public class Cat {
	private ArrayList<Integer> catEntry;
	private Mouse mouse;
	
	public Cat(Mouse mouse) {
		this.mouse = mouse;
		int randomX = (int) (Math.random()*12);
		int randomY = (int) (Math.random()*12);
		while(true) {
			boolean tembool = true;
			randomX = (int) (Math.random()*12);
			randomY = (int) (Math.random()*12);
			for (int j = 0; j < this.mouse.locationsArrayList.size(); j++) {
				if(randomX==this.mouse.locationsArrayList.get(j).get(0)&&randomY==this.mouse.locationsArrayList.get(j).get(1)) {
					tembool = false;
				}
			}
			if (tembool) {
				break;
			}
		}
		catEntry = new ArrayList<Integer>();
		catEntry.add(randomX);
		catEntry.add(randomY);
	}
	public Cat(Mouse mouse,String[] args) {
		this.mouse = mouse;
		catEntry = new ArrayList<Integer>();
		catEntry.add(Integer.parseInt(args[1]));
		catEntry.add(Integer.parseInt(args[2]));
	}
	public void move(int s) {
		switch (s) {
		case 1:
			catEntry.set(1, catEntry.get(1)-1);
			catEntry.set(0, catEntry.get(0)+2);//
			break;
		case 2:
			catEntry.set(1, catEntry.get(1)-2);//
			catEntry.set(0, catEntry.get(0)+1);
			break;
		case 3:
			catEntry.set(1, catEntry.get(1)-2);
			catEntry.set(0, catEntry.get(0)-1);
			break;
		case 4:
			catEntry.set(1, catEntry.get(1)-1);
			catEntry.set(0, catEntry.get(0)-2);
			break;
		case 5:
			catEntry.set(1, catEntry.get(1)+1);
			catEntry.set(0, catEntry.get(0)-2);
			break;
		case 6:
			catEntry.set(1, catEntry.get(1)+2);
			catEntry.set(0, catEntry.get(0)-1);
			break;
		case 7:
			catEntry.set(1, catEntry.get(1)+2);//
			catEntry.set(0, catEntry.get(0)+1);
			break;
		case 8:
			catEntry.set(1, catEntry.get(1)+1);
			catEntry.set(0, catEntry.get(0)+2);//
			break;

		default:
			break;
		}

	}

	public ArrayList<Integer> getCatEntry() {
		return catEntry;
	}

	public void setCatEntry(ArrayList<Integer> catEntry) {
		this.catEntry = catEntry;
	}
}
