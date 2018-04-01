package service;

import java.awt.*;
import java.awt.event.InputEvent;
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
                    case "mouse":
                        main.handleMouseEvent(split);
                        break;
                }
            }
        }
    }

    private void handleMouseEvent(String[] parameters){
        String action = parameters[1];

        switch (action){
            case "move":
                moveMouse(Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]));
                break;
            case "click":
                clickMouse(parameters[2]);
        }
    }

    private void moveMouse(int _x, int _y) {
        x += _x;
        y += _y;
        if (x < 0) x = 0;
        if (x > X) x = X;
        if (y < 0) y = 0;
        if (y > Y) y = Y;
        robot.mouseMove(x, y);
    }

    private void clickMouse(String button){
        switch (button){
            case "left":
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                break;
            case "right":
                robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                System.out.println(button + " mouse button clicked");
        }
    }
}
