package competition.subsystems.drive;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

import competition.electrical_contract.ElectricalContract;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.controls.actuators.XCANTalon.XCANTalonFactory;
import xbot.common.math.PIDManager;
import xbot.common.math.XYPair;
import xbot.common.math.PIDManager.PIDManagerFactory;
import xbot.common.properties.XPropertyManager;
import xbot.common.subsystems.drive.BaseDriveSubsystem;

@Singleton
public class DriveSubsystem extends BaseDriveSubsystem {
    private static Logger log = Logger.getLogger(DriveSubsystem.class);
    
    ElectricalContract contract;
    
    public final XCANTalon leftLeader;
    public final XCANTalon rightLeader;

    public final XCANTalon left2;
    public final XCANTalon left3;

    public final XCANTalon right2;
    public final XCANTalon right3;


    private final PIDManager positionPid;
    private final PIDManager rotationPid;

    private double scalingFactorFromTicksToInches = 1.0 / 256.0;

    @Inject
    public DriveSubsystem(XCANTalonFactory talonFactory, XPropertyManager propManager, ElectricalContract contract, PIDManagerFactory pf) {
        log.info("Creating DriveSubsystem");

        this.leftLeader = talonFactory.create(contract.getLeftLeader());
        this.rightLeader = talonFactory.create(contract.getRightLeader());
        
        this.left2 = talonFactory.create(contract.left2());
        this.left3 = talonFactory.create(contract.left3());
        
        this.right2 = talonFactory.create(contract.right2());
        this.right3 = talonFactory.create(contract.right3());

        leftLeader.configureAsFollowerMotor(leftLeader, false);
        rightLeader.configureAsFollowerMotor(rightLeader, false);

        left2.configureAsFollowerMotor(leftLeader, true);
        left3.configureAsFollowerMotor(leftLeader, true);

        right2.configureAsFollowerMotor(rightLeader, true);
        right3.configureAsFollowerMotor(rightLeader, true);


        positionPid = pf.create(getPrefix() + "PositionPID");
        rotationPid = pf.create(getPrefix() + "RotationPID");

        leftLeader.createTelemetryProperties(this.getPrefix(), "frontLeft");
        rightLeader.createTelemetryProperties(this.getPrefix(), "frontRight");

        this.register();
    }

    public void tankDrive(double leftPower, double rightPower) {
        this.leftLeader.simpleSet(leftPower);
        this.rightLeader.simpleSet(rightPower);
    }

    @Override
    public PIDManager getPositionalPid() {
        return positionPid;
    }

    @Override
    public PIDManager getRotateToHeadingPid() {
        return rotationPid;
    }

    @Override
    public PIDManager getRotateDecayPid() {
        return null;
    }

    @Override
    public void move(XYPair translate, double rotate) {
        double y = translate.y;

        double left = y - rotate;
        double right = y + rotate;

        this.leftLeader.simpleSet(left);
        this.rightLeader.simpleSet(right);
    }

    @Override
    public double getLeftTotalDistance() {
        return leftLeader.getSelectedSensorPosition(0) * scalingFactorFromTicksToInches;
    }

    @Override
    public double getRightTotalDistance() {
        return rightLeader.getSelectedSensorPosition(0) * scalingFactorFromTicksToInches;
    }

    @Override
    public double getTransverseDistance() {
        return 0;
    }
}
