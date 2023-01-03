package org.yugo.backend.YuGo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.dto.FavoritePathOut;
import org.yugo.backend.YuGo.dto.ReviewOut;
import org.yugo.backend.YuGo.model.FavoritePath;
import org.yugo.backend.YuGo.model.RideReview;

@Component
public class FavoritePathMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public FavoritePathMapper(ModelMapper modelMapper) {
        FavoritePathMapper.modelMapper = modelMapper;
    }


    public static FavoritePath fromDTOtoFavoritePath(FavoritePathOut dto) {
        return modelMapper.map(dto, FavoritePath.class);
    }

    public static FavoritePathOut fromFavoritePathtoDTO(FavoritePath favoritePath) {
        return modelMapper.map(favoritePath, FavoritePathOut.class);
    }

}
