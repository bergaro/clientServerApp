package client;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String HOST = "netology.homework";         // Адрес для соединения
    private static final int PORT = 8081;                           // Порт для соединения
    private static Socket clientSocket;                             //сокет для общения
    private static BufferedReader reader;                           // ридер читающий с консоли
    private static BufferedReader in;                               // поток чтения из сокета
    private static BufferedWriter out;                              // поток записи в сокет
    /**
     * Создаём сокет-соединение и отправляем
     * переданное в метод сообщение.
     * Дожидаемся ответа от сервера, печатаем его на экран
     * и закрываем соединение.
     * @param msgToSrv сообщение серверу
     */
    public static void sendToServer(String msgToSrv) {
        try {
            try {
                clientSocket = new Socket(HOST, PORT);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                out.write(msgToSrv + "\n");
                out.flush();
                String serverWord = in.readLine();
                System.out.println("Server answer: " + serverWord);
            } finally {
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
