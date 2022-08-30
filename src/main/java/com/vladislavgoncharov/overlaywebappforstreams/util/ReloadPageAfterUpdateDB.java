package com.vladislavgoncharov.overlaywebappforstreams.util;


public class ReloadPageAfterUpdateDB implements Runnable{
    private static boolean isUpdateDB = false;

    public static boolean isUpdateDB(){
        return isUpdateDB;
    }

    public static void valueIsUpdateDBTrue(){
        isUpdateDB = true;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            isUpdateDB = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
