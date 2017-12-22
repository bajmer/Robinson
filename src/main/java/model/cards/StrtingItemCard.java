package model.cards;

import model.enums.cards.StartingItemType;

public class StrtingItemCard implements ICard {
    private static final int maxUseNumber = 2;
    private StartingItemType itemType;
    private int useLeftNumber;

    public StrtingItemCard(StartingItemType itemType) {
        this.itemType = itemType;
        this.useLeftNumber = maxUseNumber;
    }

    @Override
    public void useCard() {
        //todo
        this.useLeftNumber--;
    }
}
