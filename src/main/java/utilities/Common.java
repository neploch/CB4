package utilities;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Common {
    //Imported from repository data container
    static Map<String,String> repValues;
    //Repository data file
    static String commonXML = "common.xml";

    //Read data from repository file
    public static void setRepValues(){
        repValues = new HashMap<>();
        repValues.putAll(readXML(commonXML));
    }

    //Get repository value
    public static String getRepValue(String key){
        return repValues.get(key);
    }

    public static Map<String,String> readXML(String fileName){
        try {
            String rootPath = Paths.get(".").toAbsolutePath().normalize().toString();
            String path = rootPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "repository" + File.separator + fileName;
            XStream xStream = new XStream(new DomDriver());
            xStream.registerConverter(new MapEntryConverter());
            xStream.alias("general", Map.class);
            Map <String,String> map = (Map<String, String>) xStream.fromXML(new FileInputStream(path));
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static class MapEntryConverter implements Converter {

        public boolean canConvert(Class clazz) {
            return AbstractMap.class.isAssignableFrom(clazz);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

            AbstractMap map = (AbstractMap) value;
            for (Object obj : map.entrySet()) {
                Map.Entry entry = (Map.Entry) obj;
                writer.startNode(entry.getKey().toString());
                Object val = entry.getValue();
                if ( null != val ) {
                    writer.setValue(val.toString());
                }
                writer.endNode();
            }

        }

        public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

            Map<String, String> map = new HashMap<String, String>();

            while(reader.hasMoreChildren()) {
                reader.moveDown();

                String key = reader.getNodeName(); // nodeName aka element's name
                String value = reader.getValue();
                map.put(key, value);

                reader.moveUp();
            }

            return map;
        }

    }

}
