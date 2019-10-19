import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;


public class BlackJack extends JFrame{
	
	private boolean playerPhase = true;
	private Player player1 = new Player("Player 1", 0);
	private Player bank = new Player("BANK", 0);
	private Deck gameDeck = new Deck();
	private MainFrame window = new MainFrame(this);
	private Boolean drawCard = false;
	

	public void runGame() {

		firstPhase();
		//while (!this.drawCard) {}
		//playerDrawCard();
	}


	public void firstPhase() {

		for (int firstTurnDraw = 0 ; firstTurnDraw < 2 ; firstTurnDraw++) {
			this.player1.addCard(this.gameDeck.getCard());
			this.bank.addCard(this.gameDeck.getCard());
		}
		this.window.refreshBankCards(this.bank.getHand(), playerPhase);
		this.window.refreshPlayerCards(this.player1.getHand());	
	}

	public void playerDrawCard() {

		this.player1.addCard(this.gameDeck.getCard());
		this.window.playerAddCard(player1.getHand());
	}

	public void setDrawCard() {

		this.drawCard = true;
	}
	
}
