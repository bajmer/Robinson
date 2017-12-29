package model.cards;

import java.util.LinkedList;

public class CardsDeck {
    private LinkedList<ICard> deck;

    public CardsDeck() {
        deck = new LinkedList<>();
    }

    public LinkedList<ICard> getDeck() {
        return deck;
    }

    public void setDeck(LinkedList<ICard> deck) {
        this.deck = deck;
    }


}
