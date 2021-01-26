package javatester;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Topic_07_Loop_Statement {
	public static void main(String[] args) {
		String[] studentName = { "TR", "DH", "AB", "DV" };
		// in ra tu 1 -100
		for (int i = 0; i < studentName.length; i++) {
			System.out.println(studentName[i]);
		}
		for (int i = 1; i <= 100; i++) {
			System.out.println(i);
		}
		List<String> address = new ArrayList<String>();
		address.add("TR");
		address.add("DH");
		address.add("AB");
		address.add("DV");

		for (Iterator iterator = address.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());

		}
		for (String temp : address) {
			System.out.println(temp);
		}
	}
}
