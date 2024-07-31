package com.group_30_lab3_2024.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {

    static public List<FeedsData> parseJsonFeedsData(String jsonFilePath) throws IOException {
        String jsonData = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        List<FeedsData> feedsList = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonData);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String label = jsonObject.getString("label");
            String url = jsonObject.getString("url");
            String type = jsonObject.getString("type");
            feedsList.add(new FeedsData(label, url, type));
        }
        return feedsList;
    }

    // Parse our database/json dictionary (dictionary.json)
    static public List<DatabaseData> parseJsonDatabaseData(String jsonFilePath) throws IOException {
        String jsonData = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        List<DatabaseData> databaseList = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonData);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String label = jsonObject.getString("label");
            String url = jsonObject.getString("Category");
            JSONArray topicsJsonArray = jsonObject.getJSONArray("Topics");
            String[] Topics = new String[topicsJsonArray.length()];
            for (int j = 0; j < topicsJsonArray.length(); j++) {
                Topics[j] = topicsJsonArray.getString(j);
            }
            JSONArray keywJsonArray = jsonObject.getJSONArray("keywords");
            String[] keywords = new String[keywJsonArray.length()];
            for (int j = 0; j < keywJsonArray.length(); j++) {
                keywords[j] = keywJsonArray.getString(j);
            }
            databaseList.add(new DatabaseData(label, url, Topics, keywords));
        }
        return databaseList;
    }
}
