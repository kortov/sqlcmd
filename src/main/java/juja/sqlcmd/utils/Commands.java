package juja.sqlcmd.utils;

public class Commands {
    public static int sizeOfSplitArray(String string) {
        return string.split("\\|").length;
    }

    public static String[] splitArrayOf(String string) {
        return string.split("\\|");
    }
}
