package cn.th.model;

import java.util.*;

public enum Color {
    RED("红", "RED", "#FF0000"),
    ORANGE("橙", "ORANGE", "#FFA500"),
    YELLOW("黄", "YELLOW", "#FFFF00"),
    GREEN("绿", "GREEN", "#008000"),
    CYAN("青", "CYAN", "#00FFFF"),
    BLUE("蓝", "BLUE", "#0000FF"),
    PURPLE("紫", "PURPLE", "#800080"),
    GRAY("灰", "GRAY", "#808080"),
    PINK("粉", "PINK", "#FFC0CB"),
    GOLD("金", "GOLD", "#FFD700"),
    BROWN("棕", "BROWN", "#A52A2A");

    private String CN;
    private String EN;
    private String code;

    Color(String CN, String EN, String code) {
        this.CN = CN;
        this.EN = EN;
        this.code = code;
    }

    public String getCN() {
        return CN;
    }

    public String getEN() {
        return EN;
    }

    public String getCode() {
        return code;
    }

    public static Deque<Color> getRandomColorStack() {
        List<Color> colorList = new ArrayList<>();
        Collections.addAll(colorList, Color.values());
        Collections.shuffle(colorList);
        ArrayDeque<Color> randomColorStack = new ArrayDeque<>();
        for (Color each : colorList) {
            randomColorStack.push(each);
        }
        return randomColorStack;
    }
}
