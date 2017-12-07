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

    private double intakePower = 0.3;






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


        //Translations
        updateGyro();
            dAxial   = -gamepad1.left_stick_y;
            dLateral =  gamepad1.left_stick_x;
            dYaw     = -gamepad1.right_stick_x;
        setMoveRobot(dAxial, dLateral, dYaw);

        gyroYaw  = angles.firstAngle;

        // Add some telemetry.
        telemetry.addData("Axial: ", dAxial);
        telemetry.addData("Lateral: ", dLateral);
        telemetry.addData("Yaw: ", dYaw);
        telemetry.addData("Gyro Yaw: ", gyroYaw);


        //Turn the conveyor
        moveConveyor(-gamepad2.right_stick_y/5);

        //Drop intake
        if(gamepad2.right_bumper){
            moveDropKick(-0.4);
        }else if(gamepad2.left_bumper){
            moveDropKick(0.4);
        }else{
            moveDropKick(0);
        }
        //Turn intake
        if (gamepad2.left_trigger > 0.2){ // May need to change this.
            moveIntake(-intakePower);
        }
        else if (gamepad2.right_trigger > 0.2){
            moveIntake(intakePower);
        }else{
            moveIntake(0);
        }






        telemetry.update();
    }

    @Override
    public void stop(){


    }

}
