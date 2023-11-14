package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Mechanism Test", group = "Teleop")
public class MechanismTest extends OpMode
{
    //intake declaration
    public CRServo counteroller;
    public DcMotor intakeMotor;

/*
    //deposit servo declaration
    public Servo depositServoOne;
    public Servo depositServoTwo;

    //button logics
    boolean aPressed = false;
    boolean bPressed = false;
    boolean xPressed = false;

    //servo variable declarations
    boolean leftUp = false;
    boolean rightUp = false;

    static final double MAX_POS = 1.0;
    static final double MIN_POS = 0.0;
    double  position = MIN_POS;
*/

    @Override
    public void init() //initialization method
    {
        //intake servo config
        counteroller = hardwareMap.get(CRServo.class, "Intake Servo");
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake Motor");

/*
        //Deposit servo config
        depositServoOne = hardwareMap.get(Servo.class, "Right Deposit");
        depositServoTwo = hardwareMap.get(Servo.class, "Left Deposit");
*/
    }

    @Override
    public void loop() //teleop loop
    {
        //intake
        if (gamepad1.right_trigger > 0.05) {
            counteroller.setPower(1);
            intakeMotor.setPower(-gamepad1.right_trigger);
        } else if (gamepad1.left_trigger > 0.05){
            counteroller.setPower(-1);
            intakeMotor.setPower(gamepad1.left_trigger);
        } else {
            counteroller.setPower(0);
            intakeMotor.setPower(0);
        }
/*
//        deposit
//        a button logic
        if (gamepad1.a && !aPressed)
        {
            rightUp = !rightUp;
            leftUp = !leftUp;
            aPressed = true;
        }
        else if (!gamepad1.a)
            aPressed = false;

        //x button logic
        if (gamepad1.x && !xPressed)
        {
            leftUp = !leftUp;
            xPressed = true;
        }
        else if (!gamepad1.x)
            xPressed = false;

        //b button logic
        if (gamepad1.b && !bPressed)
        {
            rightUp = !rightUp;
            bPressed = true;
        }
        else if (!gamepad1.b)
            bPressed = false;

        //x button drops left
        if (leftUp == true)
            depositServoTwo.setPosition(1);
        else if (leftUp == false)
            depositServoTwo.setPosition(0);

        //b button drops right
        if (rightUp == true)
            depositServoOne.setPosition (1);
        else if (rightUp == false)
            depositServoOne.setPosition (0);

        telemetry.addData("left up", leftUp);
        telemetry.addData("right up", rightUp);
        telemetry.addData("a pressed", aPressed);
        telemetry.addData("b pressed", bPressed);
        telemetry.addData("x pressed", xPressed);
        telemetry.update();
 */
    }
}
