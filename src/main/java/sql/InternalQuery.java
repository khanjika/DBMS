package sql;

import java.util.HashMap;
import java.util.Map;

public class InternalQuery {
    private Map<String,Object> objectMap = new HashMap<>();

    public Object get(String key){
        return objectMap.get(key);
    }

    public void set(String key, Object value){
        if(value == null){
            value = "";
        }
        objectMap.put(key, value);
    }
}
