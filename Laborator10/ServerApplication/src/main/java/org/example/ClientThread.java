package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ClientThread extends Thread
{
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private GameServer server;
    private String id_game;
    private String playerName;
    private int board_size;
    private boolean joined;

    public ClientThread(Socket clientSocket, GameServer server)
    {
        this.clientSocket = clientSocket;
        this.server = server;
        try
        {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        }
        catch (IOException e)
        {
            System.err.println("Error creating input/output streams: " + e.getMessage());
        }
        this.joined = false;
    }

    public Socket getClientSocket ()
    {
        return this.clientSocket;
    }

    @Override
    public void run()
    {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        )
        {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {

                System.out.println("Got the following command from the client: " + inputLine);
                String[] components = inputLine.split(" ");
                if (components[0].equals("stop"))
                {
                    server.stop();
                    out.println("Server stopped");
                    break;
                }
                else if (components[0].equals("status"))
                {
                    Map<String, Game> games = server.getGames();
                    Game game = games.get(id_game);
                    Player player = new Player();
                    if (playerName.equals(game.getPlayers().get(0).getName()))
                        player = game.getPlayers().get(0);
                    else if (playerName.equals(game.getPlayers().get(1).getName()))
                        player = game.getPlayers().get(1);
                    String status = server.getStatus(id_game, player);
                    System.out.println("Sent status to player " + playerName);
                    if (game.getPlayers().get(0).getTime() <= 0 || game.getPlayers().get(1).getTime() <= 0)
                    {
                        boolean iswinner0 = game.isWinner('?');
                        boolean iswinner1 = game.isWinner('*');
                        if(iswinner1==false && iswinner0==false)
                        {
                            status= "No one won";
                        }
                        else if (iswinner0==true)
                        {
                            status = "Player " + game.getPlayers().get(0).getName() + "won";
                        }
                        else if (iswinner1==true)
                        {
                            status = "Player " + game.getPlayers().get(1).getName() + " won";
                        }
                    }
                    out.println(status);
                }
                else if (components[0].equals("join"))
                {
                    String id_game1 = components[1];
                    String playerName1 = components[2];
                    if(joined == false)
                    {
                        id_game=id_game1;
                        playerName=playerName1;
                        joined=true;
                        server.joinGame(id_game, playerName, this);
                        out.println("You joined the game " + id_game + " with the name: " + playerName);
                    }
                    else
                    {
                        out.println("You are already connected to a game.");
                    }
                }
                else if (components[0].equals("create"))
                {
                    id_game = components[1];
                    board_size = Integer.parseInt(components[2]);
                    server.createGame(id_game, board_size, 10000);
                    out.println("Game " + id_game + " created");
                }
                else if (components[0].equals("submit"))
                {
                    Map<String, Game> games = server.getGames();
                    Game game = games.get(id_game);
                    Player player = new Player();
                    if (playerName.equals(game.getPlayers().get(0).getName()))
                        player = game.getPlayers().get(0);
                    else if (playerName.equals(game.getPlayers().get(1).getName()))
                        player = game.getPlayers().get(1);
                    int row = Integer.parseInt(components[1]);
                    int col = Integer.parseInt(components[2]);
                    String message = server.submitMove(id_game, row, col, player);
                    boolean iswinner0 = game.isWinner('?');
                    boolean iswinner1 = game.isWinner('*');
                    if (iswinner0==true)
                    {
                        message = "Player " + game.getPlayers().get(0).getName() + " won";
                        server.CloseAllClients(message);
                    }
                    else if (iswinner1==true)
                    {
                        message = "Player " + game.getPlayers().get(1).getName() + " won";
                        server.CloseAllClients(message);
                    }
                    else if (game.getPlayers().get(0).getTime() <= 0 || game.getPlayers().get(1).getTime() <= 0)
                    {
                        if(iswinner1==false && iswinner0==false)
                        {
                            message= "No one won";
                            server.CloseAllClients(message);
                        }
                    }
                    out.println(message);
                }
                else
                {
                    out.println("Command: " + inputLine + " is incorrect!");
                }
            }

        }
        catch (IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}