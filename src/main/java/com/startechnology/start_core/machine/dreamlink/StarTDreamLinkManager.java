package com.startechnology.start_core.machine.dreamlink;

import java.util.HashSet;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.startechnology.start_core.api.capability.IStarTDreamLinkNetworkRecieveEnergy;

import net.minecraft.core.BlockPos;
import rx.Observable;

public class StarTDreamLinkManager {
    private RTree<IStarTDreamLinkNetworkRecieveEnergy, Geometry> DREAM_LINK_TREE = RTree.create();
    private HashSet<IStarTDreamLinkNetworkRecieveEnergy> INSERTED_SET = new HashSet<>();

    // Singleton for management
    private static final StarTDreamLinkManager MANAGER = new StarTDreamLinkManager();
    
    private StarTDreamLinkManager() {}

    public static void addDevice(IStarTDreamLinkNetworkRecieveEnergy machine) {
        /* Translate position to RTree position */
        BlockPos position = machine.devicePos();

        int x = position.getX();
        int z = position.getZ();

        // Dont insert if this machine is already in there
        if (MANAGER.INSERTED_SET.contains(machine))
            return;

        MANAGER.INSERTED_SET.add(machine);
        MANAGER.DREAM_LINK_TREE = MANAGER.DREAM_LINK_TREE.add(machine, Geometries.point(x, z));
    }

    public static void removeDevice(IStarTDreamLinkNetworkRecieveEnergy machine) {
        /* Translate position to RTree position */
        BlockPos position = machine.devicePos();

        int x = position.getX();
        int z = position.getZ();

        // Delete from the set and tree.
        MANAGER.INSERTED_SET.remove(machine);
        MANAGER.DREAM_LINK_TREE = MANAGER.DREAM_LINK_TREE.delete(machine, Geometries.point(x, z));
    }

    public static Observable<Entry<IStarTDreamLinkNetworkRecieveEnergy, Geometry>> getDevices(int tx, int tz, int bx, int bz) {
        return MANAGER.DREAM_LINK_TREE.search(Geometries.rectangle(bx, bz, tx, tz));
    }
}
