package frc.robot.test;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import org.littletonrobotics.junction.Logger;

public class TestBeamBreaker extends Command {
  private final DigitalInput beamBreaker = new DigitalInput(1);

  @Override
  public void execute() {
    Logger.recordOutput("beam breaker triggered", beamBreaker.get());
  }

  @Override
  public void end(boolean interrupted) {
    beamBreaker.close();
  }
}
