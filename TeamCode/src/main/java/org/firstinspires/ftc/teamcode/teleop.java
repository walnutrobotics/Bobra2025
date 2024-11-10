package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name  = "teleop1", group = "1")
public class teleop extends LinearOpMode {
    DcMotor r1;
    DcMotor r2;
    DcMotor l1;
    DcMotor l2;

    DcMotor lift;

    Servo clawwheel;

    double SpeedMultiplier = 0.50;

    @Override
    public void runOpMode() throws InterruptedException {

        r1 = hardwareMap.get(DcMotor.class,"r1");
        r2 = hardwareMap.get(DcMotor.class,"r2");
        l1 = hardwareMap.get(DcMotor.class,"l1");
        l2 = hardwareMap.get(DcMotor.class,"l2");
        lift = hardwareMap.get(DcMotor.class,"lift");
        clawwheel = hardwareMap.get(Servo.class, "clawwheel");

        waitForStart();
        while(!isStopRequested()) {
            //drive code forward and back
            r1.setPower(gamepad1.left_stick_y);
            r2.setPower(gamepad1.left_stick_y);
            l1.setPower(gamepad1.left_stick_y);
            l2.setPower(gamepad1.left_stick_y);
            //strafe right
            r1.setPower(gamepad1.right_trigger);
            r2.setPower(-gamepad1.right_trigger);
            l1.setPower(-gamepad1.right_trigger);
            l2.setPower(gamepad1.right_trigger);
            //strafe left
            r1.setPower(-gamepad1.left_trigger);
            r2.setPower(gamepad1.left_trigger);
            l1.setPower(gamepad1.left_trigger);
            l2.setPower(-gamepad1.left_trigger);
            //turn
            r1.setPower(gamepad1.left_stick_x);
            r2.setPower(gamepad1.left_stick_x);
            l1.setPower(-gamepad1.left_stick_x);
            l2.setPower(-gamepad1.left_stick_x);

            lift.setDirection(DcMotorSimple.Direction.REVERSE);
            lift.setPower(gamepad2.left_stick_y*SpeedMultiplier);

            //CLAW CODE

            if (gamepad2.a) {
                clawwheel.setPosition(0);
                telemetry.addData("Pressed:a", "a");
            }
            if (gamepad2.b) {
                clawwheel.setPosition(1);
                telemetry.addData("Pressed:","b");
            }
        }
    }
}