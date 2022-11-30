package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.yugo.backend.YuGo.model.Path;
import org.yugo.backend.YuGo.repository.PathRepository;

import java.util.List;
import java.util.Optional;

public class PathServiceImpl implements PathService {
    private final PathRepository pathRepository;

    @Autowired
    public PathServiceImpl(PathRepository pathRepository){
        this.pathRepository = pathRepository;
    }

    @Override
    public Path add(Path path){
        return pathRepository.save(path);
    }

    @Override
    public List<Path> getAll() {
        return pathRepository.findAll();
    }

    @Override
    public Optional<Path> get(Integer id) {
        return pathRepository.findById(id);
    }
}
