import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.Color;
import java.nio.ByteOrder;
import javax.swing.border.LineBorder;

/**
 * Created By subbaramaiah mettu
 *
 * The purpose of the class is to create players game interface and manipulate elements on it
 */
public class GameInterface extends JFrame {

    //instance variable
    private JPanel arena;
    private JPanel gameTopPanel;
    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JPanel deck;
    private JPanel gamePanel;
    private JLabel scoreCard;
    private JLabel status;
    private JLabel cardsLeft;
    private JButton playingCard;
    private JButton quitButton;
    private JButton newGameButton;
    private ActionListener listener;
    private Player player;

    public GameInterface(Player p) {

        player = p;
        listener = new ChoiceListener();
        setTitle("Memory Game player " + p.playerID);
        setSize(800, 800);
        playingCard = new JButton("PLay Card");
        quitButton = new JButton("Quit Game");
        newGameButton = new JButton("New Game");
        generateCards();

        //load the game panel first
        arena = new JPanel();
        gamePanel = new JPanel();
        gameTopPanel = createTopPanel();
        centerPanel = createCenterPanel();
        bottomPanel = createBottomPanel();

        BorderLayout layout = new BorderLayout();

        //to have some gap between rows
        layout.setVgap(50);
        arena.setLayout(layout);
        arena.add(gameTopPanel, BorderLayout.NORTH);
        arena.add(centerPanel, BorderLayout.CENTER);
        arena.add(bottomPanel, BorderLayout.SOUTH);
        add(arena);
        displayCardsCount();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    /**
     * class to implement eventlistener for every click on the buttons and on the card
     */
    class ChoiceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //if current player clicked on card 0
            if (e.getSource() == player.getCard(0)) {

                player.selectedCard = isCardSelected(player.getCard(0));
                player.cardNo = 0;
                System.out.println("CLIENT --> SERVER " + player.playerID +" : TRY CARD 0");

            }
            //if current player clicked on card 1
            else if (e.getSource() == player.getCard(1)) {

                player.selectedCard = isCardSelected(player.getCard(1));
                player.cardNo = 1;
                System.out.println("CLIENT --> SERVER " + player.playerID + " : TRY CARD 1");

            }
            //if current player clicked on card 2
            else if (e.getSource() == player.getCard(2)) {

                player.selectedCard = isCardSelected(player.getCard(2));
                player.cardNo = 2;
                System.out.println("CLIENT --> SERVER " + player.playerID + " : TRY CARD 2");

            }
            //if current player clicked on card 3
            else if (e.getSource() == player.getCard(3)) {

                player.selectedCard = isCardSelected(player.getCard(3));
                player.cardNo = 3;
                System.out.println("CLIENT --> SERVER " + player.playerID + " : TRY CARD 3");

            }
            //if player hits playcard button trigger the function that plays the card on the deck
            else if (e.getSource() == playingCard) {

                playCard();

            }
        }
    }

    /**
     * a helper method to get what current player played the card on deck
     * @return
     */
    public Card getPlayingCard(){

        return player.selectedCard;
    }

    /**
     * this method is to set the current player opponent card deck that was played by the opponent and to display it on the deck that is shared between two players
     * @param oppCard card that opponend played
     */
    public void setOpponentCard(Card oppCard){

        if(oppCard != null){

            player.opponentCard.setName(oppCard.getName(), oppCard.getPower(), oppCard.getType(), oppCard.getWeakness());
        }

    }

    /**
     * this method is to set the player card that was played by the player and to display it on the deck that is shared between two players
     * @param pCard card that current player played
     */
    public void setPlayingCard(Card pCard){

        if(pCard != null){
            player.playerCard.setName(pCard.getName(), pCard.getPower(), pCard.getType(), pCard.getWeakness());
        }

    }

    /**
     * this method is used to update the cards count of the player once the player plays a card
     */
    public void displayCardsCount(){

        cardsLeft.setText("Cards Left: " + player.cardsCount);
    }

    /**
     * method to set the cards count. Mostly from the server.
     * @param count count of the cards for the player
     */
    public void setCardsCount(int count){

        player.cardsCount = count;
    }
    /**
     This method stops the game for a player for 4 secs.
     **/
    public void waiting(){
        try{
            Thread.sleep(4000);
        }catch(InterruptedException e){
            System.err.println("Thread Interrupted");
        }
    }

    /**
     * Method to return player cards
     * @return returns arraylist of players cards
     */
    public ArrayList<Card> getCards(){

        return player.getCards();
    }

    /**
     *put the player's selected card on the deck
     */
    public void playCard(){

        try{
            System.out.println("CLIENT --> SERVER " + player.playerID  +" : PLAY CARD " + "POWER: " + player.selectedCard.getPower() + " TYPE: " + player.selectedCard.getType());
            player.getCard(player.cardNo).setVisible(false);
            player.cardsCount--;
           player.setCard(player.cardNo,new Card("text"));
            displayCardsCount();
        }
        catch (NullPointerException e){
            status.setText("Status: Choose a card to play first");
        }

    }

    /**
     *  A method to determine and highlight which card is clicked and selected by the player
     * @param card selected card by the player on the interface
     * @return returns the selected card
     */
    public Card isCardSelected(Card card) {


        card.setBorder(new LineBorder(Color.BLUE));

        //loop to set border of other cards to white
        for (Card c : player.getCards()) {

            if (!c.equals(card)) {

                c.setBorder(new LineBorder(Color.WHITE));
            }

        }
        return card;
    }

    /**
     * This is to add event listeners to the player cards generated by the player
     */
    public void generateCards() {

        for (int i = 0; i < player.cardsCount; i++) {

            player.getCard(i).addActionListener(listener);

        }

        playingCard.addActionListener(listener);
    }

    /**
     * methdo to get the players status
     * @return a boolean value, true if its player turn
     */
    public boolean getIsPlaying() {

        return player.getIsPlaying();
    }

    /**
     * Method to set the status of the player, true if its current player's turn
     * @param status boolean value to set player status
     */
    public void setIsPlaying(boolean status) {

        player.setIsPlaying(status);
    }

    /**
     * This is to keep changing status of the player. Custom status
     * @param text status text to be shown on the interface of the player
     */
    public void setStatus(String text) {

        status.setText(text);
    }

    /**
     * method update score board of player interface
     * @param Score score from the server
     */
    public void setScore(int Score) {

        scoreCard.setText("Your Score: " + Score);
        //for later purpose when we have server to change player score player.setScore(score);
    }

    /**
     * method to return the score
     *
     * @return returns score of the player
     */
    public int getScore() {
        return player.getScore();
    }

    /**
     * This method creates the top panel of the GUI which has a message and score label.
     **/
    public JPanel createTopPanel() {

        JPanel pan = new JPanel();
        BorderLayout layout = new BorderLayout();
        layout.setHgap(150);
        layout.setVgap(10);
        pan.setLayout(layout);
        scoreCard = new JLabel("Your Score: " + getScore());

        cardsLeft = new JLabel("Cards Left: ");
        pan.add(cardsLeft, BorderLayout.CENTER);
        pan.add(scoreCard, BorderLayout.LINE_START);

        return pan;
    }

    /**
     * This method creates the center panel of the the game GUI which has 4 buttons or cards.
     **/
    public JPanel createCenterPanel() {

        //create 3 panels
        JPanel cardsDeck = new JPanel();
        JPanel deckP = new JPanel();
        JPanel centerPanel = new JPanel();
        JLabel deckpLabel = new JLabel("Deck: ");
        JLabel cardsLabel = new JLabel("Your Deck: ");

        //set players common deck and their cards deck
        cardsDeck.setLayout(new BorderLayout());
        deckP.setLayout(new BorderLayout());


        GridLayout layout = new GridLayout(2, 1);
        centerPanel.setLayout(layout);
        layout.setVgap(50);

        //set them to center of its own panel so that they can be displayed big cards
        cardsDeck.add(cardsLabel, BorderLayout.NORTH);
        cardsDeck.add(createCards(), BorderLayout.CENTER);
        deckP.add(deckpLabel, BorderLayout.NORTH);
        deckP.add(createDeck(), BorderLayout.CENTER);
        cardsDeck.add(playingCard, BorderLayout.SOUTH);

        //add both decks to a common panel
        centerPanel.add(deckP);
        centerPanel.add(cardsDeck);


        return centerPanel;

    }

    /**
     * This method creates the player cards panel of the GUI which has 4 buttons or cards.
     **/
    public JPanel createCards() {
        JPanel pan = new JPanel();

        pan.setLayout(new GridLayout(1, 4));
        for (int i = 0; i < player.getCards().size(); i++) {

            pan.add(player.getCard(i));
        }

        return pan;
    }

    /**
     * This method creates the Deck panel of the GUI which has 2 buttons or cards.
     **/
    public JPanel createDeck() {

        JPanel pan = new JPanel();

        pan.setLayout(new GridLayout(1, 2));
        pan.add(player.playerCard);
        pan.add(player.opponentCard);

        return pan;
    }

    /**
     * This method creates the bottom panel of the GUI which has a quit button, start new game
     * button and message label.
     **/
    public JPanel createBottomPanel() {
        JPanel pan = new JPanel();
        status = new JLabel("Status: ");

        BorderLayout layout = new BorderLayout();
        layout.setHgap(150);
        pan.setLayout(layout);
        pan.add(quitButton, BorderLayout.LINE_START);
        pan.add(status, BorderLayout.CENTER);
        pan.add(newGameButton, BorderLayout.LINE_END);

        return pan;
    }


}