package InterfaceServer;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HTMLHandler {
    public static String fillTemplate(String htmlFileAddress, HashMap<String, String> context) throws Exception {
        File file = new File(htmlFileAddress);
        String htmlFileString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        for(Map.Entry<String, String> entry : context.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            htmlFileString = htmlFileString.replaceAll("%" + key + "%", value);
        }
        return htmlFileString;
    }
}
