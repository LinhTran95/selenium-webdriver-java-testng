package javatester;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Topic_09_RGB {
	public static void main(String[] args) {
		System.out.println(hex2Rgb("#03a9f4"));
		
		}
	public static Color hex2Rgb (String colorStr) {
		return Color.fromString (colorStr);
	}
}
