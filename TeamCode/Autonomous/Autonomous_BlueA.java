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
        initGyro();
        initVuforia();

        waitForStart();
        activateTracking();

        while (time.time()<10){
            // Add some telemetry.
            telemetry.addData("Current Time:", time.time());




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
            kicker.setPosition(0.55);




            if(blueOnLeft){
                if(time.time()>1 && time.time()<1.25){
                    setMoveRobot(1,0,0);

                }else if(time.time()>1.25 && time.time()<1.5){
                    setMoveRobot(-1,0,0);

                }else if(time.time()>1.5 && time.time()<2.5){
                    //moveDropKick(0.2);
                    setMoveRobot(0,0,0);
                }else if(time.time()>2.5 && time.time()<3){
                    kicker.setPosition(0.95);
                }
            }else{
                if(time.time()>1 && time.time()<1.25){
                    setMoveRobot(-1,0,0);
                    telemetry.addData("Oh hi:", "mark");
                }else if(time.time()>1.25 && time.time()<1.5){
                    setMoveRobot(1,0,0);
                    telemetry.addData("I did not:", "Hit her");
                }else if(time.time()>1.5 && time.time()<2.5){
                    // moveDropKick(0.2);
                    setMoveRobot(0,0,0);
                }else if(time.time()>2.5 && time.time()<3){
                    kicker.setPosition(0.95);
                }
            }



            if(vuMark == RelicRecoveryVuMark.RIGHT){
                if(time.time()>3.5 && time.time()<4.5){
                    setMoveRobot(-1,0,0);
                }else if(time.time()>4.5 && time.time()<5.25){
                    setMoveRobot(0,0,0);
                }

            }else if(vuMark == RelicRecoveryVuMark.CENTER){
                if(time.time()>3.5 && time.time()<4.75){
                    setMoveRobot(-1,0,0);
                }else if(time.time()>4.75 && time.time()<5.25){
                    setMoveRobot(0,0,0);
                }
            }else if(vuMark == RelicRecoveryVuMark.LEFT){
                if(time.time()>3.5 && time.time()<5){
                    setMoveRobot(-1,0,0);
                }else if(time.time()>5 && time.time()<5.25){
                    setMoveRobot(0,0,0);
                }
            }






            //Turn the robot so the conveyor faces the cryptobox.
            if(time.time()>5.5 && time.time()<6){
                setMoveRobot(0,0,1);
            }else if(time.time()>6 && time.time()<6.5){
                setMoveRobot(0,0,0);
            }else if(time.time()>6.75 && time.time()<7.75){
                conveyor.setPower(0.5);
            }else if(time.time()>7.75){
                conveyor.setPower(0);
            }
        }


            telemetry.update();
        }



}
