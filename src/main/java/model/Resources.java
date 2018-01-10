package model;

public class Resources {
    private int woodAmount;
    private int foodAmount;
    private int longExpiryDateFoodAmount;
    private int hideAmount;

    public Resources() {
        woodAmount = 0;
        foodAmount = 0;
        longExpiryDateFoodAmount = 0;
        hideAmount = 0;
    }

    public int getWoodAmount() {
        return woodAmount;
    }

    public void setWoodAmount(int woodAmount) {
        this.woodAmount = woodAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getLongExpiryDateFoodAmount() {
        return longExpiryDateFoodAmount;
    }

    public void setLongExpiryDateFoodAmount(int longExpiryDateFoodAmount) {
        this.longExpiryDateFoodAmount = longExpiryDateFoodAmount;
    }

    public int getHideAmount() {
        return hideAmount;
    }

    public void setHideAmount(int hideAmount) {
        this.hideAmount = hideAmount;
    }
}
