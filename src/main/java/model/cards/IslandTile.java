package model.cards;

import model.enums.TerrainType;
import model.enums.elements.ResourceType;

public class IslandTile {
    private int tileId;
    private int positionOnBoard;
    private boolean tileDiscovered;
    private TerrainType terrainType;
    private boolean animalSource;
    private int totemsNumber;
    private int discoveryTokensNumber;
    private boolean naturalShelter;
    private ResourceType leftSquareResource;
    private ResourceType rightSquareResource;
    private boolean terrainTypeCovered;
    private boolean leftSquareResourceCovered;
    private boolean rightSquareResourceCovered;
    private boolean fog;
    private boolean cross;

    public IslandTile(int tileId, TerrainType terrainType, ResourceType leftSquareResource, ResourceType rightSquareResource, boolean animalSource, int totemsNumber, int discoveryTokensNumber, boolean naturalShelter) {
        this.tileId = tileId;
        this.positionOnBoard = 0;
        this.tileDiscovered = false;
        this.terrainType = terrainType;
        this.leftSquareResource = leftSquareResource;
        this.rightSquareResource = rightSquareResource;
        this.animalSource = animalSource;
        this.totemsNumber = totemsNumber;
        this.discoveryTokensNumber = discoveryTokensNumber;
        this.naturalShelter = naturalShelter;
        this.terrainTypeCovered = false;
        this.leftSquareResourceCovered = false;
        this.rightSquareResourceCovered = false;
        this.fog = false;
        this.cross = false;
    }

    public int getTileId() {
        return tileId;
    }

    public void setTileId(int tileId) {
        this.tileId = tileId;
    }

    public int getPositionOnBoard() {
        return positionOnBoard;
    }

    public void setPositionOnBoard(int positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    public boolean isTileDiscovered() {
        return tileDiscovered;
    }

    public void setTileDiscovered(boolean tileDiscovered) {
        this.tileDiscovered = tileDiscovered;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }

    public boolean isAnimalSource() {
        return animalSource;
    }

    public void setAnimalSource(boolean animalSource) {
        this.animalSource = animalSource;
    }

    public int getTotemsNumber() {
        return totemsNumber;
    }

    public void setTotemsNumber(int totemsNumber) {
        this.totemsNumber = totemsNumber;
    }

    public int getDiscoveryTokensNumber() {
        return discoveryTokensNumber;
    }

    public void setDiscoveryTokensNumber(int discoveryTokensNumber) {
        this.discoveryTokensNumber = discoveryTokensNumber;
    }

    public boolean isNaturalShelter() {
        return naturalShelter;
    }

    public void setNaturalShelter(boolean naturalShelter) {
        this.naturalShelter = naturalShelter;
    }

    public ResourceType getLeftSquareResource() {
        return leftSquareResource;
    }

    public void setLeftSquareResource(ResourceType leftSquareResource) {
        this.leftSquareResource = leftSquareResource;
    }

    public ResourceType getRightSquareResource() {
        return rightSquareResource;
    }

    public void setRightSquareResource(ResourceType rightSquareResource) {
        this.rightSquareResource = rightSquareResource;
    }

    public boolean isTerrainTypeCovered() {
        return terrainTypeCovered;
    }

    public void setTerrainTypeCovered(boolean terrainTypeCovered) {
        this.terrainTypeCovered = terrainTypeCovered;
    }

    public boolean isLeftSquareResourceCovered() {
        return leftSquareResourceCovered;
    }

    public void setLeftSquareResourceCovered(boolean leftSquareResourceCovered) {
        this.leftSquareResourceCovered = leftSquareResourceCovered;
    }

    public boolean isRightSquareResourceCovered() {
        return rightSquareResourceCovered;
    }

    public void setRightSquareResourceCovered(boolean rightSquareResourceCovered) {
        this.rightSquareResourceCovered = rightSquareResourceCovered;
    }

    public boolean isFog() {
        return fog;
    }

    public void setFog(boolean fog) {
        this.fog = fog;
    }

    public boolean isCross() {
        return cross;
    }

    public void setCross(boolean cross) {
        this.cross = cross;
    }
}
