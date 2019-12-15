package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import GUI.GameFrame;

public class GameManager {
	public static void startGame() {
		GameFrame.createFrame();
	}
	
	public static void loadUser(String username, GameCharacter gameCharacter) throws IOException {
		try {
			String filename = "gamefiles/users/" + username + ".txt";
			File userFile = new File(filename);
			BufferedReader userInfoFile = new BufferedReader(new InputStreamReader(new FileInputStream(userFile), "UTF8"));
			String infoLine = userInfoFile.readLine();
			String[] splitLine;
			
			splitLine = infoLine.split("=");
			gameCharacter.setHighScore(Integer.parseInt(splitLine[1]));
			userInfoFile.close();
		} catch (FileNotFoundException e) {
			createUser(username);
		}
	}
	
	public static void createUser(String username) throws IOException {
		File userFile = new File("gamefiles/users/" + username + ".txt");
		userFile.createNewFile();
		try {
			BufferedWriter userInfoFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userFile), "UTF8"));
			userInfoFile.write("highscore=0");
			userInfoFile.newLine();
			
			userInfoFile.flush();
			userInfoFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}