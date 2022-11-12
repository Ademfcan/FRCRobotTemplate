package competition.electrical_contract;

import javax.inject.Inject;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import competition.subsystems.pose.PoseSubsystem;
import xbot.common.injection.electrical_contract.CANTalonInfo;

public class CompetitionContract extends ElectricalContract {

    protected final double simulationScalingValue = 256.0 * PoseSubsystem.INCHES_IN_A_METER;

    @Inject
    public CompetitionContract() {}

    @Override
    public CANTalonInfo getLeftLeader() {
        return new CANTalonInfo(33, false, FeedbackDevice.CTRE_MagEncoder_Absolute, false, simulationScalingValue);
    }

    @Override
    public CANTalonInfo getRightLeader() {
        return new CANTalonInfo(22, true, FeedbackDevice.CTRE_MagEncoder_Absolute, true, simulationScalingValue);
    }


    public CANTalonInfo left2(){

        return new CANTalonInfo(32, false, FeedbackDevice.CTRE_MagEncoder_Absolute, false, simulationScalingValue);

    }

    public CANTalonInfo left3(){
        return new CANTalonInfo(34, false, FeedbackDevice.CTRE_MagEncoder_Absolute, false, simulationScalingValue);
    }

    public CANTalonInfo right2(){
        return new CANTalonInfo(21, true, FeedbackDevice.CTRE_MagEncoder_Absolute, true, simulationScalingValue);

    }

    public CANTalonInfo right3(){
        return new CANTalonInfo(23, true, FeedbackDevice.CTRE_MagEncoder_Absolute, true, simulationScalingValue);

    }





}
