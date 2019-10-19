


public class Card {
	
	private String cardType;
	private int cardValue;
	private String cardPicture;
	
	public Card(String type,int value, String cardPicture) {
		this.cardType = type;
		this.cardValue = value;
		this.cardPicture = cardPicture;
	}
	
	public String getCardType() {
		return this.cardType;
	}

	public int getCardValue() {
		return this.cardValue;
	}

	public String getCardPict() {
		return this.cardPicture;
	}

	public void setCardValue(int value) {
		this.cardValue = value;
	}

	public void setCardPict(String cardPict) {
		this.cardPicture = cardPict;
	}

	public boolean isAce() {
		boolean isAce = false;
		if (this.cardType.equals("ACE")) {
			isAce = true;
		}
		return isAce;
	}
}
