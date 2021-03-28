package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Server instance;                                 // Поле хранящее экземпляр класса
    private ServerSocket serverSocket;                              // Поле хранит объект типа ServerSocket
    private Socket clientSocket;                                    // Сокет для обмена сообщениями
    private PrintWriter out;                                        // Поток записи в сокет
    private BufferedReader in;                                      // Поток чтения из сокета

    private Server() { }
    /**
     * Создаёт объект типа Server или
     * возвращает уже имеющийся.
     * @return Объект типа Server
     */
    public static Server getInstance() {
        if(instance == null) {
            instance = new Server();
        }
        return  instance;
    }
    /**
     * Создаёт сервер-сокет и слушает порт 8080.
     * После получения одного воходящего сообщения
     * Закрывает соединение и сервер-сокет
     */
    public void startServer() {
        try {
            try{
                serverSocket = new ServerSocket(8081);
                clientSocket = serverSocket.accept();
                serverDoAnswer();
            } finally {
                System.out.println("Server close!");
                clientSocket.close();
                serverSocket.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    /**
     * Читает сообщение из сокета
     * и выводит его на экран вместе с номером
     * порта, с которого пришло соединение.
     * В ответ отправляет строку с приветствием клиента.
     * @throws IOException
     */
    private void serverDoAnswer() throws IOException{
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            String msg = in.readLine();
            System.out.println("New connection accepted");
            String answer = String.format("Hi %s, your port is %d", msg, clientSocket.getPort());
            System.out.println(answer);
            out.write("Hello!");
            out.flush();
        } finally {
            in.close();
            out.close();
        }
    }
}
