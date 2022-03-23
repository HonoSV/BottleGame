package cn.th.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Stack;

public class Bottle {
    public static final int MAX = 4;

    private ArrayDeque<Color> bottle = new ArrayDeque<>(MAX);

    public Bottle() {}

    public Bottle(Color color) {
        for (int i = 0; i < MAX; i++) {
            bottle.add(color);
        }
    }

    public boolean isEmpty() {
        return bottle.size() == 0;
    }

    public boolean isFully() {
        return bottle.size() == MAX;
    }

    public boolean isFinal() {
        if (isFully()) {
            for (Color each : bottle) {
                if (!each.equals(bottle.getFirst()))
                    return false;
            }
            return true;
        }
        return false;
    }

    public boolean willFinal(Color color) {
        if (bottle.size() == MAX - 1) {
            for (Color each :  bottle) {
                if (!each.equals(color))
                    return false;
            }
            return true;
        }
        return false;
    }

    public boolean noFusion(Color color) {
        if (bottle.peek() == null)
            return false;
        return !color.equals(bottle.peek());
    }

    public void pourIn(Color color) {
        bottle.add(color);
    }

    public boolean pourOutBySystem(Bottle target) {
        if (isEmpty() || target.isFully() || target.willFinal(bottle.peek())) {
            return false;
        }
        target.pourIn(bottle.pop());
        return true;
    }

    public boolean pourOutByUser(Bottle target) {
        if (isEmpty() || isFinal() || target.isFully() || target.noFusion(bottle.peek())) {
            return false;
        }
        target.pourIn(bottle.pop());
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int count = 0;
        for (Color color : bottle) {
            String colorCN = color.getCN();
            sb.append(colorCN).append(" ");
            count++;
        }
        for (int i = 0; i < MAX - count; i++) {
            sb.append("口").append(" ");
        }
        return sb.toString().trim() + "]";
    }

    public static void main(String[] args) {
        Bottle bottleBlue = new Bottle(Color.BLUE);
        Bottle bottleRed = new Bottle(Color.RED);
        Bottle other = new Bottle();
        System.out.println(bottleBlue.pourOutBySystem(other));
        bottleRed.pourOutBySystem(bottleBlue);
        System.out.println(bottleBlue);
        System.out.println(other);
        System.out.println(bottleRed);
        System.out.println(new Bottle());
    }
}
