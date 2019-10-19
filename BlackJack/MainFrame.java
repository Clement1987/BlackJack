import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;

public class MainFrame extends JFrame implements ActionListener {



    private JFrame window = new JFrame("BlackJack");
    private JLabel title = new JLabel("BlackJack");
    private JLabel bankName = new JLabel("BANK");
    private JLabel playerName = new JLabel("YOUR CARDS");
    private JLabel valChoice = new JLabel("Choose ace value");
    private JButton drawCard = new JButton("DRAW CARD");
    private JButton endTurn = new JButton("END TURN");
    private JButton restart = new JButton("RESTART");
    private JButton aceVal1 = new JButton("1");
    private JButton aceVal11 = new JButton("11");
    private JLabel gameRunDisplay = new JLabel("    Make your Choice: ");
    private int displayWidth = 1800;
    private int displayHeight = 950;
    private JPanel bankCardsPanel = new JPanel();
    private JPanel playerCardsPanel = new JPanel();
    private boolean playerPhase = true;
    private boolean alreadyAnAce = false;
    private boolean gameOver = false;
    private boolean bankAlreadyAnAce = false;
	private Player player1 = new Player("Player 1", 0);
	private Player bank = new Player("BANK", 0);
    private Deck gameDeck = new Deck();
   
	
//--------- Constructeur de la fenêtre -----------------------------------------------------------------   

    public MainFrame() {        
        
        firstPhase();
        this.window.setSize(this.displayWidth,this.displayHeight);
        this.window.setResizable(false);
        BackGround backGround = new BackGround();
        this.window.setContentPane(backGround);
        this.window.setLayout(null);        
        this.window.add(this.title);
        this.title.setBounds((int)(this.displayWidth / 2 - 160), 40, 400, 60);
        this.title.setFont(new Font("Arial", Font.BOLD, 60));
        this.window.add(this.bankName); 
        this.bankName.setBounds((int)(this.displayWidth / 2 - 40), 130, 200, 50);
        this.bankName.setFont(new Font("Arial", Font.BOLD, 26));
        this.window.add(this.playerName); 
        this.playerName.setBounds((int)(this.displayWidth / 2 - 110), 430, 220, 50);
        this.playerName.setFont(new Font("Arial", Font.BOLD, 26));
        this.playerName.setForeground(Color.white);
        this.playerName.setOpaque(true);
        this.playerName.setBackground(new Color(10, 220, 150));
        this.playerName.setHorizontalAlignment(SwingConstants.CENTER);
        this.window.add(this.bankCardsPanel);
        this.bankCardsPanel.setBounds(500, 200, 800, 200);
        this.bankCardsPanel.setOpaque(false);
        this.window.add(this.playerCardsPanel);
        this.playerCardsPanel.setBounds(500, 500, 800, 200);
        this.playerCardsPanel.setOpaque(false);
        this.window.add(this.gameRunDisplay);
        this.gameRunDisplay.setBounds(this.displayWidth / 2 - 160, 685, 320, 25);
        this.gameRunDisplay.setFont(new Font("Arial", Font.BOLD, 24));
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setVisible(true);
        addDrawButton();
        addEndTurnButton();
        addRestartButton();
        this.window.add(this.aceVal1);
        this.window.add(this.aceVal11);
        this.window.add(this.valChoice);
        this.aceVal1.setVisible(false);
        this.aceVal11.setVisible(false); 
        this.valChoice.setVisible(false);
    }
//--------- Surcharge de la méthode due à l'implémentation ActionListener (laissée vide car la méthode est surchargée dans l'instantiation des listeners)

    public void actionPerformed(ActionEvent e) {
        
    }

//--------- JButtons avec leurs listeners pour capter les clics sur les boutons  -------------------------------------------

    public void addDrawButton() {

        this.window.add(drawCard);
        drawCard.setBounds(625, 750, 150, 80);
        drawCard.setFont(new Font("Arial", Font.BOLD, 15)); 
        drawCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                          
                playerDrawCard();
            }
        });
    }

    public void addEndTurnButton() {

        this.window.add(endTurn);
        endTurn.setBounds(825, 750, 150, 80);
        endTurn.setFont(new Font("Arial", Font.BOLD, 15));
        endTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                endTurnBtnClick();                
            }
        });       
    }

    public void addRestartButton() {

        this.window.add(restart);
        restart.setBounds(1025, 750, 150, 80);
        restart.setFont(new Font("Arial", Font.BOLD, 15));
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartBtnClick();
            }
        });        
    }

