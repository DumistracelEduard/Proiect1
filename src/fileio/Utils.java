package fileio;

import fileio.InputClass.Child;
import fileio.InputClass.ChildUpdate;
import fileio.InputClass.Children;
import fileio.InputClass.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class Utils{

    public static void distributionGift(double budgetUnit, Children children, HashMap<String, ArrayList<Gift>> listGift) {
        for (Child child : children.getChildren()) {
            double buget = child.calculateBudget(budgetUnit);
            for(String giftPreference : child.getGiftsPreferences()) {
                if (listGift.get(giftPreference) != null && buget - listGift.get(giftPreference).get(0).getPrice() > 0.0) {
                    buget -= listGift.get(giftPreference).get(0).getPrice();
                    child.getReceivedGifts().add(listGift.get(giftPreference).get(0));
;               }
            }
        }
    }

    public static HashMap<String, ArrayList<Gift>> convertObjectGift(final List<Object> array) {
        HashMap<String, ArrayList<Gift>> listGift = new HashMap<>();
        for (Object gift : array) {
            Gift giftSearch = (Gift) gift;
            if(listGift.containsKey(giftSearch.getCategory())) {
                listGift.get(giftSearch.getCategory()).add(giftSearch);
                Collections.sort(listGift.get(giftSearch.getCategory()), new Comparator<Gift>() {
                    @Override
                    public int compare(Gift o1, Gift o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                });
            } else {
                ArrayList<Gift> newCategory = new ArrayList<>();
                newCategory.add(giftSearch);
                listGift.put(giftSearch.getCategory(), newCategory);
            }
        }
        return listGift;
    }

    public static ArrayList<Child> convertObject(final List<Object> array) {
        ArrayList<Child> children = new ArrayList<>();
        for(Object object : array) {
            if (((Child) object).getAge() <= 18) {
                children.add((Child) object);
            }
        }
        return children;
    }

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

    public static ArrayList<Gift> convertJSONArrayGift(final JSONArray array) {
        if(array != null) {
            ArrayList<Gift> finalArrayGift = new ArrayList<>();
            for (Object object : array) {
                finalArrayGift.add(new Gift((String) ((JSONObject) object).get(Constants.PRODUCTNAME),
                        Double.parseDouble(((JSONObject) object).get(Constants.PRICE).toString()),
                        (String) ((JSONObject) object).get(Constants.CATEGORY)));
            }
            return finalArrayGift;
        } else {
            return null;
        }
    }

    public static ArrayList<Child> convertJSONArrayChildren(final JSONArray array) {
        if (array != null) {
            ArrayList<Child> newChildrenList = new ArrayList<>();
            for (Object object : array) {
                newChildrenList.add(new Child(Integer.parseInt(((JSONObject) object).get(Constants.ID).toString()),
                        (String) ((JSONObject) object).get(Constants.LASTNAME),
                        (String) ((JSONObject) object).get(Constants.FIRSTNAME),
                        Integer.parseInt(((JSONObject) object).get(Constants.AGE).toString()),
                        (String) ((JSONObject) object).get(Constants.CITY),
                        Utils.convertJSONArrayString((JSONArray) ((JSONObject) object)
                                .get(Constants.GIFTSPREFERENCES)),
                        Double.parseDouble(((JSONObject) object).get(Constants.NICESCORE).toString())

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
