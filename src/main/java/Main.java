import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Finanse finanse = new Finanse();
        try (ServerSocket server = new ServerSocket(ServerConfig.PORT)) {
            System.out.println("Сервер запущен!");
            while (true) {
                try (Socket client = server.accept();
                    PrintWriter out = new PrintWriter(client.getOutputStream(),true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
                    System.out.println("Подключен новый клиент");
                    finanse.add(in);
                    out.println("{\"maxCategory\": " + finanse.sum());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
