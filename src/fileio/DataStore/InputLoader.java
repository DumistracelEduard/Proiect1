package fileio.DataStore;

import fileio.Constants;
import fileio.InputClass.AnnualChanges;
import fileio.InputClass.Child;
import fileio.InputClass.Gift;
import fileio.Utils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class InputLoader {
    private final String inputPath;

    public InputLoader(String inputPath) {
        this.inputPath = inputPath;
    }

    public InputData readData() {
        JSONParser jsonParser = new JSONParser();
        Integer numberOfYears = 0;
        double santaBudget = 0;
        List<Child> childList = new ArrayList<>();
        List<Gift> giftList = new ArrayList<>();
        List<String> cityList = new ArrayList<>();
        List<AnnualChanges> annualChanges = new ArrayList<>();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));
            numberOfYears = Integer.parseInt(jsonObject.get(Constants.NUMBEROFYEARS).toString());
            santaBudget = Double.parseDouble(jsonObject.get(Constants.SANTABUDGET).toString());
            JSONObject jsoninitialData = (JSONObject) jsonObject.get(Constants.INITIALDATA);
            JSONArray jsonChangesAnnual = (JSONArray) jsonObject.get(Constants.ANNUALCHANGES);
            if (jsoninitialData != null) {
                JSONArray jsonChildren = (JSONArray) jsoninitialData.get(Constants.CHILDREN);
                if(jsonChildren != null) {
                    for(Object jsonChild : jsonChildren) {
                        childList.add(new Child(Integer.parseInt(((JSONObject) jsonChild).get(Constants.ID).toString()),
                                (((String)((JSONObject) jsonChild).get(Constants.LASTNAME))),
                                (((String)((JSONObject) jsonChild).get(Constants.FIRSTNAME))),
                                Integer.parseInt(((JSONObject) jsonChild).get(Constants.AGE).toString()),
                                (((String)((JSONObject) jsonChild).get(Constants.CITY))),Utils.convertJSONArrayString((JSONArray) ((JSONObject) jsonChild)
                                .get(Constants.GIFTSPREFERENCES)),
                                Double.parseDouble(((JSONObject) jsonChild).get(Constants.NICESCORE).toString())
                                ));
                    }
                }
                JSONArray jsonGifts = (JSONArray) jsoninitialData.get(Constants.SANTAGIFTSLIST);
                if(jsonGifts != null) {
                    for(Object jsonGift : jsonGifts) {
                        giftList.add(new Gift((String) ((JSONObject) jsonGift).get(Constants.PRODUCTNAME),
                                Double.parseDouble(((JSONObject) jsonGift).get(Constants.PRICE).toString()),
                                (String) ((JSONObject) jsonGift).get(Constants.CATEGORY)
                                ));
                    }
                }
            } else {
                System.out.println("Nu exista date initiale");
            }
            if (jsonChangesAnnual != null) {
                for(Object jsonChange : jsonChangesAnnual) {
                    annualChanges.add(new AnnualChanges(Double.parseDouble(((JSONObject) jsonChange).get(Constants.NEWSANTABUDGET).toString()),
                            Utils.convertJSONArrayGift((JSONArray) ((JSONObject) jsonChange).get(Constants.NEWGIFTS)),
                            Utils.convertJSONArrayChildren((JSONArray) ((JSONObject) jsonChange).get(Constants.NEWCHILDREN)),
                            Utils.convertJSONArrayChildUpdate((JSONArray) ((JSONObject) jsonChange).get(Constants.CHILDRENUPDATES))
                            ));
                }
            } else {
                System.out.println("Nu exista schimbari anuale");
            }
        } catch (ParseException | IOException e){
            e.printStackTrace();
        }

        return new InputData(numberOfYears, santaBudget, childList, giftList, cityList, annualChanges);
    }
}
