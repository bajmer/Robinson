package model;

import model.enums.TerrainType;
import model.enums.cards.InventionType;

import java.util.List;

public class ActionRequirements {
    private List<TerrainType> requiredTerrains;
    private List<InventionType> requiredItems;
    private int requiredWoods;
    private int requiredHides;

    public ActionRequirements(List<TerrainType> requiredTerrains, List<InventionType> requiredItems, int requiredWoods, int requiredHides) {
        this.requiredTerrains = requiredTerrains;
        this.requiredItems = requiredItems;
        this.requiredWoods = requiredWoods;
        this.requiredHides = requiredHides;
    }

    public List<TerrainType> getRequiredTerrains() {
        return requiredTerrains;
    }

    public void setRequiredTerrains(List<TerrainType> requiredTerrains) {
        this.requiredTerrains = requiredTerrains;
    }

    public List<InventionType> getRequiredItems() {
        return requiredItems;
    }

    public void setRequiredItems(List<InventionType> requiredItems) {
        this.requiredItems = requiredItems;
    }

    public int getRequiredWoods() {
        return requiredWoods;
    }

    public void setRequiredWoods(int requiredWoods) {
        this.requiredWoods = requiredWoods;
    }

    public int getRequiredHides() {
        return requiredHides;
    }

    public void setRequiredHides(int requiredHides) {
        this.requiredHides = requiredHides;
    }
}
