import java.io.*;
import java.util.*;

public class TextManip {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_YELLOW = "\u001B[33m";

	// String text will change with hashmap
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		try {
			String text = readTextFile();
			HashMap<Integer, ArrayList<String>> map = createHashMap(text);
			System.out.println("Please state your choice...\r\n" + "UPPER case or lower case (U or L): ");
			String upperLower = input.nextLine();
			caseChanger(map, upperLower);
			System.out
					.println("Please state your choice...\r\n" + "How many characters to shift (number between 1-3): ");
			int shiftCounter = input.nextInt();
			input.nextLine();
			shift(map, shiftCounter);
			System.out.println("Please state your choice...\r\n" + "Color of characters (R or Y): ");
			String color = input.nextLine();
			changeColor(map, color);
			display(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		input.close();

	}

	public static HashMap<Integer, ArrayList<String>> createHashMap(String text) {

		HashMap<Integer, ArrayList<String>> hashMap = new HashMap<Integer, ArrayList<String>>();

		for (int i = 1; i < text.length() + 1; i++) {
			ArrayList<String> list = new ArrayList<String>();
			String f = Character.toString(text.charAt(i - 1));
			list.add(f);
			hashMap.put(i, list);
		}

		return hashMap;

	}

	public static HashMap<Integer, ArrayList<String>> caseChanger(HashMap<Integer, ArrayList<String>> map, String choice) {
		ArrayList<String> list = new ArrayList<String>();
		if (choice.equalsIgnoreCase("U")) {
			for (int i = 1; i <= map.size(); i++) {
				list = map.get(i);
				list.add(list.get(0).toUpperCase());
			}
			return map;
		}
		if (choice.equalsIgnoreCase("L")) {
			for (int i = 1; i <= map.size(); i++) {
				list = map.get(i);
				list.add(list.get(0).toLowerCase());
			}
		}
		return map;
	}

	public static HashMap<Integer, ArrayList<String>>  shift(HashMap<Integer, ArrayList<String>> map, int shiftCounter) {
		ArrayList<String> list = new ArrayList<String>();
		switch (shiftCounter) {
		case 1:
			for (int i = 1; i <= map.size(); i++) {
				list = map.get(i);
				int a = list.get(0).charAt(0);
				char newChar = (char) (a + 1);
				String f = Character.toString(newChar);
				list.add(f);
			}
			break;
		case 2:
			for (int i = 1; i <= map.size(); i++) {
				list = map.get(i);
				int a = list.get(0).charAt(0);
				char newChar = (char) (a + 2);
				String f = Character.toString(newChar);
				list.add(f);
			}

			break;
		case 3:
			for (int i = 1; i <= map.size(); i++) {
				list = map.get(i);
				int a = list.get(0).charAt(0);
				char newChar = (char) (a + 3);
				String f = Character.toString(newChar);
				list.add(f);
			}

			break;
		default:
			System.out.println("Please write a valid value.");
			break;

		}
		return map;
	}

	public static HashMap<Integer, ArrayList<String>>  changeColor(HashMap<Integer, ArrayList<String>> map, String color) {
		ArrayList<String> list = new ArrayList<String>();
		if (color.equalsIgnoreCase("R")) {
			for (int i = 1; i <= map.size(); i++) {
				list = map.get(i);
				String x = list.get(0);
				x = new String(ANSI_RED + list.get(0) + ANSI_RESET);
				list.add(x);
			}

		}
		if (color.equalsIgnoreCase("Y")) {
			for (int i = 1; i <= map.size(); i++) {
				list = map.get(i);
				String x = list.get(0);
				x = new String(ANSI_YELLOW + "SYSTEM" + ANSI_RESET);
				list.add(x);
			}

		}
		return map;
	}

	public static void display(HashMap<Integer, ArrayList<String>> map) {
		String original = "";
		String shifted = "";
		String colored = "";
		String caseChanged = "";
		ArrayList<String> list = new ArrayList<String>();
		System.out.println("Original");
		for (int i = 1; i <= map.size(); i++) {
			list = map.get(i);
			original = original + list.get(0);
		}
		System.out.println(original);
		System.out.println("After Case Change");
		for (int i = 1; i <= map.size(); i++) {
			list = map.get(i);
			caseChanged = caseChanged + list.get(1);
		}
		System.out.println(caseChanged);
		System.out.println("After Shift");
		for (int i = 1; i <= map.size(); i++) {
			list = map.get(i);
			shifted = shifted + list.get(2);
		}
		System.out.println(shifted);
		System.out.println("After Color Change");
		for (int i = 1; i <= map.size(); i++) {
			list = map.get(i);
			colored = colored + list.get(3);
		}
		System.out.println(colored);
	}

	public static String readTextFile() throws IOException {
		FileInputStream fstream = new FileInputStream("C:\\Users\\C605\\eclipse-workspace\\SE375LAB1\\src\\sample.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		String returnString = "";
		while ((strLine = br.readLine()) != null) {
			returnString += strLine;
		}
		fstream.close();
		return returnString;
	}

}