//----------- Gestion du choix de la valeur de l'AS, on fait apparaitre les boutons et le texte ---------------------------------------------
    
    public void chooseAceValue(Card aceCard) {
        
        this.valChoice.setBounds(335, 530, 200, 50);
        this.valChoice.setBackground(new Color(10, 220, 150));
        this.valChoice.setOpaque(true);
        this.valChoice.setFont(new Font("Arial", Font.BOLD, 20));
        this.valChoice.setForeground(Color.white);
        this.valChoice.setHorizontalAlignment(SwingConstants.CENTER);
        this.valChoice.setVisible(true);
        this.aceVal1.setBounds(400, 600, 70, 50);
        this.aceVal1.setFont(new Font("Arial", Font.BOLD, 15)); 
        this.aceVal1.setVisible(true);
        this.aceVal1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                          
                choiceDone();
                victoryCheck();
            }
        });
        this.aceVal11.setBounds(400, 660, 70, 50);
        this.aceVal11.setFont(new Font("Arial", Font.BOLD, 15));
        this.aceVal11.setVisible(true); 
        this.aceVal11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                          
                aceCard.setCardValue(11);                
                choiceDone();
                victoryCheck();     
            }
        });        
        refresh();
    }

    public void choiceDone() {

        this.valChoice.setVisible(false);
        this.aceVal1.setVisible(false);
        this.aceVal11.setVisible(false);
    }


//--------- Méthodes de rafraichissement des JPanels   ---------------------------------------------------------------------

    public void refreshBankCards(ArrayList<Card> hand, boolean playerPhase) {

        if (playerPhase) {
            ImageIcon cardPict = new ImageIcon("pictures/" + hand.get(0).getCardPict());
            this.bankCardsPanel.add(new JLabel(cardPict));
            cardPict = new ImageIcon("pictures/cardBackSide.png");
            this.bankCardsPanel.add(new JLabel(cardPict));
        }
        else {
            for (int cardsInHand = 0 ; cardsInHand < hand.size() ; cardsInHand++) {
            ImageIcon cardPict = new ImageIcon("pictures/" + hand.get(cardsInHand).getCardPict());
            this.bankCardsPanel.add(new JLabel(cardPict));
            }
        }
        refresh();
    }

    public void refreshPlayerCards(ArrayList<Card> hand) {        

        for (int cardsInHand = 0 ; cardsInHand < hand.size() ; cardsInHand++) {
            ImageIcon cardPict = new ImageIcon("pictures/" + hand.get(cardsInHand).getCardPict());
            this.playerCardsPanel.add(new JLabel(cardPict));                
        }    
        refresh();
    }

    public void refresh() {

        this.playerCardsPanel.revalidate();      
        this.playerCardsPanel.repaint();
        this.bankCardsPanel.revalidate();
        this.bankCardsPanel.repaint();        
    }

    

//------- Méthodes appelées par les boutons  -------------------------------------------------------

    public void playerDrawCard() {

		this.player1.addCard(this.gameDeck.getCard());
        playerAddCard(this.player1.getHand());
        if (this.player1.getHand().get(this.player1.getHand().size()-1).isAce()) {
            chooseAceValue(this.player1.getHand().get(this.player1.getHand().size()-1));
        }
        victoryCheck();
    }

    public void endTurnBtnClick() {

        playerPhase = false;
        this.bankCardsPanel.removeAll();
        refresh();
        refreshBankCards(this.bank.getHand(), this.playerPhase);
        this.drawCard.setVisible(false);
        this.endTurn.setVisible(false);
        victoryCheck();        
    }

    public void restartBtnClick() {        
        
        this.playerCardsPanel.removeAll();        
        this.playerCardsPanel.revalidate(); 
        this.playerCardsPanel.repaint();
        this.bankCardsPanel.removeAll();
        this.bankCardsPanel.revalidate();
        this.bankCardsPanel.repaint();       
        restartGame();       
    }

