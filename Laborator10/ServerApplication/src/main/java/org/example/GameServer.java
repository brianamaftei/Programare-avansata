package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;

public class GameServer
{
    private final int port;
    private ServerSocket serverSocket;
    private boolean running = false;
    private Map<String, Game> games = new HashMap<>();
    private List<ClientThread> clients = new ArrayList<>();

    public GameServer(int port)
    {
        this.port = port;
    }

    public void start() throws IOException
    {
        serverSocket = new ServerSocket(port);
        running = true;
        System.out.println("Game server started at port " + port);

        while (running)
        {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());
            ClientThread client = new ClientThread(clientSocket, this);
            clients.add(client);
            client.start();
        }
    }

    public void stop() throws IOException
    {
        running = false;
        serverSocket.close();
        System.out.println("Server stopped.");
    }

    public void createGame(String gameId, int boardSize, int timeLimit)
    {
        if (games.containsKey(gameId))
        {
            System.out.println("Game " + gameId + " already exists");
            return;
        }

        Game game = new Game(boardSize);
        games.put(gameId, game);
        System.out.println("Game " + gameId + " has been created");
    }

    public Map<String, Game> getGames ()
    {
        return games;
    }

    public void joinGame(String gameId, String playerName, ClientThread client)
    {
        Game game = games.get(gameId);
        if (game == null)
        {
            System.out.println("Game " + gameId + " doesn't exist");
            return;
        }

        Player player = new Player(playerName, client);
        boolean success = game.addPlayer(player);
        if (!success)
        {
            System.out.println("Can't join game " + gameId);
            return;
        }

        System.out.println("Player " + playerName + " joined game " + gameId);
    }

    public String submitMove(String gameId, int row, int col, Player player)
    {
        Game game = games.get(gameId);
        String message = "";
        if (game == null)
        {
            message = "Error from server";
            System.out.println("Game " + gameId + " doesn't exist");
            return message;
        }

        boolean timeOut = false;
        boolean success = game.makeMove(row, col, player, timeOut);
        if (!success)
        {
            message = "Invalid move";
            System.out.println("Invalid move from player " + player.getName());
            return message;
        }
        message = "You moved in cell [" + row + "][" + col + "]";
        System.out.println("Player " + player.getName() + " moved in " + gameId);
        return message;
    }

    public void CloseAllClients (String message)
    {

        for (ClientThread client : clients)
        {
            try
            {
                Socket socket = client.getClientSocket();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        for (ClientThread client : clients)
        {
            try
            {
                Socket socket = client.getClientSocket();
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    public String getStatus(String gameId, Player player)
    {
        Game game = games.get(gameId);
        if (game == null)
        {
            System.out.println("Game " + gameId + " doesn't exist");
            return "Game not found";
        }

        String status = game.getStatus(player);
        return status;
    }


    public static void main(String[] args)
    {
        GameServer server = new GameServer(5000);
        try
        {
            server.start();
        }
        catch (IOException e)
        {
            System.err.println("Error starting the server: " + e.getMessage());
        }
    }
}