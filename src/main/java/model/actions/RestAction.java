package model.actions;

public class RestAction extends Action {

    public RestAction() {
        super();
    }

    @Override
    public void runAction() {
        super.getAssignedMarkers().forEach(marker -> {
            marker.getCharacter().changeLife(1);
            super.getLogger().info("Odpoczynek!");
        });
    }
}