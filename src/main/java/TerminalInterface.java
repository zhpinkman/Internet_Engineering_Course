import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalInterface {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    private String parseInput(String input) {
        String[] input_parts = input.split(" ", 2);
        String command = input_parts[0];
        String jsonData = input_parts[1];

        return "";
    }

}
