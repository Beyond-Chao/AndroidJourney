package com.example.javaknowledge.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LicensePlateUniqueCheckerImpl implements IUniqueChecker {

    private List<IndexPair> duplicatedIndexes;

    LicensePlateUniqueCheckerImpl() {
        duplicatedIndexes = new ArrayList<IndexPair>();
    }

    @Override
    public CheckResult checkUnique(List<IVehicle> underCheckItems) {
        if (underCheckItems == null || underCheckItems.isEmpty()) {
            return new CheckResult(true, "");
        }

        HashMap<String, Integer> recordMap = new HashMap<String, Integer>();

        for (int i = 0; i < underCheckItems.size(); i++) {
            IIdentifiable checkItem = underCheckItems.get(i);
            String identifier = checkItem.identifier();
            if (recordMap.containsKey(identifier)) {
                IndexPair indexPair = new IndexPair(recordMap.get(identifier), i);
                duplicatedIndexes.add(indexPair);
            } else {
                recordMap.put(checkItem.identifier(), i);
            }
        }

        boolean isValid = (duplicatedIndexes == null || duplicatedIndexes.isEmpty());
        return new CheckResult(isValid, isValid ? "" : message());
    }

    @Override
    public String name() {
        return "唯一性检查";
    }

    @Override
    public String message() {
        String msg = "";

        if (duplicatedIndexes != null && !duplicatedIndexes.isEmpty()) {
            for (IndexPair indexPair : duplicatedIndexes) {
                msg += String.format("(%d, %d)；", indexPair.getIndexLow(), indexPair.getIndexHeight());
            }
        }
        return String.format("检查到有唯一性错误，有如下索引处重复：%s", msg);
    }
}
