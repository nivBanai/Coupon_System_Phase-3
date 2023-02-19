package com.jb.coupon_system.utils;

public class PrintUtils {

    private static final String RESET_TEXT_COLOR = "\u001B[0m";
    private static final String PURPLE_TEXT = "\u001B[35m";
    private static final String BLUE_TEXT = "\u001B[34m";
    private static final String RED_TEXT = "\u001B[31m";
    private static final String GREEN_TEXT = "\u001B[32m";
    private static final String CHECK_MARK = "\u2713";

    private static void printUnderline(String content) {
        for (int i = 0; i < content.length(); i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void print(String content) {
        String toPrint = "@@@@@ " + content + " @@@@@";
        printUnderline(toPrint);
        System.out.println(BLUE_TEXT + toPrint + RESET_TEXT_COLOR);
        printUnderline(toPrint);
    }

    public static void printTitle(String title) {
        String toPrint = "##### " + title + " #####";
        printUnderline(toPrint);
        System.out.println(PURPLE_TEXT + toPrint + RESET_TEXT_COLOR);
        printUnderline(toPrint);
    }

    public static void printSuccess(String msg) {
        System.out.println(GREEN_TEXT + CHECK_MARK + " " + msg + RESET_TEXT_COLOR);
    }

    public static void printRes(int resCode, int desiredResCode) {
        System.out.print("Res: ");
        System.out.print((resCode == desiredResCode) ?
                GREEN_TEXT + "Success\n" + RESET_TEXT_COLOR : RED_TEXT + "Failure\n" + RESET_TEXT_COLOR);
    }
}
