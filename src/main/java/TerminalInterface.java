import McFayyaz.McFayyaz;
import McFayyaz.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.gson.Gson;

public class TerminalInterface {
    public static void main(String[] args) throws IOException {
        McFayyaz mcFayyaz = new McFayyaz();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            try {
                String[] input_parts = parseInput(line);
                if(input_parts.length != 2)
                    throw new Exception("Error: Bad Format");
                String command = input_parts[0];
                String jsonData = input_parts[1];
                runCommand(command, jsonData, mcFayyaz);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static String runCommand(String command, String jsonData, McFayyaz mcFayyaz){
        Gson gson = new Gson();
        Resturant resturant = gson.fromJson(jsonData, Resturant.class);
        resturant.print();
        return "";
    }

    private static String[] parseInput(String input) {
        return input.split(" ", 2);
    }
}
