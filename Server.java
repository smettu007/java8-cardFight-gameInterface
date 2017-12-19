import java.util.ArrayList;
import java.awt.*;

/**
 * Create by subabramaiah mettu
 *
 * class to implement server logic
 */
public class Server {

    private GameInterface player0;
    private GameInterface player1;
    private ArrayList<Card> player0Cards, player1Cards;

    /**
     * constructor takingplayer 1 and player 2
     * @param p0 player 1
     * @param p1 player 2
     */
    public Server(GameInterface p0, GameInterface p1){

        player0 = p0;
        player1 = p1;
        player0Cards = player0.getCards();
        player1Cards = player1.getCards();
    }

    /**
     * method to pause the game for 4 seconds
     */
    public void waitPlayer(){

        player1.waiting();

        player0.waiting();

    }

    /**
     * Method to reset the deck once both players played their turn
     */
    public void resetDeck(){

        player0.setPlayingCard(new Card("Your Card"));
        player1.setPlayingCard(new Card("Your Card"));
        player0.setOpponentCard(new Card("Opponent Card"));
        player1.setOpponentCard(new Card("Opponent Card"));
    }

    /**
     * methdo to set the cards count of the player
     * @param player player number
     * @param count cards count of the player
     */
    public void setPlayerCardsCount(int player, int count){

        if(player ==1){

            player1.setCardsCount(count);
            player1.displayCardsCount();
        }else{
            player0.setCardsCount(count);
            player0.displayCardsCount();
        }
    }

    /**
     * methdo to play the card of the player based on the player number and card number
     * @param player player nubmer
     * @param cardNo card number
     */
    public void playCard(int player, int cardNo){

        if( player == 0){
            System.out.println("CLIENT --> SERVER " + player  +" : PLAY CARD " + player0Cards.get(cardNo).getName() + " POWER: " + player0Cards.get(cardNo).getPower() + " TYPE: " + player0Cards.get(cardNo).getType());
            player0.setPlayingCard(player0Cards.get(cardNo));
            player1.setOpponentCard(player0Cards.get(cardNo));
            player0Cards.get(cardNo).setVisible(false);
            player0.playCard();
        }

        if(player == 1){

            System.out.println("CLIENT --> SERVER " + player  +" : PLAY CARD " + player1Cards.get(cardNo).getName() + " POWER: " + player1Cards.get(cardNo).getPower() + " TYPE: " + player1Cards.get(cardNo).getType());
            player1.setPlayingCard(player1Cards.get(cardNo));
            player0.setOpponentCard(player1Cards.get(cardNo));
            player1Cards.get(cardNo).setVisible(false);
            player1.playCard();
        }
    }

    /**
     * metoof to quit the used based on player number
     * @param player player number
     */
    public void quit(int player){

        System.out.println("SERVER --> CLIENT " + player + ": QUIT PLAYER " + player);

        switch (player){

            case 0:

                //quit only if its current player turn
                if(player0.getIsPlaying()){

                    player0.setStatus("Status: You quit the game, you lost");
                    setWinner(1);
                }
                // player cannot quit because its not his turn
                else{
                    player0.setStatus("Status: cannot quit now, its not your turn");
                }
                break;

            case 1:
                if(player1.getIsPlaying()){

                    player1.setStatus("Status: You quit the game, you lost");
                    setWinner(0);
                }else{
                    player1.setStatus("Status: cannot quit now, its not your turn");
                }
                break;
            default:
        }


    }
    /**
     * sets the players
     * @param player value from server
     */
    public void setPlayer(String player){

        System.out.println("SERVER --> CLIENT " + player + ": SET PLAYER " + player);
    }

    /**
     * sets the player turn based on the val
     * @param player player number
     */
    public void playing(int player){

        System.out.println("SERVER --> CLIENT " + player + ": PLAYING " + player);

        if(player == 1){

            player1.setStatus(" Status: your turn");
            player0.setStatus(" Status: Player 1 turn to play");
            player0.setIsPlaying(false);
            player1.setIsPlaying(true);
        }else{

            player0.setStatus(" Status: your turn");
            player1.setStatus(" Status: player 0 turn to play");
            player1.setIsPlaying(false);
            player0.setIsPlaying(true);
        }
    }


    /**
     * method to give score to the player based on player number
     * @param player player number
     * @param score score to display
     */
    public void giveScore(int player, int score){

        System.out.println("SERVER --> CLIENT " + player + ": GIVE SCORE " + score);

        if(player == 1){

            player1.setScore(score);
        }else{

            player0.setScore(score);
        }
    }


    /**
     *method to set the winner based on the player number
     * @param player player number
     */
    public void setWinner(int player){

        System.out.println("SERVER --> CLIENT " + player + ": WINNER PLAYER " + player);

        if(player == 1){

            player1.setStatus("Status: You Won!");
            player0.setStatus("Status: You Lost!");
        }else{
            player0.setStatus("Status: You Won!");
            player1.setStatus("Status: You Lost!");
        }
    }
}
