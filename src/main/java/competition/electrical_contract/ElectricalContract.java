package competition.electrical_contract;

import javax.swing.plaf.nimbus.AbstractRegionPainter;

import xbot.common.injection.electrical_contract.CANTalonInfo;

public abstract class ElectricalContract {
    public abstract CANTalonInfo getLeftLeader();
    public abstract CANTalonInfo getRightLeader();

    public abstract CANTalonInfo left2();
    public abstract CANTalonInfo left3();

    public abstract CANTalonInfo right2();
    public abstract CANTalonInfo right3();


    

}
