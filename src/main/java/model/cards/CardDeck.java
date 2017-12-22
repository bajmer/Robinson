package model.cards;

import java.util.Collections;
import java.util.List;

public class CardDeck implements ICardDeck {
    private List<ICard> deck;

    public CardDeck(List<ICard> deck) {

        this.deck = deck;
    }

    public List<ICard> getDeck() {
        return deck;
    }

    public void setDeck(List<ICard> deck) {
        this.deck = deck;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    @Override
    public boolean containsCard(ICard iCard) {
        return deck.contains(iCard);
    }

    @Override
    public ICard getCardFromTop() {
        ICard card = deck.get(0);
        deck.remove(0);
        return card;
    }

    @Override
    public void removeCard(ICard iCard) {
        deck.remove(iCard);
    }
}
