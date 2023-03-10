package com.test.evp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;


public class SplitJSON {

    public static Class getOutputClass() {
        Class<JsonData> jsonDataClass = JsonData.class;
        return jsonDataClass;
    }

    public SplitJSON() { }

    public Stream<JsonData> process(InputStream inputStream, String extrusionElement) throws IOException, JSONException {


        StringBuffer sb = new StringBuffer();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream)));
        }
        catch (ZipException ze) {
            br = new BufferedReader(new InputStreamReader(inputStream));
        }
        String l;
        while ((l = br.readLine()) != null) { sb.append(l); }
        br.close();

        // Parse the JSON document
        JSONObject json = new JSONObject(sb.toString());

        // Build the JSON Array
        JSONArray data = json.getJSONArray(extrusionElement);


        // Iterate over the JSONArry, building a new Array which will
        // be emitted as the table.
        List<JsonData> results = new ArrayList<>();
  //      for (Object datum : data) {
  //          JsonData result = new JsonData(datum.toString()); // Note:  This will be the individual rows
  //          results.add(result);
  //      }
        for(int i=0; i<= data.length()-1; i++ ){
            JsonData result = new JsonData(data.get(i).toString()); // Note:  This will be the individual rows
            results.add(result);
        }
        return results.stream();

    }
}