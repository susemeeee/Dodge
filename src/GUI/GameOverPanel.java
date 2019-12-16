package GUI;

import java.awt.Color;

import javax.swing.JPanel;

import core.GameCharacter;

public class GameOverPanel extends JPanel {
	public GameOverPanel(GameCharacter gameCharacter) {
		setBackground(Color.GREEN);//test
		//현재점수가 최고점수일때 최고점수를 바꿈
		//랭킹 세이브함수 작동
		
		//back버튼 누르면
		//현재점수를 0으로 돌림
	}
}