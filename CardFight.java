import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.Color;

/**
 * Program Created by Subbaramaiah Mettu
 *
 * Note: This program is just a GUI without implementation. Game logic is not complete yet.
 *
 * This program is a card fight game where two players can play together. This game is similar to trading cards game where
 * you have to take turns in playing a card and oompare each card stats and determine the winner for the round.
 * Player who scored most points wins the game
 *
 */
public class CardFight {

    public static void main(String[] args){

        //creating two players and starting a mock server
        Player p0 = new Player();
        Player p1 = new Player();
        GameInterface player0 = new GameInterface(p0);
        GameInterface player1 = new GameInterface(p1);
        ServerInterface server = new ServerInterface(player0,player1);

    }
}
