package model.cards;

import java.util.LinkedList;

public class CardDeck {
    private LinkedList<ICard> deck;

    public CardDeck() {
        deck = new LinkedList<>();
    }

    public LinkedList<ICard> getDeck() {
        return deck;
    }

    public void setDeck(LinkedList<ICard> deck) {
        this.deck = deck;
    }


}
