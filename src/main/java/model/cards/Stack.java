package model.cards;

import java.util.LinkedList;

public class Stack {
    private LinkedList<Usable> stack;

    public Stack() {
        stack = new LinkedList<>();
    }

    public LinkedList<Usable> getStack() {
        return stack;
    }

    public void setStack(LinkedList<Usable> stack) {
        this.stack = stack;
    }


}
