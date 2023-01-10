package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.FavoritePath;
import org.yugo.backend.YuGo.repository.FavoritePathRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritePathService {

    FavoritePath insert(FavoritePath favoritePath);
    List<FavoritePath> getAll();
    FavoritePath get(Integer id);
    List<FavoritePath> getByPassengerId(Integer id);
    void delete(Integer id);
}
