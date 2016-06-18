package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by citruseel on 6/10/2016.
 */
public class ServoTestPrgm extends LinearOpMode {

    private Servo testing;

    public void runOpMode() throws InterruptedException{
        testing = hardwareMap.servo.get("servo");
        waitForStart();
        ServoMove();
        ServoStart();
    }
    
    //Sets servo position to 0
    public void ServoStart()
    {
        testing.setPosition(0);
        telemetry.addData("ServoTest", "Servo: " + testing);
    }
    
    //Sets servo position to 0.5
    public void ServoMove()
    {
        testing.setPosition(0.5);
        telemetry.addData("ServoTest", "Servo: " + testing);
    }
}
