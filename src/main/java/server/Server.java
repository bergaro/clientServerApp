package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Server instance;                                 // Поле хранящее экземпляр класса
    private Socket clientSocket;                                    // Сокет для обмена сообщениями
    private boolean workStatus = true;                              // Отвечает за работу сервера
    private String userName;                                        // Поле хранит имя клиента

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
     * Создаёт сервер-сокет и слушает порт 8081 пока
     * workStatus = true.
     */
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(8081)){
            while (workStatus) {
                clientSocket = serverSocket.accept();
                serverDoAnswer();
            }
            System.out.println("Server close!");
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Читает сообщение из сокета и при помощи getAnswer()
     * формирует ответ.
     * Если от клиента поступит команда - 'stop', изменяет
     * workStatus на false.
     * @throws IOException
     */
    private void serverDoAnswer(){
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String msg = in.readLine();
            if(msg.equals("stop")) {
                workStatus = false;
            } else {
                System.out.println("New connection accepted");
                out.write(getAnswer(msg));
                out.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * 3 варианта возможного ответа от сервера.
     * @param msg строка сообщения
     * @return один из 3-ёх возможных ответов, в зависимости от
     * содержимого строки.
     */
    private String getAnswer(String msg) {
        if(msg.equals("yes")) {
            return String.format("Welcome to the kids area, %s! Let's play!", userName);
        } else if(msg.equals("no")) {
            return String.format("Welcome to the adult zone, %s!", userName);
        } else {
            userName = msg;
            return String.format("Hi %s! " +
                                "Are you child?(yes/no)", userName);
        }
    }
}
