package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.FavoritePath;
import org.yugo.backend.YuGo.repository.FavoritePathRepository;

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
    public FavoritePath get(Integer id) {
        Optional<FavoritePath> favoritePathOptional = favoritePathRepository.findById(id);
        if (favoritePathOptional.isPresent()){
            return favoritePathOptional.get();
        }
        throw new NotFoundException("Favourite path not found!");
    }

    @Override
    public List<FavoritePath> getByPassengerId(Integer id) {
        Optional<List<FavoritePath>> favoritePathListOptional = favoritePathRepository.findByPassengerId(id);
        if (favoritePathListOptional.isPresent()){
            return favoritePathListOptional.get();
        }
        throw new NotFoundException("Favourite paths not found!");
    }

    @Override
    public void delete(Integer id){
        if (favoritePathRepository.findById(id).isEmpty())
            throw new NotFoundException("Favorite Location does not exist!");
        favoritePathRepository.deleteById(id);
    }
}
