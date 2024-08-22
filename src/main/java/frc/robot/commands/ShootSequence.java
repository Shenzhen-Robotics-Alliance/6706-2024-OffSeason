package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ShootSequence extends SequentialCommandGroup{
    public ShootSequence(Arm arm, Intake intake, Shooter shooter,double armPosition) {
        super.addRequirements(arm, intake, shooter);

        /*
         * The following steps are done in the Autonomous time
         * when the robot are placed in the middle.
         */

        //First step: Shoot the preload note to the speaker
        super.addCommands(Commands.run(() -> {
            arm.runSetPointProfiled(armPosition);               //Set the Arm to aim the Speaker
            shooter.runSpeakerShoot();                          //Start shooting motors
        }, arm, shooter).withTimeout(0.8));

        super.addCommands(Commands.run(intake::runShoot, intake).withTimeout(0.3));     //Shooting the note

        super.addCommands(Commands.run(() -> {
            intake.runIdle();
            arm.runSetPointProfiled(Arm.INTAKE_POSITION_DEG);
            shooter.runIdle();
        }, arm, shooter).withTimeout(0.2));
    }
}
