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
    DcMotor belt;
    DcMotor lift;

    Servo flipper;

    double SpeedMultiplier = 0.75;

    @Override
    public void runOpMode() throws InterruptedException {

        r1 = hardwareMap.get(DcMotor.class,"r1");
        r2 = hardwareMap.get(DcMotor.class,"r2");
        l1 = hardwareMap.get(DcMotor.class,"l1");
        l2 = hardwareMap.get(DcMotor.class,"l2");
        lift = hardwareMap.get(DcMotor.class,"lift");
        flipper = hardwareMap.get(Servo.class, "clawwheel");
        belt = hardwareMap.get(DcMotor.class, "belt");

        waitForStart();
        while(!isStopRequested()) {
            //drive code forward and back
            r1.setDirection(DcMotorSimple.Direction.REVERSE);
            r2.setDirection(DcMotorSimple.Direction.REVERSE);
            if (gamepad1.dpad_down) {
                r1.setPower(1);
                r2.setPower(1);
                l1.setPower(0.75);
                l2.setPower(0.75);
            } else if (!gamepad1.dpad_down) {
                r1.setPower(0);
                r2.setPower(0);
                l1.setPower(0);
                l2.setPower(0);
            }

            //right turn
            if (gamepad1.dpad_up) {
                r1.setPower(-1);
                r2.setPower(-1);
                l1.setPower(-0.75);
                l2.setPower(-0.75);
                }
            else if (!gamepad1.dpad_up) {
                r1.setPower(0);
                r2.setPower(0);
                l1.setPower(0);
                l2.setPower(0);
            }
            //left turn
            if (gamepad1.dpad_left) {
                r1.setPower(-1);
                r2.setPower(-1);
                l1.setPower(1);
                l2.setPower(1);
            } else if (!gamepad1.dpad_left) {
                r1.setPower(0);
                r2.setPower(0);
                l1.setPower(0);
                l2.setPower(0);
            }

            if (gamepad1.dpad_right) {
                r1.setPower(1);
                r2.setPower(1);
                l1.setPower(-1);
                l2.setPower(-1);
            } else if (!gamepad1.dpad_right) {
                r1.setPower(0);
                r2.setPower(0);
                l1.setPower(0);
                l2.setPower(0);
            }

            //Turn Right
            if (gamepad1.right_trigger > 0.5) {
                r1.setPower(-1);
                r2.setPower(-1);
                l1.setPower(1);
                l2.setPower(1);
            }

            //Turn Left
            if (gamepad1.left_trigger > 0.5) {
                l1.setPower(-1);
                l2.setPower(-1);
                r1.setPower(1);
                r2.setPower(1);

            }

            belt.setPower(gamepad2.right_stick_y*0.5);
            if (belt.getPower() > 0) {
                telemetry.addData("Pressed:right stick", "y");
            }

            lift.setPower(gamepad1.right_stick_y*0.5);

            //CLAW CODE

            if (gamepad2.a) {
                flipper.setPosition(0);
                telemetry.addData("Pressed:", "a");
            }
            if (gamepad2.b) {
                flipper.setPosition(1);
                telemetry.addData("Pressed:","b");
            }
            telemetry.update();
        }
    }
}