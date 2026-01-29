package edu.upc.prop.grup13_4.inventari;

import com.google.gson.*;
import edu.upc.prop.grup13_4.inventari.Producte;

import java.lang.reflect.Type;

//Needed by GSON in order to serialize Producte.
public class ProducteAdapter implements JsonSerializer<Producte>, JsonDeserializer<Producte> {

    @Override
    public JsonElement serialize(Producte src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getId());
    }

    @Override
    public Producte deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Producte(json.getAsString());
    }
}