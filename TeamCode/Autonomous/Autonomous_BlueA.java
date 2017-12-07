package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Libraries.LibraryBaseAutonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Harrison on 22/11/2017.
 */
@Autonomous(name="Auto_BlueA")

public class Autonomous_BlueA extends LibraryBaseAutonomous{


    ElapsedTime time = new ElapsedTime();


    @Override
    public void runOpMode(){
        initBase();
        initConveyor();
        initGyro();
        initVuforia();

        waitForStart();
        activateTracking();

        while (opModeIsActive()){
            // Add some telemetry.
            telemetry.addData("Current Time:", time.time());


            if(time.time()<9){
                kicker.setPosition(0.55);

                if(colour.red()<75){
                    blueOnLeft = false;
                    telemetry.addData("Blue location:", "Right");
                }else if(colour.red()>200){
                    blueOnLeft = true;
                    telemetry.addData("Blue location:", "Left");
                }else{
                    telemetry.addData("Blue location:", "Unknown");
                }
            }else{
                kicker.setPosition(0.95);
            }


            if(blueOnLeft){
                if(time.time()>5 && time.time()<6){
                    setMoveRobot(0.3,0,0);

                }else if(time.time()>6 && time.time()<7){
                    setMoveRobot(-0.3,0,0);

                }else if(time.time()>7 && time.time()<9){
                    //moveDropKick(0.2);
                    setMoveRobot(0,0,0);
                }
            }else{
                if(time.time()>5 && time.time()<6){
                    setMoveRobot(-0.3,0,0);

                }else if(time.time()>6 && time.time()<7){
                    setMoveRobot(0.3,0,0);

                }else if(time.time()>7 && time.time()<9) {
                    // moveDropKick(0.2);
                    setMoveRobot(0, 0, 0);

                }
            }

            if (getPositionalData()) {
                telemetry.addData("WORK?", vuMark);


                telemetry.update();
                if (vuMark == RelicRecoveryVuMark.RIGHT) {
                    vuMarkInt = 2;


                } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                    vuMarkInt = 1;

                } else if (vuMark == RelicRecoveryVuMark.LEFT) {
                    vuMarkInt = 0;


                }

                if(vuMarkInt == 0){
                    telemetry.addData("Vumark", "left");
                    if (time.time() > 11.5 && time.time() < 13) {
                        setMoveRobot(0.5, 0, 0);
                    } else if (time.time() > 13 && time.time() < 15.5) {
                        setMoveRobot(0, 0, 0);
                    }

                }else if(vuMarkInt ==1 ){
                    telemetry.addData("Vumark", "center");
                    if (time.time() > 11.5 && time.time() < 12.5) {
                        setMoveRobot(0.5, 0, 0);
                    } else if (time.time() > 12.5 && time.time() < 15.5) {
                        setMoveRobot(0, 0, 0);
                    }
                }else if(vuMarkInt ==2){

                    telemetry.addData("Vumark", "right");
                    if (time.time() > 11.5 && time.time() < 12) {
                        setMoveRobot(0.5, 0, 0);
                    } else if (time.time() > 12 && time.time() < 15.5) {
                        setMoveRobot(0, 0, 0);
                    }
                }
            }
            else{
                telemetry.addData("vuMark", "not found.");
            }
            telemetry.update();






            //Turn the robot so the conveyor faces the cryptobox.
            if(time.time()>16 && time.time()<16.5){
                setMoveRobot(0,0,-1);
            }else if(time.time()>16.6 && time.time()<18){
                setMoveRobot(0,0,0);
            }else if(time.time()>18 && time.time()<25){
                moveConveyor(0.5);
            }else if(time.time()>25){
                moveConveyor(0);
            }
            telemetry.update();
        }



        }



}
