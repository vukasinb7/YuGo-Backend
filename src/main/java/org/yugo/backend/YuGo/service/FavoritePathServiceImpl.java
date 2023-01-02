package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.FavoritePath;
import org.yugo.backend.YuGo.model.Rejection;
import org.yugo.backend.YuGo.repository.FavoritePathRepository;
import org.yugo.backend.YuGo.repository.RejectionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritePathServiceImpl implements FavoritePathService {

    private final FavoritePathRepository favoritePathRepository;

    @Autowired
    public FavoritePathServiceImpl(FavoritePathRepository favoritePathRepository){
        this.favoritePathRepository = favoritePathRepository;
    }

    @Override
    public FavoritePath insert(FavoritePath favoritePath){
        return favoritePathRepository.save(favoritePath);
    }

    @Override
    public List<FavoritePath> getAll() {
        return favoritePathRepository.findAll();
    }

    @Override
    public Optional<FavoritePath> get(Integer id) {
        return favoritePathRepository.findById(id);
    }

    @Override
    public Optional<List<FavoritePath>> getByPassengerId(Integer id){return Optional.ofNullable(favoritePathRepository.findByPassengerId(id));}
}
