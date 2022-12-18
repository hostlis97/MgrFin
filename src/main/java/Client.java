import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try (Socket client = new Socket(ServerConfig.HOST, ServerConfig.PORT);
             PrintWriter out = new PrintWriter(client.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            String json = "{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}";
            out.println(json);
            System.out.println(in.readLine());
//            System.in.read();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
