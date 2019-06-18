package configurator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.FileWriter;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Configurator {
    
    private static final Configurator instance = new Configurator();
    private String path = "configuration.json";

    private Configurator(){}

    public static Configurator getInstance(){
        return instance;
    }

    public void setFPS(int FPS) throws IOException,ParseException {

        try {
        
            JSONParser jparser = new JSONParser();

            FileReader reader = new FileReader(path);
            
            JSONObject jobj = (JSONObject) jparser.parse(reader);
            

            if (jobj.containsKey("FPS")){
                jobj.put("FPS",FPS);
            }
            
            

            reader.close();

            FileWriter writer = new FileWriter(path);

            writer.write(jobj.toJSONString());

            writer.close();


        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ParseException e){
            e.printStackTrace();
        }

    }

    public int getFPS() throws IOException,ParseException{

        Integer FPS = 0;

        try (FileReader reader = new FileReader(path)){

            JSONParser jparser = new JSONParser();
            JSONObject jobj = (JSONObject) jparser.parse(reader);
            
        

            if (jobj.containsKey("FPS")){
                FPS = ((Long)jobj.get("FPS")).intValue();
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ParseException e){
            e.printStackTrace();
        }

        return FPS;
    }
    
}