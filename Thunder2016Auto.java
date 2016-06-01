package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by citruseel on 5/22/2016.
 */
public class Thunder2016Auto extends LinearOpMode{


    //Declaring the motors!
    DcMotor motorL; //Left Motor
    DcMotor motorR; //Right Motor


    public void runOpMode() throws InterruptedException
    {


        //Initiating motors!
        motorL = hardwareMap.dcMotor.get("L");
        motorR = hardwareMap.dcMotor.get("R");

        //set channel mode?
        motorL.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorR.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        //Reverses the right motor
        motorL.setDirection(DcMotor.Direction.REVERSE);

        //"Wait for game to start"
        waitForStart();

        //Commence the autonomous!
        //Paste
        MoveForwardTime(1,3000);
        TurnRightTime(1,5000);
        TurnLeftTime(1, 5000);
        MoveBackwardTime(1, 3000);

    }


    //Do "public void [methodName]" to create another another one (method/function)! ^_^
    //The variable "long" indicates that the time will be in milliseconds (i.e. long 1000 = 1 second)
    public void MoveForward(double power)
    {
        motorL.setPower(power);
        motorR.setPower(power);
    }
    public void MoveForwardTime(double power, long time) throws InterruptedException
    {
        MoveForward(power);
        Thread.sleep(time);
    }

    public void TurnLeft(double power)
    {
        motorL.setPower(-power);
        motorR.setPower(power);
    }
    public void TurnLeftTime(double power, long time) throws InterruptedException
    {
        TurnLeft(power);
        Thread.sleep(time);
    }

    public void TurnRight(double power)
    {
        motorL.setPower(power);
        motorR.setPower(-power);
    }
    public void TurnRightTime(double power, long time) throws InterruptedException
    {
        TurnRight(power);
        Thread.sleep(time);
    }

    public void MoveBackward(double power)
    {
        motorL.setPower(-power);
        motorR.setPower(-power);
    }
    public void MoveBackwardTime(double power, long time) throws InterruptedException
    {
        MoveBackward(power);
        Thread.sleep(time);
    }

    public void StopMoving(double power)
    {
        MoveForward(0);
    }



}
