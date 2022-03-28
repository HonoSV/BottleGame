package cn.th.model;

import java.util.List;
import java.util.Scanner;

public class Operate {
    public static void pour(String input, Screen screen) {
        List<Bottle> bottleList = screen.getBottleList();
        char[] arr = input.toUpperCase().toCharArray();
        int srcIndex = getIndexByChar(arr[0]);
        // System.out.println("src" + srcIndex);
        int tarIndex = getIndexByChar(arr[1]);
        // System.out.println("tar" + tarIndex);
        Bottle srcBottle = bottleList.get(srcIndex);
        Bottle tarBottle = bottleList.get(tarIndex);
        srcBottle.pourOutByUser(tarBottle);
    } 

    private static int getIndexByChar(char c) {
        if (c >= '0' && c <= '9')
            return c - '0' + 0;
        else if (c >= 'A' && c <= 'F')
            return c - 'A' + 10;
        else
            return -1;
    }

    public static void main(String[] args) {
        Screen screen = new Screen();
        System.out.println(screen);
        Scanner sc = new Scanner(System.in);
        while(true) {
            if (sc.hasNextLine()) {
                System.out.print("\033[H\033[2J");
            }
            String input = sc.nextLine();
            pour(input, screen);
            if (screen.isSuccess()) {
                System.out.println("WIN!!!");
                break;
            }
            System.out.println(screen);
        }
        sc.close();
    }
}
