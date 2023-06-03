package filesReaders;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadFromFiles {

    public synchronized static Properties getAllProperties(String fileName) {
        Properties fileProperties = null;
        try {
            FileReader fileReader = new FileReader( "./src/main/resources/properties/" + fileName) ;
            fileProperties=new Properties();
            fileProperties.load(fileReader);
        }
        catch (IOException ioException)
        {
            System.out.println("There is io exception happened");
        }
        return fileProperties;
    }
    public synchronized  static String getPropertyByKey(String fileName, String propertyKey) {
        Properties fileProperties = getAllProperties (fileName);
        return fileProperties.getProperty(propertyKey);
    }
    public synchronized  static boolean getBooleanProperty(String fileName, String propertyKey) {
        Properties fileProperties = getAllProperties (fileName);
        return Boolean.parseBoolean(fileProperties.getProperty(propertyKey));
    }
    public synchronized  static JSONObject getJsonBody(String fileName)
    {
        JSONParser parser = new JSONParser();
        Object obj = null ;
        try {
            obj = parser.parse(new FileReader("./src/test/resources/testData/" + fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }
    public synchronized  static String getJsonStringValueByKey(String fileName, String jsonKey) {
        JSONObject jsonObject = getJsonBody(fileName);
        String value = (String) jsonObject.get(jsonKey);
        return value;
    }
    public synchronized  static Object getJsonValueByKey(String fileName, String jsonKey) {
        JSONObject jsonObject = getJsonBody(fileName);
        return  jsonObject.get(jsonKey);
    }

    public synchronized  static int getJsonIntegerValueByKey(String fileName, String jsonKey) {
        JSONObject jsonObject = getJsonBody(fileName);
        int value = (int) jsonObject.get(jsonKey);
        return value;
    }
}
