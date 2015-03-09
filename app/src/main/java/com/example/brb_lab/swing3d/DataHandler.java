package com.example.brb_lab.swing3d;

import java.util.ArrayList;

public class DataHandler extends Thread
{
    private static DataHandler handler;
    private ArrayList<Float> list;

    public DataHandler()
    {}

    public static DataHandler getInstance()
    {
        if (handler == null)
            handler = new DataHandler();
        return handler;
    }

    public ArrayList<Float> getList()
    {
        return list;
    }

    public void setList(ArrayList<Float> list)
    {
        this.list = list;
    }
}