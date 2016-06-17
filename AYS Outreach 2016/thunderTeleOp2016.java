/**
 * Created by azure_000 on 5/4/2016, edited by citruseel.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.GyroSensor;


/**
 * Created by admin on 5/18/16.
 */

public class thunderTeleOp2016 extends OpMode {
    private DcMotor L;//left wheel motor
    private DcMotor R;//right wheel motor
    private DcMotor ArmBase;//first part of arm
    private DcMotor Winch;//winch motor
    //private UltrasonicSensor U;//was going to be used in autonomous
    //private GyroSensor G;//^
    private Servo servoArm;//arm servo
    private Servo leftHook;//left hook
    private Servo rightHook;//right hook
    private Servo leftPlow;//left plow servo
    private Servo rightPlow;//right plow servo
    private double servoposition = 1;//arms start in robot, our robot's servo is reverse so code accordingly
    private double servoposition2 = 0;
    private double servoposition3 = 0.45;
    private double servoposition4 = 0.36;//plow starts in robot
    private double servoposition5 = 0.36;

    @Override
    public void init(){
        L = hardwareMap.dcMotor.get("L");//correspondence of hardware map names
        R = hardwareMap.dcMotor.get("R");
        ArmBase = hardwareMap.dcMotor.get("A");
        Winch = hardwareMap.dcMotor.get("B");
        //U = hardwareMap.ultrasonicSensor.get("U");
        //G = hardwareMap.gyroSensor.get("G");
        servoArm = hardwareMap.servo.get("servo");
        leftHook = hardwareMap.servo.get("servo2");
        rightHook = hardwareMap.servo.get("servo3");
        leftPlow = hardwareMap.servo.get("servo4");
        rightPlow = hardwareMap.servo.get("servo5");

        R.setDirection(DcMotor.Direction.REVERSE);//reverses the right motor
    }
    @Override
    public void loop(){
        //setting speeds of motors:
        //drivetrain has logarithmic control - cubed so numbers maintain sign
        double left = Math.pow(gamepad1.left_stick_y, 3);//y value of left stick on gamepad 1 controls the left wheel
        double right = Math.pow(gamepad1.right_stick_y, 3);//y value of right stick on gamepad 1 controls the right wheel
        double motorArmBase = -gamepad2.left_stick_y*0.7;//y value of left stick on gamepad 2 controls unwinding of the hanging mechanism

        //Setting ranges of motors:
        left = Range.clip(left, -1, 1);//range of motor values is between -1 and 1
        right= Range.clip(right, -1, 1);
        motorArmBase=Range.clip(motorArmBase, -1, 1);
        //Motor B does not need a range because it has set values rather than being controlled by a joystick
        //Y and B buttons on gamepad 2 determine whether slack is given or rope is pulled in:

        if (gamepad2.y == true){
            Winch.setPower(1); //Power can be changed according to preference
        }
        else if (gamepad2.b == true){
            Winch.setPower(-1);
        }
        else {
            Winch.setPower(0);
        }

        //Setting ranges of servos:
        servoposition=Range.clip(servoposition, 0, 1);//range of servo values is between 0 and 1
        servoposition2=Range.clip(servoposition2, 0, 0.45);//stress is put on hook servos if they attempt to pass 0.45
        servoposition3=Range.clip(servoposition3, 0, 0.45);
        servoposition4=Range.clip(servoposition4, 0, 1);
        servoposition5=Range.clip(servoposition5, 0, 1);
        //Y value of the right stick on gamepad 2 controls the arm with the hook:
        if(-gamepad2.right_stick_y < -0.75) {
            servoposition = servoposition + 0.004;
        }
        else if(-gamepad2.right_stick_y > -0.75 && -gamepad2.right_stick_y < -0.1){
            servoposition = servoposition + 0.001;
        }
        else if(-gamepad2.right_stick_y > 0.75){
            servoposition = servoposition - 0.004;
        }
        else if(-gamepad2.right_stick_y < 0.75 && -gamepad2.right_stick_y > 0.1){
            servoposition = servoposition - 0.001;
        }
        //Y value of left stick on gamepad 2 controls the first part of the arm (extending part)
        if(-gamepad2.left_stick_y < -0.75) {
            motorArmBase = motorArmBase - 0.001;
        }
        else if(-gamepad2.left_stick_y > -0.75 && -gamepad2.left_stick_y < -0.1){
            motorArmBase = motorArmBase - 0.0005;
        }
        else if(-gamepad2.left_stick_y > 0.75){
            motorArmBase = motorArmBase + 0.003;
        }
        else if(-gamepad2.left_stick_y < 0.75 && -gamepad2.left_stick_y > 0.1){
            motorArmBase = motorArmBase + 0.0005;
        }
        //On gamepad 1 x and a control the left hook, and y and b control the right hook:
        if (gamepad1.x == true){
            servoposition2 = servoposition2 - 0.003;
        }
        if (gamepad1.a == true){
            servoposition2 = servoposition2 + 0.003;
        }
        if (gamepad1.y == true){
            servoposition3 = servoposition3 - 0.003;
        }
        if (gamepad1.b == true){
            servoposition3 = servoposition3 + 0.003;
        }
        //D-pad position on gamepad 2 controls the plow:
        if (gamepad2.dpad_up == true){
            servoposition4 = servoposition4 - 0.003;
            servoposition5 = servoposition5 - 0.003;
        }
        else if (gamepad2.dpad_down == true){
            servoposition4 = servoposition4 + 0.003;
            servoposition5 = servoposition5 + 0.003;
        }

        //Setting ranges of servos again because you can never be too safe (´???`):
        servoposition=Range.clip(servoposition, 0, 1);
        servoposition2=Range.clip(servoposition2, 0, 0.45);
        servoposition3=Range.clip(servoposition3, 0, 0.45);
        servoposition4=Range.clip(servoposition4, 0, 1);
        servoposition5=Range.clip(servoposition5, 0, 1);

        //Makes the robot move:
        servoArm.setPosition(servoposition);
        leftHook.setPosition(servoposition2);
        rightHook.setPosition(servoposition3);
        leftPlow.setPosition(servoposition4);
        rightPlow.setPosition(servoposition5);
        ArmBase.setPower(motorArmBase);
        L.setPower(left);
        R.setPower(right);

        //Shows values of servos on driver station, useful during testing
        telemetry.addData("Text", "WALL-E");
        telemetry.addData("ServoArm", "ServoArm: " + servoposition);
        telemetry.addData("leftHook", "leftHook: " + servoposition2);
        telemetry.addData("rightHook", "rightHook: " +servoposition3);
    }
}