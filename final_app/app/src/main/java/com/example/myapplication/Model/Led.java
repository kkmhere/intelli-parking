package com.example.myapplication.Model;

public class Led {
    private boolean greenLed;
    private boolean redLed;


    public Led() {

    }

    public Led(boolean greenLed, boolean redLed) {
        this.greenLed = greenLed;
        this.redLed = redLed;
    }

    public boolean isGreenLed() {
        return greenLed;
    }

    public void setGreenLed(boolean greenLed) {
        this.greenLed = greenLed;
    }

    public boolean isRedLed() {
        return redLed;
    }

    public void setRedLed(boolean redLed) {
        this.redLed = redLed;
    }
}