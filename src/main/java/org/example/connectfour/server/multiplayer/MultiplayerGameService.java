package org.example.connectfour.server.multiplayer;



import org.example.connectfour.connectFour.core.Board;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.example.connectfour.server.controller.ConnectFourController.getBoard;


public class MultiplayerGameService {

    private final Map<String, MultiplayerGame> activeGames = new HashMap<>();

    public String createRoom(String owner, int rows, int columns) {
        String gameId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);;
        Board board = new Board(rows, columns);
        MultiplayerGame game = new MultiplayerGame(board, false, -1, owner, null);
        activeGames.put(gameId, game);
        return gameId;
    }

    public Map<String, Object> joinRoom(String roomId, String username){
        MultiplayerGame game = activeGames.get(roomId);
        if(game == null){
            return null;
        }
        if(game.getPlayer2() == null) {
            game.setPlayer2(username);
        }
        return getJSONFormat(game);
    }

    private Map<String, Object> getJSONFormat(MultiplayerGame game) {
        Map<String, Object> response = new HashMap<>();
        response.put("board", getBoard(game.getBoard()));
        response.put("gameState", game.getGameState().toString());
        response.put("currentPlayer", game.getCurrentPlayerTurn());
        response.put("player_1", game.getPlayer1());
        response.put("player_2", game.getPlayer2());
        return response;
    }

    public Map<String, Object> getActiveGame(String roomId) {
        MultiplayerGame game = activeGames.get(roomId);
        if(game == null){
            return null;
        }
        return getJSONFormat(game);
    }

    public Map<String, Object> makeMove(String roomId, String username, int column) {
        MultiplayerGame game = activeGames.get(roomId);
        game.makeMove(username, column);
        return getJSONFormat(game);
    }

    public String leaveRoom(String roomId, String username) {
        MultiplayerGame game = activeGames.get(roomId);
        if(game == null){
            return "game not found";
        }

        if( game.getPlayer1() != null && game.getPlayer1().equals(username)){
            game.setPlayer1(game.getPlayer2());
            game.setPlayer2(null);
        }
        if(game.getPlayer2() != null && game.getPlayer2().equals(username)){
            game.setPlayer2(null);
        }

        if(game.getPlayer1() == null && game.getPlayer2() == null){
            activeGames.remove(roomId);
        }

        return "success";
    }


}
