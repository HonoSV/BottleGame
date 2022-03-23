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
        bottle.push(color);
    }

    public boolean pourOutBySystem(Bottle target) {
        if (isEmpty() || target.isFully()) {
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
        StringBuilder sb2 = new StringBuilder();
        int count = 0;
        for (Color color : bottle) {
            String colorCN = color.getCN();
            sb2.append(colorCN).append(" ");
            count++;
        }
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < MAX - count; i++) {
            sb1.append("口").append(" ");
        }
        return sb.append(sb1).append(sb2).toString().trim() + "]";
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