//------ Appelée depuis le main de RunGame, script début de partie  ------------------------------------------------ 

	public void firstPhase() {

		for (int firstTurnDraw = 0 ; firstTurnDraw < 2 ; firstTurnDraw++) {
			this.player1.addCard(this.gameDeck.getCard());
            this.bank.addCard(this.gameDeck.getCard());
            if (this.player1.getHand().get(firstTurnDraw).isAce() && !alreadyAnAce) {
                this.alreadyAnAce = true;
                chooseAceValue(this.player1.getHand().get(firstTurnDraw));
            }
            if (this.bank.getHand().get(firstTurnDraw).isAce() && !bankAlreadyAnAce && bank.getScore() <= 10) {
                this.alreadyAnAce = true;
                this.bank.getHand().get(firstTurnDraw).setCardValue(11);
            }
		}
		refreshBankCards(this.bank.getHand(), playerPhase);
        refreshPlayerCards(this.player1.getHand());
        victoryCheck();	
	}	

//-------- Phases de jeu -------------------------------------------------------------    

    public void playerAddCard(ArrayList<Card> hand) {

        ImageIcon cardPict = new ImageIcon("pictures/" + hand.get((hand.size()-1)).getCardPict());
        this.playerCardsPanel.add(new JLabel(cardPict)); 
        refresh();               
    }

    public void bankAddCard(ArrayList<Card> hand) {

        ImageIcon cardPict = new ImageIcon("pictures/" + hand.get((hand.size()-1)).getCardPict());
        this.bankCardsPanel.add(new JLabel(cardPict)); 
        refresh();               
    }

    public void endTurn() {
        
        this.bank.addCard(this.gameDeck.getCard());
        if (this.bank.getHand().get(this.bank.getHand().size()-1).isAce() && !bankAlreadyAnAce) {
            this.bankAlreadyAnAce = true;
            this.bank.getHand().get(this.bank.getHand().size()-1).setCardValue(11);
        }           
        bankAddCard(this.bank.getHand());
        victoryCheck();
    }

    public void victoryCheck() {

        int playerScore = this.player1.getScore();
        int bankScore = this.bank.getScore();
        if ((playerScore == 21 && bankScore == 21) || (playerScore == bankScore && !playerPhase)) {
            playerPhase = false;
            this.bankCardsPanel.removeAll();
            refresh();
            refreshBankCards(this.bank.getHand(), this.playerPhase);
            this.gameRunDisplay.setText("Equal Scores!");
            gameOver();
                      
        }
        else if (playerScore > 21 || (playerScore < bankScore && !this.playerPhase && bankScore > 17 && bankScore < 22) || (bankScore > playerScore && !this.playerPhase && bankScore < 21)) {
            this.bankCardsPanel.removeAll();
            refresh();
            refreshBankCards(this.bank.getHand(), this.playerPhase);
            this.gameRunDisplay.setText("You lose!"); 
            gameOver();
        }
       else if ((playerScore > bankScore && !playerPhase && bankScore >= 17) || bankScore > 21 || (playerScore == 21 && playerPhase && bankScore >= 17)) {
            playerPhase = false;
            this.bankCardsPanel.removeAll();
            refresh();
            refreshBankCards(this.bank.getHand(), this.playerPhase);
            this.gameRunDisplay.setText("You Win!");
            gameOver();
        }
        else if (playerScore == 21 && this.playerPhase) {
            endTurnBtnClick();
        }
        if (!this.playerPhase && this.bank.getScore() < 17) {
            endTurn();
        }        
    }

    public void gameOver() {

        this.gameOver = true;
        this.drawCard.setVisible(false);
        this.endTurn.setVisible(false);   
    }

    public void restartGame() {
        
        this.playerPhase = true;
	    this.player1 = new Player("Player 1", 0);
	    this.bank = new Player("BANK", 0);
        this.gameDeck = new Deck();
        this.alreadyAnAce = false;
        this.gameOver = false;
        this.gameRunDisplay.setText("    Make your Choice: ");
        this.drawCard.setVisible(true);
        this.endTurn.setVisible(true);
        choiceDone();
        firstPhase();
    }


}

    
