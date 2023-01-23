package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.Path;
import org.yugo.backend.YuGo.repository.PathRepository;

import java.util.List;
import java.util.Optional;
@Service
public class PathServiceImpl implements PathService {
    private final PathRepository pathRepository;

    @Autowired
    public PathServiceImpl(PathRepository pathRepository){
        this.pathRepository = pathRepository;
    }

    @Override
    public Path insert(Path path){
        return pathRepository.save(path);
    }

    @Override
    public List<Path> getAll() {
        return pathRepository.findAll();
    }

    @Override
    public Path get(Integer id) {
        Optional<Path> pathOptional = pathRepository.findById(id);
        if (pathOptional.isPresent()){
            return pathOptional.get();
        }
        throw new NotFoundException("Path not found!");
    }
}
