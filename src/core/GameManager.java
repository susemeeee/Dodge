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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
	
	public static void readRanking(ArrayList<String> usernameArray, ArrayList<Integer> highscoreArray) throws IOException {
		try {
			File rankeingFile = new File("gamefiles/users/Ranking.txt");
			BufferedReader rankingBuffer = new BufferedReader(new InputStreamReader(new FileInputStream(rankeingFile),"UTF8"));
			String fileLine = rankingBuffer.readLine();
			String[] splitLine;
			
			while(fileLine != null) {
				splitLine = fileLine.split("=");
				usernameArray.add(splitLine[0]);
				highscoreArray.add(Integer.parseInt(splitLine[1]));
				fileLine = rankingBuffer.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}//use RankingPanel
	
	public static void sortRanking(int highscore) {
		
	}//use GameOverPanel
}