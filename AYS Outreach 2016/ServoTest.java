package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by citruseel on 6/10/2016.
 */
public class ServoTest extends LinearOpMode {

    private Servo testing;

    public void runOpMode() throws InterruptedException{
        testing = hardwareMap.servo.get("servo");
        waitForStart();
        ServoMove();
        ServoStart();
    }
    public void ServoStart()
    {
        testing.setPosition(0);
    }
    public void ServoMove()
    {
        testing.setPosition(0.5);
    }
}
