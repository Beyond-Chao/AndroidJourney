package com.example.javaknowledge.vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleManager {

    private static VehicleManager instance = new VehicleManager();
    private VehicleManager() {
        inflateAllChecks();
    }

    public static VehicleManager getInstance() {
        return instance;
    }

    List<IRangeChecker> rangeCheckers = new ArrayList<IRangeChecker>();
    List<ILengthChecker> lengthCheckers = new ArrayList<ILengthChecker>();
    List<IUniqueChecker> uniqueCheckers = new ArrayList<IUniqueChecker>();

    private void inflateAllChecks() {
        /// range checker
        OilRangeCheckerImpl oilChecker = new OilRangeCheckerImpl(0.1, 60);
        TemperatureRangeCheckerImpl temperatureChecker = new TemperatureRangeCheckerImpl(-5, 55);

        rangeCheckers.add(oilChecker);
        rangeCheckers.add(temperatureChecker);

        /// length checker
        PINCodeLengthCheckerImpl pinCodeChecker = new PINCodeLengthCheckerImpl(9);
        lengthCheckers.add(pinCodeChecker);

        /// unique checker
        LicensePlateUniqueCheckerImpl licensePlateUniqueChecker = new LicensePlateUniqueCheckerImpl();
        uniqueCheckers.add(licensePlateUniqueChecker);
    }

    public List<CheckResult> insert(IVehicle truck) {
        List<CheckResult> checkResult = check(truck, false);

        if (checkResult != null && checkResult.isEmpty()) {
            // do some persist operation(save to database)
        }

        /// is valid
        // do some persist operation(save to database)

        return checkResult;
    }

    public List<CheckResult> batchInsert(List<IVehicle> truckList) {

        List<CheckResult> invalidResults = new ArrayList<>();

        for (IVehicle truck : truckList) {
            invalidResults = check(truck, false);
            if (invalidResults != null && !invalidResults.isEmpty()) {
                break;
            }
        }

        /// do unique checker with batch self
        for (IUniqueChecker uniqueChecker : this.uniqueCheckers) {
            CheckResult checkResult = uniqueChecker.checkUnique(truckList);

            if (!checkResult.isValid()) {
                invalidResults.add(checkResult);
                break;
            }
        }

        /// do unique checker with database, with performance concern
        ///

        if (invalidResults != null && invalidResults.isEmpty()) {
            // do some persist operation(save to database)
        }

        return invalidResults;
    }

    private List<CheckResult> check(IVehicle truck, boolean invalidReturnInstantly) {
        List<CheckResult> invalidResults = new ArrayList<>();

        // 1. do range checker firstly
        for (IRangeChecker rangeChecker : this.rangeCheckers) {
            CheckResult checkResult = rangeChecker.checkRange(truck);
            if (!checkResult.isValid()) {
                invalidResults.add(checkResult);
            }

            if (!checkResult.isValid() && invalidReturnInstantly) {
                return invalidResults;
            }
        }

        // 2. do length checker secondly
        for (ILengthChecker lengthChecker : this.lengthCheckers) {
            CheckResult checkResult = lengthChecker.checkLength(truck);
            if (!checkResult.isValid()) {
                invalidResults.add(checkResult);
            }

            if (!checkResult.isValid() && invalidReturnInstantly) {
                return invalidResults;
            }
        }

        return invalidResults;
    }
}
