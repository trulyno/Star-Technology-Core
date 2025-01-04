package com.startechnology.start_core.machine;
import com.startechnology.start_core.machine.bacteria.StarTBacteriaMachines;
import com.startechnology.start_core.machine.hpca.StarTHPCAParts;

public class StarTMachines {

    public static void init() {
        StarTHPCAParts.init();
        StarTBacteriaMachines.init();
    }
}
