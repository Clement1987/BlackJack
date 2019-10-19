

import java.util.ArrayList;

public class Player {

	private String name;	
	private int score;
	private ArrayList<Card> hand;
	
	public Player(String name, int score) {
		
		this.name = name;		
		this.score = score;
		this.hand = new ArrayList<Card>();
	}
	
	public String getName() {
		
		return this.name;
	}	

	public int getScore() {
		
		this.score = 0;
		for (int i = 0 ; i < this.hand.size(); i++) {
			this.score += this.hand.get(i).getCardValue();
		}
		return this.score;
	}

	public void setName(String name) {
		
		this.name = name;
	} 

	public void setScore(int value) {
		
		this.score = value;
	}
	
	public ArrayList<Card> getHand() {
		
		return this.hand;
	}

	public void addCard(Card card) {

		this.hand.add(card);
	}


}
