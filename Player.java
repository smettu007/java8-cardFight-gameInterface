import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
/**
 * Create by subabramaiah mettu
 *
 * class to implement player logic and its properties
 */
public class Player {
    //currently public to make testing easier but will be private eventually
    //public variables
    public int cardNo, cardsCount;
    public int playerID;
    public Card playerCard;
    public Card opponentCard;
    public Card selectedCard;

    //instance variables
    private static int ID = 0;
    private boolean isPlaying;
    private int score;
    private ArrayList<Card> cards;


    public Player() {

        playerID = ID;
        ID++;
        cardsCount = 4;
        selectedCard = null;
        playerCard = new Card("your card");
        opponentCard = new Card("Opponent Card");
        cards = new ArrayList<Card>();
        score = 0;
        generateCards();
    }

    /**
     * method to return the card of the played based on the index
     * @param index index of the card
     * @return
     */
    public Card getCard(int index) {

        return cards.get(index);
    }

    /**
     * heper method gives the score of the player
     * @return returns the score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * method to change the replace the card in index that used wants
     * @param index index of the card
     * @param card card to replace with
     */
    public void setCard(int index, Card card) {

        cards.set(index, card);
    }

    /**
     * helper method to return the cards of the player
     * @return
     */
    public ArrayList<Card> getCards() {

        return cards;
    }

    /**
     * methdod to see if its current players turn or not
     * @return returns boolean value, true if its his turn false if not
     */
    public boolean getIsPlaying() {

        return isPlaying;
    }

    /**
     * methdo to set the playing  status of the player
     * @param status bollean, true if its current player turn, false if not
     */
    public void setIsPlaying(boolean status) {

        isPlaying = status;
    }

    /**
     * methdo to generate cards of the player, can be random p11
     */
    public void generateCards() {
        for (int i = 0; i < cardsCount; i++) {

            cards.add(new Card("dino", 10, "Strength", "Intelligence"));

        }
        cards.get(0).setName("zeus", 8, "Intelligence", "Agility");

    }
}
