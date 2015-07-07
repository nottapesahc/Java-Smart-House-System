package smarthouse;

import java.io.*;
import java.util.Calendar;
import java.util.Scanner;

public class Room {
	public String room = "Living Room";
	private int currTemp, lightLevel, maxTemp, minTemp;
	private String fan, currentUser;
	private char value;

	public Room() {
            currTemp = 70;
            lightLevel = 50;
            maxTemp = 80;
            minTemp = 60;
            fan = "off";
	}

	public Room(int CT, int LL, String F, int HT, int LT) {
		currTemp = CT;
		lightLevel = LL;
		fan = F;
		maxTemp = HT;
		minTemp = LT;
	}

	public void setRoom(String n) {
		room = n;
	}

	public void setTemp(int temp) {
		currTemp = temp;
	}

	public void setMaxTemp(int MT) {
		maxTemp = MT;
	}

	public void setMinTemp(int LT) {
		minTemp = LT;
	}

	public void setFan(String f) {
		fan = f;
	}

	public void setLights(int lights) {
		lightLevel = lights;
	}

	public void setCurrentUser(String CU) {
		currentUser = CU;
	}

	public String getRoom() {
		return room;
	}

	public int getMaxTemp() {
		return maxTemp;
	}

	public int getMinTemp() {
		return minTemp;
	}

	public int getTemp() {
		return currTemp;
	}

	public String getFan() {
		return fan;
	}

	public int getLights() {
		return lightLevel;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void writeToLog() {
		//
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter("RoomLog.txt", true)))) {
			Calendar now = Calendar.getInstance();
			out.println("\nID Code:" + currentUser + "("
					+ now.get(Calendar.MONTH) + "/"
					+ now.get(Calendar.DAY_OF_MONTH) + "/"
					+ now.get(Calendar.YEAR) + " "
					+ now.get(Calendar.HOUR_OF_DAY) + ":"
					+ now.get(Calendar.MINUTE) + ")" + room + ": "
					+ "Current Temp:" + currTemp + " Max Temp:" + maxTemp
					+ " Min Temp:" + minTemp + " Lights:" + lightLevel
					+ " Fan:" + fan + ".");
		} catch (IOException e) {
		}
	}

	public void readFile(File file) {

		Scanner x;
		String fileLine = null;
		String roomLine = null;
		int index;
		int index2;
		int index3;
		int index4;
		int index5;
		try {
			x = new Scanner(file);
			while (x.hasNextLine()) {
				fileLine = x.nextLine();
				if (fileLine.contains(getRoom())) {
					roomLine = fileLine;
				}
			}
			x.close();
		} catch (FileNotFoundException ex) {

		}
		index = roomLine.indexOf("Current Temp:") + 13;
		index2 = roomLine.indexOf(" Max");
		String s2 = roomLine.substring(index, index2);
		currTemp = Integer.parseInt(s2);

		index3 = roomLine.indexOf(" Min");
		String s3 = roomLine.substring(10 + index2, index3);
		maxTemp = Integer.parseInt(s3);
		if (roomLine.contains("off")) {
			fan = "off";
		}
		if (roomLine.contains("low")) {
			fan = "low";
		}
		if (roomLine.contains("high")) {
			fan = "high";
		}
		index4 = roomLine.indexOf(" Lights:");

		String s4 = roomLine.substring(10 + index3, index4);
		minTemp = Integer.parseInt(s4);
		index5 = roomLine.indexOf(" Fan:");

		String s5 = roomLine.substring(8 + index4, index5);
		lightLevel = Integer.parseInt(s5);

	}
}
