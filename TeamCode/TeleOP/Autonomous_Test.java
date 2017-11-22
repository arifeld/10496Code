package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Libraries.LibraryBaseAutonomous;

/**
 * Created by Ari on 15-11-17.
 */

public class Autonomous_Test extends LibraryBaseAutonomous {

    private double gyroYaw    = 0;
    private double dAxial     = 0;
    private double dLateral   = 0;
    private double dYaw       = 0;

    @Override
    public void runOpMode(){
        initBase();
        initGyro();
        initVuforia();

        waitForStart();
        activateTracking();
        while (opModeIsActive()){
            if (gamepad1.right_bumper){
                if ( getPositionalData() ){
                    cruiseControl(10);
                }
            }
            else{
                dAxial   = -gamepad1.left_stick_y;
                dLateral =  gamepad1.left_stick_x;
                dYaw     = -gamepad1.right_stick_x;
                gyroYaw  = angles.firstAngle;

                // Add some telemetry.
                telemetry.addData("Axial: ", dAxial);
                telemetry.addData("Lateral: ", dLateral);
                telemetry.addData("Yaw: ", dYaw);
                telemetry.addData("Gyro Yaw: ", gyroYaw);
                telemetry.addData("Mode:", topRight.getMode());
                telemetry.addData("Red Particle Location: ",redLocX + "," + redLocY );
                telemetry.addData("Blue Particle Location: ",blueLocX + "," + blueLocY );

                setMoveRobot(dAxial, dLateral, dYaw);


            }
            addNavTelemetry();
            telemetry.update();
        }

    }


}
