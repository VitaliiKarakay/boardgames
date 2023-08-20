package com.vkarakai.boardgames.repository;

import com.vkarakai.boardgames.model.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
}
