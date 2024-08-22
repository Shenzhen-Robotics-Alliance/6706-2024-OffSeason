// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.TunerConstants;

public class Shooter extends SubsystemBase {
  private final TalonFX lowerShooterTalonFX = new TalonFX(12), upperShooterTalonFX = new TalonFX(13);
  /** Creates a new Shooter. */
  public Shooter() {
        lowerShooterTalonFX.setNeutralMode(NeutralModeValue.Coast);
        lowerShooterTalonFX.setInverted(false);

        upperShooterTalonFX.setNeutralMode(NeutralModeValue.Coast);
        upperShooterTalonFX.setInverted(false);
        setDefaultCommand(Commands.run(this::runIdle, this));
  }

  /*
   * Stop the shooting motors
   */
  public void runIdle() {
    lowerShooterTalonFX.set(0);
    upperShooterTalonFX.set(0);
  }

  /*
   * Start two motors to shoote the note to the speaker
   * in a high speed
   */
  public void runSpeakerShoot() {
    lowerShooterTalonFX.set(0.7);
    upperShooterTalonFX.set(0.7);
    // lowerShooterTalonFX.set(0.2);
    // upperShooterTalonFX.set(0.2);
  }

  /*
   * Start two motors to slowly shoote the note to the AMP
   */
  public void runAmpShoot() {
    lowerShooterTalonFX.set(0.3);
    upperShooterTalonFX.set(0.4);
    // lowerShooterTalonFX.set(0.1);
    // upperShooterTalonFX.set(0.1);
  }
}
