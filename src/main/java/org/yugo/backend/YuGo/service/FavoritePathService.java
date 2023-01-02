package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.FavoritePath;
import org.yugo.backend.YuGo.repository.FavoritePathRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritePathService {

    public FavoritePath insert(FavoritePath favoritePath);
    public List<FavoritePath> getAll();
    public Optional<FavoritePath> get(Integer id);
    public Optional<List<FavoritePath>> getByPassengerId(Integer id);
}
