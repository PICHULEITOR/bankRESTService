package pe.com.jorgeberrios.util;

//import java.util.Collections;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ConvertToUtils {
	private ConvertToUtils() {}

    private static final Gson GSON =new GsonBuilder().disableHtmlEscaping().create();


    public static String convertFromObjectToJson(Object object) {
        return GSON.toJson(object);
    }
    public static <T> T convertFromJsonToObject(String json,Class<T> c) {
    	return GSON.fromJson(json,c);
    }
/*    public static String convertFromObjectToJsonResponse( Object object, String name) throws JsonProcessingException  {
        ObjectMapper mapper = new ObjectMapper();
       
        mapper.setPropertyNamingStrategy(new ReplaceNamingStrategy(Collections.singletonMap("object", name)));
        
        return mapper.writeValueAsString(object);
	
    }
*/
}
