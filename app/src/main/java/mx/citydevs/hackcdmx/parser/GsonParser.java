package mx.citydevs.hackcdmx.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import mx.citydevs.hackcdmx.beans.Infraction;
import mx.citydevs.hackcdmx.beans.Officer;
import mx.citydevs.hackcdmx.dialogues.Dialogues;

public class GsonParser {
	private static String TAG_CLASS = GsonParser.class.getName();
	
	public static Officer getFromJSON(String json) throws Exception {
		Gson gson = new Gson();
        Officer publication = gson.fromJson(json, Officer.class);
		return publication;
	}

    public static List<Officer> getOfficerListFromJSON(String json) {
        Gson gson = new Gson();

        Type listType = new TypeToken<List<Officer>>(){}.getType();
        List<Officer> listOfficers = gson.fromJson(json, listType);

        return listOfficers;
    }

    public static List<Infraction> getInfractionsListFromJSON(String json) {
        Gson gson = new Gson();

        Type listType = new TypeToken<List<Infraction>>(){}.getType();
        List<Infraction> listInfractions = gson.fromJson(json, listType);

        return listInfractions;
    }

	public static String createJsonFromObject(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		
		Dialogues.Log(TAG_CLASS, "Json: " + json, Log.INFO);
		
		return json;
	}
	
	public static String createJsonFromObjectWithoutExposeAnnotations(Object object) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(object);
		
		Dialogues.Log(TAG_CLASS, "Json: " + json, Log.INFO);
		
		return json;
	}
}
