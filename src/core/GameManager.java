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
import java.util.ArrayList;

import GUI.GameFrame;
import gameobject.GameCharacter;

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
		String fileName = ("gamefiles/users/" + username + ".txt");
		File userFile = new File(fileName);
		userFile.createNewFile();
		try {
			BufferedWriter userInfoFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userFile), "UTF8"));
			userInfoFile.write("highscore=0");
			
			userInfoFile.flush();
			userInfoFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void readRanking(ArrayList<String> usernameArray, ArrayList<Integer> highscoreArray) throws IOException {
		try {
			File rankingFile = new File("gamefiles/users/rank/Ranking.txt");
			BufferedReader rankingBuffer = new BufferedReader(new InputStreamReader(new FileInputStream(rankingFile),"UTF8"));
			String fileLine = rankingBuffer.readLine();
			String[] splitLine;
			
			while(fileLine != null) {
				splitLine = fileLine.split("=");
				usernameArray.add(splitLine[0]);
				highscoreArray.add(Integer.parseInt(splitLine[1]));
				fileLine = rankingBuffer.readLine();
			}
			
			rankingBuffer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveHighScore(String username, int highscore) throws IOException {
		try {
			String fileName = "gamefiles/users/" + username + ".txt";
			File userFile = new File(fileName);
			BufferedReader userBuffer = new BufferedReader(new InputStreamReader(new FileInputStream(userFile), "UTF8"));
			StringBuilder newUserInfo = new StringBuilder();
			//String fileLine = userBuffer.readLine();
		
			newUserInfo.append("highscore=" + Integer.toString(highscore));
			
			userBuffer.close();
			
			BufferedWriter userWritter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userFile), "UTF8"));
			userWritter.append(newUserInfo);
			userWritter.flush();
			userWritter.close();
			//돈 등 다른기능 만들거면 수정해야됨
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void sortRanking(String username, int highscore) throws IOException, FileNotFoundException {
		ArrayList<RankingInfo> rankingArray = new ArrayList<RankingInfo>();
		File rankingFile = new File("gamefiles/users/rank/Ranking.txt");
		BufferedReader rankingBuffer = new BufferedReader(new InputStreamReader(new FileInputStream(rankingFile), "UTF8"));
		String fileLine = rankingBuffer.readLine();
		String[] splitLine;
		
		while(fileLine != null) {
			splitLine = fileLine.split("=");
			rankingArray.add(new RankingInfo(splitLine[0], Integer.parseInt(splitLine[1])));
			fileLine = rankingBuffer.readLine();
		}
		
		rankingBuffer.close();
		
		RankingInfo currentUserInfo = new RankingInfo(username, highscore);
		
		for(int i = rankingArray.size() - 1; i >= 0; --i) {
			if((highscore > rankingArray.get(i).getHighscore())) {
				if(i == rankingArray.size() - 1) {
					rankingArray.add(i + 1, rankingArray.get(i));
				}
				else {
					rankingArray.set(i + 1, rankingArray.get(i));
				}
				
				rankingArray.set(i, currentUserInfo);
			}
		}
		
		BufferedWriter rankingWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rankingFile), "UTF8"));
		StringBuilder rankingFileBuilder = new StringBuilder();
		
		for(int i = 0; i < 5; ++i) {
			rankingFileBuilder.append(rankingArray.get(i).getUserName() + "=" + Integer.toString(rankingArray.get(i).getHighscore()));
			rankingFileBuilder.append("\n");
		}
		rankingWriter.append(rankingFileBuilder);
		
		rankingWriter.flush();
		rankingWriter.close();
	}
}