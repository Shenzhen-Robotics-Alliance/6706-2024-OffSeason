package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase{
    private final DutyCycleEncoder armEncoder = new DutyCycleEncoder(0);
    private final TalonFX armMotor = new TalonFX(10);

    private final ArmFeedforward k_armFeedForward = new ArmFeedforward(0.05, 0.12, 7.73);
    private final PIDController k_armFeedBack = new PIDController(5, 0, 0);
    private final TrapezoidProfile k_armProfile = new TrapezoidProfile(new Constraints(Math.toRadians(120), Math.toRadians(240)));
    private final Rotation2d k_armEncoderOffSet = Rotation2d.fromDegrees(250);
    private final boolean k_armEncoderInverted = true;

    private State currentState = new State(0, 0);
    public Arm() {
        armMotor.setNeutralMode(NeutralModeValue.Coast);
        armMotor.setInverted(true);
    }

    private final XboxController xboxController = new XboxController(1);
    @Override
    public void periodic() {
        if (Math.abs(xboxController.getRightY()) > 0)
            armMotor.set(-xboxController.getRightY());
        SmartDashboard.putNumber("Arm/arm angle (deg)", getArmAngle().getDegrees());
        SmartDashboard.putNumber("Arm/arm motor vel", armMotor.getVelocity().getValueAsDouble());
    }

    public Rotation2d getArmAngle() {
        return Rotation2d.fromRotations(armEncoder.get()).minus(k_armEncoderOffSet).times(k_armEncoderInverted ? -1:1);
    }
}
