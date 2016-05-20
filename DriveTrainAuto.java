package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by azure_Y410P on 5/18/2016.
 *
 * Robot moves at ~1 ft/s
 */
public class DriveTrainAuto extends OpMode
    {
        //Declaring Drive Controllers
        private DcMotorController motorController;

        //Declaring the motors!
        private DcMotor motorL; //Left Motor
        private DcMotor motorR; //Right Motor




        @Override
        public void init(){
            //Initiating DC motor controller!
            motorController = hardwareMap.dcMotorController.get("DcMotor");

            //Initiating motors! Yee :^)
            motorL = hardwareMap.dcMotor.get("L");
            motorR = hardwareMap.dcMotor.get("R");

            //Setting channel modes?
            motorL.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            motorR.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

            //Reverses the right motor
            motorR.setDirection(DcMotor.Direction.REVERSE);

        }

        public void main() throws InterruptedException
        {
            //"Wait for game to start"
            //waitForStart();

            //Commence the autonomous!
            //Paste
            MoveForwardTime(1,5000);

        }

        @Override
        public void loop(){


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

        public void MoveLeft(double power)
        {
            motorL.setPower(-power);
            motorR.setPower(power);
        }
        public void MoveLeftTime(double power, long time) throws InterruptedException
        {
            MoveLeft(power);
            Thread.sleep(time);
        }

        public void MoveRight(double power)
        {
            motorL.setPower(power);
            motorR.setPower(-power);
        }
        public void MoveRightTime(double power, long time) throws InterruptedException
        {
            MoveRight(power);
            Thread.sleep(time);
        }

        public void MoveBackward(double power)
        {
            motorL.setPower(-power);
            motorR.setPower(-power);
        }
        public void MoveBackwardTime(double power, long time) throws InterruptedException
        {
            MoveForward(power);
            Thread.sleep(time);
        }

        public void StopMoving(double power)
        {
            MoveForward(0);
        }



    }



