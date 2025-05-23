package org.example.connectfour.server.controller;


import org.example.connectfour.server.multiplayer.MultiplayerGameService;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("api/connectFour/multiplayer")
public class MultiplayerController {
    MultiplayerGameService gameService = new MultiplayerGameService();

    @PostMapping("/createRoom")
    public String createRoom(@RequestParam("owner") String owner, @RequestParam("rows") int rows, @RequestParam("columns") int columns) {
        return gameService.createRoom(owner, rows,columns);
    }
    @PostMapping("/join")
    public Map<String, Object> joinRoom(@RequestParam("roomId") String roomId, @RequestParam("username") String username) {
        return gameService.joinRoom(roomId, username);
    }

    @GetMapping("/game")
    public Map<String, Object> getGame(@RequestParam("roomId") String roomId) {
        return gameService.getActiveGame(roomId);
    }

    @PostMapping("/move")
    public Map<String, Object> makeMove(@RequestParam("roomId") String roomId, @RequestParam("username") String userName, @RequestParam("column") int column) {
        return gameService.makeMove(roomId, userName, column);
    }
    @PostMapping("/leave")
    public String leaveRoom(@RequestParam("roomId") String roomId, @RequestParam("username") String username) {
       return gameService.leaveRoom(roomId, username);
    }





}
