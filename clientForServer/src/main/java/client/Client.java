package client;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String HOST = "netology.homework";         // Адрес для соединения
    private static final int PORT = 8081;                           // Порт для соединения

    /**
     * Создаём сокет-соединение и отправляем
     * переданное в метод сообщение.
     * Дожидаемся ответа от сервера, печатаем его на экран
     * и закрываем соединение.
     * @param msgToSrv сообщение серверу
     */
    public static void sendToServer(String msgToSrv) {
        try (Socket clientSocket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))){

            out.write(msgToSrv + "\n");
            out.flush();
            String serverWord = in.readLine();
            System.out.println("Server answer: " + serverWord);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
