package model.cards;

import model.enums.cards.StartingItemType;

public class StartingItemCard implements ICard {
    private static final int maxUseNumber = 2;
    private StartingItemType itemType;
    private int useLeftNumber;

    public StartingItemCard(StartingItemType itemType) {
        this.itemType = itemType;
        this.useLeftNumber = maxUseNumber;
    }

    @Override
    public void useCard() {
        //todo
        this.useLeftNumber--;
    }

    @Override
    public String toString() {
        return "StartingItemCard{" +
                "itemType=" + itemType +
                '}';
    }
}
