package model.cards;

import java.util.Collections;
import java.util.List;

public class CardDeck {
    private List<Card> deck;

    public CardDeck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public boolean hasCards() {
        return deck.size() != 0;
    }

    public Card getCard() {
        return deck.get(0);
    }
}
