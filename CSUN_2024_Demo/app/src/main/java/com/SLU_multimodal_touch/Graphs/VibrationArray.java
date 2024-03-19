package com.SLU_multimodal_touch.Graphs;

//The classes Vibration, VibrationArray, VibrationCommand, VibrationManager, VibrationPattern, and VbrationStep are all taken and modified from: https://bitbucket.org/stefika/androidquorum/src/master/AndroidHaptic/src/

import java.util.ArrayList;
import java.util.List;

public class VibrationArray {
    public Object me_;
    List<VibrationCommand> content;

    public VibrationArray () {
        this.content = new ArrayList<VibrationCommand>();
    }

    public VibrationArray(List<VibrationCommand> content) {
        this.content = content;
    }

    public void Add(VibrationCommand item) {
        content.add(item);
    }

    public void Add(double duration) {
        VibrationCommand command = new VibrationCommand();
        command.SetDuration(duration);
        command.SetIntensity(1.0);
        content.add(command);
    }    

    public void Add(double duration, double intensity) {
        VibrationCommand command = new VibrationCommand();
        command.SetDuration(duration);
        command.SetIntensity(intensity);
        content.add(command);
    }

    public double GetDuration(int index) {
        return content.get(index).GetDuration();
    }

    public double GetIntensity(int index) {
        return content.get(index).GetIntensity();
    }

    public void Remove(int index) {
        content.remove(index);
    }

    public void RemoveAll() {
        content.clear();
    }

    public int GetSize() {
        return content.size();
    }
 }
