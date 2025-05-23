    package org.example.connectfour.server.controller;

    import org.example.connectfour.connectFour.core.Board;
    import org.example.connectfour.connectFour.core.Cell;
    import org.example.connectfour.connectFour.core.Game;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.server.ResponseStatusException;


    import java.util.*;
    import java.util.concurrent.ConcurrentHashMap;

    @RestController
    @RequestMapping("api/connectFour")
    public class ConnectFourController {

        private final Map<String, Game> games = new ConcurrentHashMap<>();

        @PostMapping("/new")
        public  Map<String, Object> newGame(@RequestParam int rows, @RequestParam int columns, @RequestParam boolean withBot, @RequestParam int difficulty) {
            Board board = new Board(rows, columns);
            Game game = new Game(board, withBot, difficulty);

            String gameId = UUID.randomUUID().toString();
            games.put(gameId, game);
            Map<String, Object> response = getJSONFormat(game);
            response.put("gameId", gameId);
            return response;
        }

        @PostMapping("/move")
        public  Map<String, Object> playerMove(@RequestParam("gameId") String gameId, @RequestParam("column") int column) {


            Game game = games.get(gameId);
            if (game == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");

            game.makeMove(column);
            return getJSONFormat(game);
        }

        @GetMapping("/bot/move")
        public  Map<String, Object> botMove(@RequestParam("gameId") String gameId) {
            Game game = games.get(gameId);
            if (game == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");

            game.playBotTurn();
            return getJSONFormat(game);
        }

        private Map<String, Object> getJSONFormat(Game game) {
            Map<String, Object> response = new HashMap<>();
            response.put("board", getBoard(game.getBoard()));
            response.put("gameOver", game.isGameOver());
            response.put("gameState", game.getGameState().toString());
            return response;
        }

        public static Object getBoard(Board board) {
            Cell[][] cells = board.getCells();
            List<List<String>> boardList = new ArrayList<>();
            for (Cell[] cell : cells) {
                List<String> row = new ArrayList<>();
                for (Cell value : cell) {
                    row.add(value.getState().toString());
                }
                boardList.add(row);
            }
            return boardList;
        }
    }
