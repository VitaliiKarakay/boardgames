package com.vkarakai.boardgames.controller;

import com.vkarakai.boardgames.model.BoardGame;
import com.vkarakai.boardgames.repository.BoardGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boardgames")
public class BoardGameController {
    private final BoardGameRepository boardGameRepository;

    @Autowired
    public BoardGameController(BoardGameRepository boardGameRepository) {
        this.boardGameRepository = boardGameRepository;
    }

    @GetMapping
    public List<BoardGame> getAllBoardGames() {
        return boardGameRepository.findAll();
    }

    @GetMapping("/{id}")
    public BoardGame getBoardGameById(@PathVariable Long id) {
        return boardGameRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<String> addBoardGame(@RequestBody BoardGame boardGame) {
        boardGameRepository.save(boardGame);
        return ResponseEntity.ok("Board game created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoardGame(@PathVariable Long id) {
        boardGameRepository.deleteById(id);
        return ResponseEntity.ok("Board game deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBoardGame(@PathVariable Long id, @RequestBody BoardGame updatedGame) {
        Optional<BoardGame> optionalGame = boardGameRepository.findById(id);

        if (optionalGame.isPresent()) {
            BoardGame existingGame = optionalGame.get();
            existingGame.setName(updatedGame.getName());
            existingGame.setDescription(updatedGame.getDescription());
            existingGame.setRules(updatedGame.getRules());

            boardGameRepository.save(existingGame);
            return ResponseEntity.ok("Board game updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
