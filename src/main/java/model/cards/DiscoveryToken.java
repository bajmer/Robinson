package model.cards;

import model.enums.cards.DiscoveryTokenType;

public class DiscoveryToken implements Usable {

    private DiscoveryTokenType discoveryToken;

    public DiscoveryToken(DiscoveryTokenType discoveryToken) {
        this.discoveryToken = discoveryToken;
    }

    @Override
    public void use() {

    }
}
