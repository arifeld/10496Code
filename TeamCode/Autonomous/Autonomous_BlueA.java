package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.teamcode.Libraries.LibraryBaseAutonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Harrison on 22/11/2017.
 */

public abstract class Autonomous_BlueA extends LibraryBaseAutonomous{

    public boolean kickDown = false;

    private double ballMiddle;
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



                findBlue();
                findRed();
                ballMiddle = Math.abs((redLocX + blueLocX)/2) ;

                //lower kicker for 1 second
                long start1 = System.currentTimeMillis();
                long end1 = start1 + 100;
                kicker.setPosition(90);
                while (System.currentTimeMillis()<end1){
                    kickDown = false;
                }
                kickDown = true;


                if(kickDown){
                    //if blue is on the left
                    if(blueLocX-ballMiddle<0){
                        long start2 = System.currentTimeMillis();
                        long end2 = start2 + 50;
                        while(System.currentTimeMillis()<end2){
                            dLateral = 1;
                            kicker.setPosition(0);
                            kickDown = false;
                        }
                    }else{
                        long start = System.currentTimeMillis();
                        long end = start + 50;
                        while(System.currentTimeMillis()<end){
                            dLateral = -1;
                            kicker.setPosition(0);
                            kickDown = false;
                        }
                    }
                }
                dLateral = 0;

                //then here realign with VuMark and figure out what the code is.
                long start3 = System.currentTimeMillis();
                long end3;

                //the following needs to be re-written with correct coding for left, right and middle vumark)

                /**
                 * if(Right Vumark){
                 end3=start3+500;
                 //move towards the cryptobox
                 while (System.currentTimeMillis()<end3){
                 dLateral = -1;
                 }
                 dLateral = 0;

                 }else if("Middle Vumark"){
                 end3=start3+600;
                 //move towards the cryptobox
                 while (System.currentTimeMillis()<end3){
                 dLateral = -1;
                 }
                 dLateral = 0;
                 
                 }else if("Left VuMark"){
                 end3=start3+700;
                 //move towards the cryptobox
                 while (System.currentTimeMillis()<end3){
                 dLateral = -1;
                 }
                 dLateral = 0;
                 }
                 */


                long start4 = System.currentTimeMillis();
                long end4 = start4+1000;
                while (System.currentTimeMillis()<end4){
                    conveyor.setPower(0.5);
                }


            }
            addNavTelemetry();
            telemetry.update();
        }

    }
}
