package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by citruseel on 6/10/2016.
 */
public class ServoTest extends LinearOpMode {

    private Servo testing;
    private double servoposition = 0;

    public void runOpMode() throws InterruptedException{
        hardwareMap.servo.get("servo");
        servoposition= Range.clip(servoposition, 0, 1);//range of servo values is between 0 and 1
        waitForStart();
        ServoMove(0.5);
        ServoMove(0);
    }
    public void ServoMove(double servoposition)
    {
        testing.setPosition(servoposition);
    }
}
