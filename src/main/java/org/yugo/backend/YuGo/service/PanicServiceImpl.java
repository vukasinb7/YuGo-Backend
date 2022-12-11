package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.Panic;
import org.yugo.backend.YuGo.repository.PanicRepository;

import java.util.List;
import java.util.Optional;
@Service
public class PanicServiceImpl implements PanicService {
    private final PanicRepository panicRepository;

    @Autowired
    public PanicServiceImpl(PanicRepository panicRepository){
        this.panicRepository = panicRepository;
    }

    @Override
    public Panic insert(Panic panic){
        return panicRepository.save(panic);
    }

    @Override
    public List<Panic> getAll() {
        return panicRepository.findAll();
    }

    @Override
    public Optional<Panic> get(Integer id) {
        return panicRepository.findById(id);
    }
}
