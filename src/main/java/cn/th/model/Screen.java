package cn.th.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class Screen {
    private final static int shuffleNumber = 100;
    private final static int emptySize = 2;
    private final static int finalShuffleStatus = -1;
    private final static int notEndShuffleStatus = -2;

    private int colorSize;
    private int realSize;
    private List<Bottle> bottleList;

    public Screen() {
        colorSize = new Random().nextInt(8) + 3;
        realSize = colorSize + emptySize;
        bottleList = new ArrayList<>();
        initBottles();
        shuffleBottles();
    }

    private void initBottles() {
        Deque<Color> randomColorStack = Color.getRandomColorStack();
        for (int i = 0; i < colorSize; i++) {
            bottleList.add(new Bottle(randomColorStack.pop()));
        }
        for (int i = 0; i < emptySize; i++) {
            bottleList.add(new Bottle());
        }
    }

    private void shuffleBottles() {
        for (int i = 0; i < emptySize * Bottle.MAX; i++) {
            int index = new Random().nextInt(colorSize);
            bottleList.get(index).pourOutBySystem(bottleList.get(colorSize + i % 2));
        }
        for (int i = 0; i < shuffleNumber; i++) {
            int src = new Random().nextInt(realSize);
            int tar = new Random().nextInt(realSize);
            bottleList.get(src).pourOutBySystem(bottleList.get(tar));
        }
        int endIndex = realSize - 1;
        while (true) {
            int flag = checkShuffleFinish();
            if (flag == finalShuffleStatus) {
                break;
            } else if (flag == notEndShuffleStatus){
                allPourOut(endIndex);
                endIndex--;
            } else {
                shuffleBottles();
            }
        }
    }

    private void allPourOut(int index) {
        Bottle bottle = bottleList.get(index);
        for (int i = 0; i < bottleList.size(); i++) {
            if (bottle.isEmpty()) {
                return;
            }
            if (i == index) {
                continue;
            }
            boolean flag = true;
            while (flag) {
                flag = bottle.pourOutBySystem(bottleList.get(i));
            }
        }
    }

    private int checkShuffleFinish() {
        int emptyBottle = 0;
        int finalIndex = finalShuffleStatus;
        int i = 0;
        for (Bottle each : bottleList) {
            if (each.isEmpty()) {
                emptyBottle++;
            } else if (each.isFully()) {
                if (each.isFinal())
                    finalIndex = i;
            } else {
                return notEndShuffleStatus;
            }
            i++;
        }
        return finalIndex;
    }

    public List<Bottle> getBottleList() {
        return bottleList;
    }

    public boolean isSuccess() {
        int count = 0;
        for (Bottle each : bottleList) {
            if (each.isFinal())
                count++;
        }
        return count == colorSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < realSize; i++) {
            sb.append(i).append("\t").append(bottleList.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i=0;i<1000;i++) {
            Screen screen = new Screen();
            System.out.println(screen);
            System.out.println("****************" + i + "*******************");
        }

    }
}
