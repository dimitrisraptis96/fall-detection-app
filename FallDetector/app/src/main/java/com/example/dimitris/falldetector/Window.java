package com.example.dimitris.falldetector;

import java.util.Collections;
import java.util.LinkedList;

public class Window {

    private final float THRESHOLD =  0.8f;
    private final int DEFAULT_SIZE = 10;

    private final int SIZE;

    LinkedList<Float> samples;

    public Window (){
        SIZE = DEFAULT_SIZE;
        samples = new LinkedList<Float>();
    }

    public Window (int size){
        SIZE = size;
        samples = new LinkedList<Float>();
    }

    public void add(float value){
        if (isFull()){
            samples.add(value);
            //add value
        } else {
            samples.removeFirst();
            samples.add(value);
        }
    }

    public void clear(){
        samples.clear();
    }

    public Boolean isFull(){
        return (samples.size() > SIZE);
    }

    public Boolean isFallDetected(){
        Float max = Collections.max(samples);
        Float min = Collections.min(samples);
        Float diff = Math.abs(max-min);

        // check if min value detected earlier than max
        Boolean isFall = ( samples.indexOf(max) > samples.indexOf(min) );

        return (diff>THRESHOLD && isFall);
    }



}
