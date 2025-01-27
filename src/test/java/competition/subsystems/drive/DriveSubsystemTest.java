package competition.subsystems.drive;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import competition.BaseCompetitionTest;

public class DriveSubsystemTest extends BaseCompetitionTest {
    @Test
    public void testTankDrive() {
        DriveSubsystem driveSubsystem = (DriveSubsystem)getInjectorComponent().driveSubsystem();
        driveSubsystem.tankDrive(1, 1);

        assertEquals(1, driveSubsystem.frontLeft.getMotorOutputPercent(), 0.001);
        assertEquals(1, driveSubsystem.frontRight.getMotorOutputPercent(), 0.001);
    }
}
