package core;

public class RankingInfo {
	private String username;
	private int highscore;
	
	public RankingInfo(String username, int highscore) {
		this.username = username;
		this.highscore = highscore;
	}
	
	public String getUserName() {
		return username;
	}
	
	public int getHighscore() {
		return highscore;
	}
	
	public void setUserInfo(String username, int highscore) {
		this.username = username;
		this.highscore = highscore;
	}
}