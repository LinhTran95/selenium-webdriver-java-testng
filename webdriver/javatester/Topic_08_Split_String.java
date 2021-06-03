package javatester;

public class Topic_08_Split_String {
	public static void main(String[] args) {
		String url = "http://the-internet.herokuapp.com/basic_auth";

		String[] newUrl = url.split("//");

		url = newUrl[0] + "//" + "admin" + ":" + "admin" + "@" +newUrl[1] ;
	}
}
