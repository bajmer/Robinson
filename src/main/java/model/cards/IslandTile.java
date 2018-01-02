package model.cards;

import model.enums.ResourceType;
import model.enums.TerrainType;

public class IslandTile implements Usable {
    private int id;
    private TerrainType terrainType;
    private boolean hasAnimalSoure;
    private int totemsNumber;
    private int discoveryTokensNumber;
    private boolean hasNaturalShelter;
    private ResourceType leftSquareResource;
    private ResourceType rightSquareResource;

    public IslandTile(int id, TerrainType terrainType, ResourceType leftSquareResource, ResourceType rightSquareResource, boolean hasAnimalSource, int totemsNumber, int discoveryTokensNumber, boolean hasNaturalShelter) {
        this.id = id;
        this.terrainType = terrainType;
        this.leftSquareResource = leftSquareResource;
        this.rightSquareResource = rightSquareResource;
        this.hasAnimalSoure = hasAnimalSource;
        this.totemsNumber = totemsNumber;
        this.discoveryTokensNumber = discoveryTokensNumber;
        this.hasNaturalShelter = hasNaturalShelter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }

    public boolean isHasAnimalSoure() {
        return hasAnimalSoure;
    }

    public void setHasAnimalSoure(boolean hasAnimalSoure) {
        this.hasAnimalSoure = hasAnimalSoure;
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

    public boolean isHasNaturalShelter() {
        return hasNaturalShelter;
    }

    public void setHasNaturalShelter(boolean hasNaturalShelter) {
        this.hasNaturalShelter = hasNaturalShelter;
    }

    @Override
    public void use() {

    }
}
