import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    public String read(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        String content = "";
        while (true) {
            if (linha != null) {
                content += linha + "\n";
            }else
                break;

            linha = buffRead.readLine();
        }

        buffRead.close();

        return content;
    }

    public static void write(String path) throws IOException {
    }
}

