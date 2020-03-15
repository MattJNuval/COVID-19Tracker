package com.example.coronavirustracker;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.joshworks.restclient.http.HttpResponse;
import io.joshworks.restclient.http.JsonNode;
import io.joshworks.restclient.http.Unirest;

public class Covid19Api extends AsyncTask {

    private static final String CORONA = "CORONA";

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            get("https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_country.php");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void get(String urlString) throws JSONException {
        HttpResponse<JsonNode> responseJson = Unirest.get(urlString)
                .header("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                .header("x-rapidapi-key", "58b22402ddmsha84b0ad50b18fd9p1e9737jsn8594137e4e6c")
                .asJson();
        JSONArray responseArray = responseJson.getBody().getArray();
        JSONObject jsonObject = null;
        for(int i = 0; i < responseArray.length(); i++) {
            jsonObject = (JSONObject) responseArray.get(i);
            JSONArray united_states_stat = (JSONArray) jsonObject.get("countries_stat");
            for(int j = 0; j < united_states_stat.length(); j++) {
                JSONObject jsonObject1 = (JSONObject) united_states_stat.get(j);
                Log.d(CORONA, jsonObject1 +"\n");
            }
        }
    }
}
