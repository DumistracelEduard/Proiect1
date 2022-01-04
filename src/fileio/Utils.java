package fileio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Utils {
    public static ArrayList<String> convertJSONArrayString(final JSONArray array) {
        if(array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for(Object object : array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    public static ArrayList<gift> convertJSONArrayGift(final JSONArray array) {
        if(array != null) {
            ArrayList<gift> finalArrayGift = new ArrayList<>();
            for (Object object : array) {
                finalArrayGift.add(new gift((String) ((JSONObject) object).get(Constants.PRODUCTNAME),
                        Double.parseDouble(((JSONObject) object).get(Constants.PRICE).toString()),
                        (String) ((JSONObject) object).get(Constants.CATEGORY)));
            }
            return finalArrayGift;
        } else {
            return null;
        }
    }

    public static ArrayList<child> convertJSONArrayChildren(final JSONArray array) {
        if (array != null) {
            ArrayList<child> newChildrenList = new ArrayList<>();
            for (Object object : array) {
                newChildrenList.add(new child(Integer.parseInt(((JSONObject) object).get(Constants.ID).toString()),
                        (String) ((JSONObject) object).get(Constants.LASTNAME),
                        (String) ((JSONObject) object).get(Constants.FIRSTNAME),
                        Integer.parseInt(((JSONObject) object).get(Constants.AGE).toString()),
                        (String) ((JSONObject) object).get(Constants.CITY),
                        Double.parseDouble(((JSONObject) object).get(Constants.NICESCORE).toString()),
                        Utils.convertJSONArrayString((JSONArray) ((JSONObject) object)
                                .get(Constants.GIFTSPREFERENCES))
                        ));
            }
            return newChildrenList;
        } else {
            return null;
        }
    }

    public static ArrayList<ChildUpdate> convertJSONArrayChildUpdate(final JSONArray array) {
        if (array != null) {
            ArrayList<ChildUpdate> childrenUpdates = new ArrayList<>();
            for (Object object : array) {
                if (((JSONObject) object).get(Constants.NICESCORE) != null) {
                    childrenUpdates.add(new ChildUpdate(Integer.parseInt(((JSONObject) object).get(Constants.ID).toString()),
                            Double.parseDouble(((JSONObject) object).get(Constants.NICESCORE).toString()),
                            Utils.convertJSONArrayString((JSONArray) ((JSONObject) object)
                                    .get(Constants.GIFTSPREFERENCES))));
                } else {
                    childrenUpdates.add(new ChildUpdate(Integer.parseInt(((JSONObject) object).get(Constants.ID).toString()),
                            -1,
                            Utils.convertJSONArrayString((JSONArray) ((JSONObject) object)
                                    .get(Constants.GIFTSPREFERENCES))));
                }
            }
            return childrenUpdates;
        } else {
            return null;
        }
    }
}
