package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Libraries.LibraryBaseTeleOP;

/**
 * Created by Ari on 02-11-17.
 */


@TeleOp(name = "TeleOP_Full", group = "Comp")
public class TeleOP_Full extends LibraryBaseTeleOP {

    private double gyroYaw    = 0;
    private double dAxial     = 0;
    private double dLateral   = 0;
    private double dYaw       = 0;

    private double intakePower = 0.5;
    private double raiseRelicPower = 0.4;
    private double lowerRelicPower = -0.3;
    private double extendRelicPower = 0.4;
    private double withdrawRelicPower = -0.3;

    private boolean stepping = false;



    @Override
    public void init(){
        initBase(); // From library, inits, sets direction, and resets all base motor.
        initConveyor();
        initGyro();
        telemetry.addData("STATUS: ", "Initialised.");
        telemetry.update();
    }

    @Override
    public void init_loop(){

    }

    @Override
    public void start(){

    }

    @Override
    public void loop(){

        updateGyro();
        if(stepping = false){
            dAxial   = -gamepad1.left_stick_y;
            dLateral =  gamepad1.left_stick_x;
            dYaw     = -gamepad1.right_stick_x;
        }

        gyroYaw  = angles.firstAngle;

        // Add some telemetry.
        telemetry.addData("Axial: ", dAxial);
        telemetry.addData("Lateral: ", dLateral);
        telemetry.addData("Yaw: ", dYaw);
        telemetry.addData("Gyro Yaw: ", gyroYaw);

        setMoveRobot(dAxial, dLateral, dYaw);
        moveConveyor(-gamepad2.right_stick_y);

        if (gamepad2.right_trigger > 0.2){ // May need to change this.
            moveIntake(intakePower);
        }
        else if (gamepad2.left_trigger > 0.2){
            moveIntake(-intakePower);
        }

        if (gamepad2.right_bumper){
            grabRelic(true);
        }
        else if (gamepad2.left_bumper){
            grabRelic(false);
        }

        if (gamepad2.dpad_up){
            raiseRelicArm(raiseRelicPower);
        }
        else if (gamepad2.dpad_down){
            raiseRelicArm(lowerRelicPower);
        }

        if (gamepad2.dpad_right){
            moveRelicArm(extendRelicPower);
        }
        else if (gamepad2.dpad_left){
            moveRelicArm(withdrawRelicPower);
        }

        /**
         *Stepping with D-Pad
         * This will drive in a direction for X milliseconds
         */

        if (gamepad1.dpad_up){
            stepping = true;
            long start = System.currentTimeMillis();
            long end = start+50;
            while(System.currentTimeMillis()<end){
                dAxial = 1;
            }
        }
        else if (gamepad1.dpad_down){
            stepping = true;
            long start = System.currentTimeMillis();
            long end = start+50;
            while(System.currentTimeMillis()<end){
                dAxial = -1;
            }
        }
        else if (gamepad1.dpad_right){
            stepping = true;
            long start = System.currentTimeMillis();
            long end = start+50;
            while(System.currentTimeMillis()<end){
                dLateral = 1;
            }
        }
        else if (gamepad1.dpad_up){
            stepping = true;
            long start = System.currentTimeMillis();
            long end = start+50;
            while(System.currentTimeMillis()<end){
                dLateral = -1;
            }
        }else{
            stepping = false;
        }
        telemetry.update();
    }

    @Override
    public void stop(){


    }

}
