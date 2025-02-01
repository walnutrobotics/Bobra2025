package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous", group = "1")
public class Auto extends LinearOpMode {
    public DcMotor r1 = null;
    public DcMotor r2 = null;
    public DcMotor l1 = null;
    public DcMotor l2 = null;
    public DcMotor belt = null;
    public DcMotor lift = null;
    public Servo flipper = null;


    @Override
    public void runOpMode() throws InterruptedException {
        r1 = hardwareMap.get(DcMotor.class, "r1");
        r2 = hardwareMap.get(DcMotor.class, "r2");
        l1 = hardwareMap.get(DcMotor.class, "l1");
        l2 = hardwareMap.get(DcMotor.class, "l2");
        lift = hardwareMap.get(DcMotor.class, "lift");
        flipper = hardwareMap.get(Servo.class, "clawwheel");
        belt = hardwareMap.get(DcMotor.class, "belt");

        waitForStart();

        if (opModeIsActive()) {
            moveleft(0.5,7000);

        }
    }
    public void moveleft(double power, long time) {
        r1.setPower(-power);
        r2.setPower(power);
        l1.setPower(power+0.15);
        l2.setPower(-power-0.15);

        sleep(time);
    }

}
