package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Libraries.LibraryBaseAutonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Harrison on 22/11/2017.
 */

public abstract class Autonomous_BlueA extends LibraryBaseAutonomous{

    private boolean jewelsNow = true;
    private boolean cryptoMove = false;

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
                gyroYaw  = angles.firstAngle;

                // Add some telemetry.
                telemetry.addData("Axial: ", dAxial);
                telemetry.addData("Lateral: ", dLateral);
                telemetry.addData("Yaw: ", dYaw);
                telemetry.addData("Gyro Yaw: ", gyroYaw);
                telemetry.addData("Mode:", topRight.getMode());
                telemetry.addData("Red Particle Location: ",redLocX + "," + redLocY );
                telemetry.addData("Blue Particle Location: ",blueLocX + "," + blueLocY );
                telemetry.update();
                setMoveRobot(dAxial, dLateral, dYaw);



                if(colour.red()<75){
                    blueOnLeft = false;
                    telemetry.addData("Blue location:", "Right");
                }else if(colour.red()>200){
                    blueOnLeft = true;
                    telemetry.addData("Blue location:", "Left");
                }else{
                    telemetry.addData("Blue location:", "Unknown");
                }
                telemetry.update();
                //move the kicker from vertical to horizontal
                kicker.setPosition(0.4); //NEEDS TO BE CHANGED TO A VIABLE POSITION
                sleep(200);//sleep for 2 seconds



                while(jewelsNow){
                    //if blue is on the left
                    if(blueOnLeft){
                        setMoveRobot(1,0,0);
                        sleep(50);
                        setMoveRobot(-1,0,0);
                        sleep(50);
                        moveIntake(0.1);
                        sleep(100);
                        kicker.setPosition(0.1);
                        jewelsNow = false;
                        cryptoMove = true;
                    }else{
                        setMoveRobot(-1,0,0);
                        sleep(50);
                        setMoveRobot(1,0,0);
                        sleep(50);
                        moveIntake(0.1);
                        sleep(100);
                        kicker.setPosition(0.1);
                        jewelsNow = false;
                        cryptoMove = true;

                    }
                }
                setMoveRobot(0,0,0);
                sleep(50);//this is put here as a half second buffer to ensure the robot is not set to 0 while trying to move due to the VuMark function.


                //then here realign with VuMark and figure out what the code is.
                //the following needs to be re-written with correct coding for left, right and middle vumark)


                while (cryptoMove){

                    if(vuMark == RelicRecoveryVuMark.RIGHT){
                        setMoveRobot(-1,0,0);
                        sleep(500); //move towards cryptobox for X seconds
                        cryptoMove = false;

                    }else if(vuMark == RelicRecoveryVuMark.CENTER){
                        setMoveRobot(-1,0,0);
                        sleep(550); //move towards cryptobox for X seconds
                        cryptoMove = false;

                    }else if(vuMark == RelicRecoveryVuMark.LEFT){
                        setMoveRobot(-1,0,0);
                        sleep(600); //move towards cryptobox for X seconds
                        cryptoMove = false;
                    }

                }
                setMoveRobot(0,0,0);
                sleep(50);//half second buffer.




                //Turn the robot so the conveyor faces the cryptobox.
                setMoveRobot(0,0,1);
                telemetry.update();
                sleep(100);
                setMoveRobot(0,0,0);
                telemetry.update();


                //Turn the conveyor
                conveyor.setPower(0.5);
                telemetry.update();
                sleep(1000);
                conveyor.setPower(0);
                telemetry.update();





            }
            addNavTelemetry();
            telemetry.update();
        }

    }

}
