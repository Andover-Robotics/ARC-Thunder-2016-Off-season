package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by azure_000 on 5/4/2016.
 */

/**Important Stuff!
 * Resource: http://paws.kettering.edu/~webe3546/FTCJavaProgramming.pdf
 *
 */

public class DrivetrainBasic extends OpMode{

    //Declaring Drive Controllers
    private DcMotorController motorController;

    //Declaring the motors!
    private DcMotor motorLeft; //Left Motor
    private DcMotor motorRight; //Right Motor


    @Override
    public void init(){
        //Initiating DC motor controller!
        motorController = hardwareMap.dcMotorController.get("DcMotor");

        //Initiating motors! Yee :^)
        motorLeft = hardwareMap.dcMotor.get("L");
        motorRight = hardwareMap.dcMotor.get("R");

        //Setting channel modes?
        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        //Reverses the right motor
        motorRight.setDirection(DcMotor.Direction.REVERSE);

        //"Wait for game to start"
        //waitForStart();

        //Commence the robot!

    }

    @Override
    public void loop(){
        //Allows the robot to move! :) xD xD xD
        motorLeft.setPower(gamepad1.left_stick_y);
        motorRight.setPower(gamepad1.right_stick_y);
    }

}
