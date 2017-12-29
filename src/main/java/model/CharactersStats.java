package model;

public class CharactersStats {
    private int moraleLevel;
    private int roofLevel;
    private int palisadeLevel;
    private int weaponLevel;

    public CharactersStats() {
        moraleLevel = 0;
        roofLevel = 0;
        palisadeLevel = 0;
        weaponLevel = 0;
    }

    public int getMoraleLevel() {
        return moraleLevel;
    }

    public void setMoraleLevel(int moraleLevel) {
        this.moraleLevel = moraleLevel;
    }

    public int getRoofLevel() {
        return roofLevel;
    }

    public void setRoofLevel(int roofLevel) {
        this.roofLevel = roofLevel;
    }

    public int getPalisadeLevel() {
        return palisadeLevel;
    }

    public void setPalisadeLevel(int palisadeLevel) {
        this.palisadeLevel = palisadeLevel;
    }

    public int getWeaponLevel() {
        return weaponLevel;
    }

    public void setWeaponLevel(int weaponLevel) {
        this.weaponLevel = weaponLevel;
    }
}
