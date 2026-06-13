package org.firstinspires.ftc.teamcode;

import android.icu.text.Transliterator;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

public class PositionSingleton {
    private static PositionSingleton PositionSelector = null;
    SparkFunOTOS.Pose2D Position;
    private PositionSingleton() {
        Position = null;
    }
    public static PositionSingleton PositionInstance(){
        if (PositionSelector == null) {
            PositionSelector = new PositionSingleton();
        }
        return PositionSelector;
    }
    public void SetPosiiton(SparkFunOTOS.Pose2D PositionInput){
        Position = PositionInput;
    }
    public SparkFunOTOS.Pose2D GetPosition(){
        return Position;
    }
}
