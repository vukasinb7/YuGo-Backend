package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.FavoritePath;

import java.util.List;
import java.util.Optional;

public interface FavoritePathRepository extends JpaRepository<FavoritePath,Integer> {

    @Query(value = "select * from favorite_paths where owner=:passengerId",nativeQuery = true)
    Optional<List<FavoritePath>> findByPassengerId(@Param("passengerId") Integer passengerId);
}
