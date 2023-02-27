package com.example.javaknowledge.vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehicleSample {

    public static void main(String[] args) {
        System.out.print("vehicle sample !\n");

        Truck v0 = new Truck(300, -23.5, "0123456789", "shangqi", "浙A9878", new Date());

        Truck v1 = new Truck(45, 23.5, "012345678", "shangqi", "浙A1234", new Date());
        Truck v2 = new Truck(55, 23.5, "012345678", "shangqi", "浙A1234", new Date());
        Truck v3 = new Truck(25, 23.5, "012345678", "shangqi", "浙A12346", new Date());
        Truck v4 = new Truck(25, 23.5, "012345678", "shangqi", "浙A12346", new Date());
        Truck v5 = new Truck(25, 23.5, "012345678", "shangqi", "浙A1234789", new Date());


        System.out.println("\n############## insert operation ##############\n");

        List<CheckResult> addChecks = VehicleManager.getInstance().insert(v0);
        if (addChecks != null && addChecks.size() > 0) {
            for (CheckResult checkResult : addChecks) {
                System.out.println(checkResult.getErrHint());
            }
        } else {
            System.out.println("add success");
        }

        System.out.println("\n############## batch insert operation ##############\n");

        List<IVehicle> vehicles = new ArrayList<>();
        vehicles.add(v1);
        vehicles.add(v2);
        vehicles.add(v3);
        vehicles.add(v4);
        vehicles.add(v5);

        List<CheckResult> batchInsertChecks = VehicleManager.getInstance().batchInsert(vehicles);

        if (batchInsertChecks != null && batchInsertChecks.size() > 0) {
            for (CheckResult checkResult : batchInsertChecks) {
                System.out.println(checkResult.getErrHint());
            }
        }
    }
}