package service;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int PORT = 1000;
    private static final int INCREMENT = 100;
    private static final int X = 1366;
    private static final int Y = 768;
    private int x;
    private int y;
    private Robot robot;

    public Main() throws AWTException {
        this.robot = new Robot();
        x = X/2;
        y = Y/2;
    }

    public static void main(String[] args) throws IOException, AWTException {
        Main main = new Main();
        try (
                ServerSocket serverSocket = new ServerSocket(PORT);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                String[] split = inputLine.split(":");
                String command = split[0];

                switch (command) {
                    case "moveMouse":
                        String direction = split[1];
                        main.moveMouse(direction);
                        break;
                }
            }
        }
    }

    private void moveMouse(String direction) {
        switch (direction) {
            case "up":
                y -= INCREMENT;
                break;
            case "down":
                y += INCREMENT;
                break;
            case "left":
                x -= INCREMENT;
                break;
            case "right":
                x += INCREMENT;
                break;
        }

        if (x < 0) x = 0;
        if (x > X) x = X;
        if (y < 0) y = 0;
        if (y > Y) y = Y;
        System.out.println(String.format("x = %d y = %d ", x, y));
        robot.mouseMove(x, y);
    }
}
