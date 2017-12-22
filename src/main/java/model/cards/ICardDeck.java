package model.cards;

public interface ICardDeck {
    void shuffle();

    boolean isEmpty();

    boolean containsCard(ICard iCard);

    ICard getCardFromTop();

    void removeCard(ICard iCard);
}
