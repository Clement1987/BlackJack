

import java.util.ArrayList;
import java.util.Random;



public class Deck {

	private ArrayList<Card> cardsList= new ArrayList<Card>(52);	
	private String cardsTypes[] = {"ACE","2","3","4","5","6","7","8","9","10","JACK","QUEEN","KING"};	
	private int cardsValuesList[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	private String cardsPictures[] = {"spadesA.png","spades2.png","spades3.png","spades4.png","spades5.png","spades6.png","spades7.png","spades8.png","spades9.png","spades10.png","spadesJ.png","spadesQ.png","spadesK.png",
									  "cloverA.png","clover2.png","clover3.png","clover4.png","clover5.png","clover6.png","clover7.png","clover8.png","clover9.png","clover10.png","cloverJ.png","cloverQ.png","cloverK.png",
									  "diamondA.png","diamond2.png","diamond3.png","diamond4.png","diamond5.png","diamond6.png","diamond7.png","diamond8.png","diamond9.png","diamond10.png","diamondJ.png","diamondQ.png","diamondK.png",
									  "heartA.png","heart2.png","heart3.png","heart4.png","heart5.png","heart6.png","heart7.png","heart8.png","heart9.png","heart10.png","heartJ.png","heartQ.png","heartK.png"};
	private Random randNumber = new Random();	
	private int nbOfCardsPickedUp = 0;

	public Deck() {
		
		int pictIndex = 0;	
		for (int numCards = 0 ; numCards < 13 ; numCards++) {
			pictIndex = numCards;
			for (int fourCards = 0 ; fourCards < 4 ; fourCards++) {
				Card fillCard = new Card(this.cardsTypes[numCards], this.cardsValuesList[numCards], this.cardsPictures[pictIndex]);					
				this.cardsList.add(fillCard);
				pictIndex += 13;					
			}
		}				
	}

	public Card getCard() {

		int randIndex = randNumber.nextInt(52 - nbOfCardsPickedUp);
		Card pickedCard = this.cardsList.get(randIndex);
		this.cardsList.remove(randIndex);
		this.nbOfCardsPickedUp += 1;		
		return pickedCard;		
	}


}
