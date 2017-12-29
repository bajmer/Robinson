package model.cards;

import model.enums.TerrainType;

public class IslandTile implements Usable {
    private int id;
    private TerrainType terrainType;
    private boolean hasWoodSource;
    private boolean hasFoodSource;
    private boolean hasAnimalSoure;
    private int totemsNumber;
    private int discoveryTokensNumber;
    private boolean hasNaturalShelter;

    public IslandTile(int id, TerrainType terrainType, boolean hasWoodSource, boolean hasFoodSource, boolean hasAnimalSoure, int totemsNumber, int discoveryTokensNumber, boolean hasNaturalShelter) {
        this.id = id;
        this.terrainType = terrainType;
        this.hasWoodSource = hasWoodSource;
        this.hasFoodSource = hasFoodSource;
        this.hasAnimalSoure = hasAnimalSoure;
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

    public boolean isHasWoodSource() {
        return hasWoodSource;
    }

    public void setHasWoodSource(boolean hasWoodSource) {
        this.hasWoodSource = hasWoodSource;
    }

    public boolean isHasFoodSource() {
        return hasFoodSource;
    }

    public void setHasFoodSource(boolean hasFoodSource) {
        this.hasFoodSource = hasFoodSource;
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
