package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Mecanum {

    // Motor names
    private String LEFT_FRONT_DRIVE_NAME = "leftFrontDrive";
    private String RIGHT_FRONT_DRIVE_NAME = "rightFrontDrive";
    private String LEFT_REAR_DRIVE_NAME = "leftRearDrive";
    private String RIGHT_REAR_DRIVE_NAME = "rightRearDrive";

    // Motors

    // Variables
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftRearDrive = null;
    private DcMotor rightRearDrive = null;

    private double driveSpeed = 1.0;
    private ElapsedTime timer;

    // Debug
    private boolean debug = true;
    private Telemetry telemetry;

    public Mecanum(HardwareMap hardwareMap, double driveSpeed) {
        this.driveSpeed = driveSpeed;
        initializeMotors(hardwareMap);
    }

    public Mecanum(HardwareMap hardwareMap) {

        // Drive base
        initializeMotors(hardwareMap);
    }

    public void initializeMotors(HardwareMap hardwareMap) {
        timer = new ElapsedTime();

        leftFrontDrive = hardwareMap.dcMotor.get(LEFT_FRONT_DRIVE_NAME);
        rightFrontDrive = hardwareMap.dcMotor.get(RIGHT_FRONT_DRIVE_NAME);
        leftRearDrive = hardwareMap.dcMotor.get(LEFT_REAR_DRIVE_NAME);
        rightRearDrive = hardwareMap.dcMotor.get(RIGHT_REAR_DRIVE_NAME);

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotor.Direction.REVERSE);
        rightRearDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public void setDriveSpeed(double driveSpeed) {
        this.driveSpeed = driveSpeed;
    }

    public void setDebug(boolean toggle) {
        this.debug = toggle;
    }

    public void updateForTime(Vector2 direction, double seconds) {
        this.timer.reset();
        while (this.timer.milliseconds() - this.timer.time() < seconds) {
            update(direction);
        }
    }

    public void update(Gamepad gamepad) {
        this.update(new Vector2(gamepad.left_stick_x, gamepad.left_stick_y));
    }

    public void update(Vector2 direction) {
        direction = direction.normalize();

        double r = Math.hypot(direction.x, direction.y);
        double robotAngle = Math.atan2(-direction.y, direction.x) - Math.PI / 4;
        double rightX = direction.x;

        double leftFrontWheelPower = r * Math.cos(robotAngle) * Math.sqrt(2) + rightX;
        double rightFrontWheelPower = r * Math.sin(robotAngle) * Math.sqrt(2) - rightX;
        double leftRearWheelPower = r * Math.sin(robotAngle) * Math.sqrt(2) + rightX;
        double rightRearWheelPower = r * Math.cos(robotAngle) * Math.sqrt(2) - rightX;

        leftFrontDrive.setPower(leftFrontWheelPower * driveSpeed);
        rightFrontDrive.setPower(rightFrontWheelPower * driveSpeed);
        leftRearDrive.setPower(leftRearWheelPower * driveSpeed);
        rightRearDrive.setPower(rightRearWheelPower * driveSpeed);

        if (this.debug) {
            telemetry.addData("leftFront", "%.2f", leftFrontWheelPower);
            telemetry.addData("rightFront", "%.2f", rightFrontWheelPower);
            telemetry.addData("leftRear", "%.2f", leftRearWheelPower);
            telemetry.addData("rightRear", "%.2f", rightRearWheelPower);
        }
    }
}