package configurator;

import org.json.simple.JSONObject;

public class configurator{

    JSONObject obj = new JSONObject();
    obj.put("name", "mkyong.com");
    obj.put("age", 100);
    System.out.println(obj);

}