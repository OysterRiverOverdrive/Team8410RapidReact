package frc.robot.team8410.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.team8410.subsystems.HangerSubsystem;

public class stopHangCmd extends CommandBase {
    private HangerSubsystem hanger;

    public stopHangCmd(HangerSubsystem hang) {
        hanger = hang;
        addRequirements(hanger);

    }
    @Override
    public void initialize() {}
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}
