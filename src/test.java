import java.util.ArrayList;
import java.util.TreeMap;

public class test {
	public static void main(String args[]) {
		ArrayList<ArrayList<Integer>> arrayList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> arrayList2 =  new ArrayList<Integer>();
		arrayList2.add(1);
		arrayList2.add(1);
		arrayList2.add(1);
		arrayList2.add(1);
		arrayList2.add(1);
		arrayList.add(arrayList2);
		ArrayList<Integer> arrayList3 =  new ArrayList<Integer>();
		arrayList3.add(2);
		arrayList3.add(2);
		arrayList3.add(2);
		arrayList3.add(2);
		arrayList3.add(2);
		arrayList.add(arrayList3);
		ArrayList<Integer> arr = arrayList.get(0);
		arrayList.remove(0);
		System.out.println(arr);
		System.out.println(arrayList);
	}
}
