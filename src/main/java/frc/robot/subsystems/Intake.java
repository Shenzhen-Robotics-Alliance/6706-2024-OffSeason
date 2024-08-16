package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class Intake extends SubsystemBase {
    private final TalonFX lowerIntakeFalcon = new TalonFX(9), upperIntakeFalcon = new TalonFX(11);
    private final DigitalInput beamBreaker = new DigitalInput(2);

    public Intake() {
        // super.setDefaultCommand();
        lowerIntakeFalcon.setNeutralMode(NeutralModeValue.Coast);
        lowerIntakeFalcon.setInverted(true);

        upperIntakeFalcon.setNeutralMode(NeutralModeValue.Coast);
        upperIntakeFalcon.setInverted(true);

        setDefaultCommand(Commands.run(this::runIdle, this));
    }

    public void runIdle() {
        lowerIntakeFalcon.set(0);
        upperIntakeFalcon.set(0);
    }

    public void runIntake() {
        lowerIntakeFalcon.set(0.3);
        upperIntakeFalcon.set(0.3);
    }

    public void runShoot() {
        lowerIntakeFalcon.set(0);
        upperIntakeFalcon.set(0.3);
    }

    public Command runIntakeUntilNotePresent() {
        return Commands.run(this::runIntake, this)
            .onlyIf(() -> !this.hasNote())
            .until(this::hasNote);
    }

    public Command launchNote() {
        return Commands.run(this::runShoot, this)
            .onlyIf(this::hasNote)
            .until(()-> !this.hasNote());
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("BeamBreak value", beamBreaker.get());
    }

    public boolean hasNote() {
        return !beamBreaker.get();
    }
}
