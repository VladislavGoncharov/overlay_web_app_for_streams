package com.vladislavgoncharov.overlaywebappforstreams.util;


public class ReloadPageAfterUpdateDB {
    private static boolean isUpdateDB = false;

    public static boolean isUpdateDB(){
        return isUpdateDB;
    }
    public static void valueIsUpdateDBFalse(){
        isUpdateDB = false;
    }
    public static void valueIsUpdateDBTrue(){
        isUpdateDB = true;
    }
}
