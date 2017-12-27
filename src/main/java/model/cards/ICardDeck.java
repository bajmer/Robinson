package model.cards;

public interface ICardDeck {
    void shuffle();

    boolean containsCard(ICard iCard);

    ICard getCardFromTop();

    ICard getCard(ICard iCard);

    void removeCard(ICard iCard);
}
