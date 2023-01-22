package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.Panic;
import org.yugo.backend.YuGo.repository.PanicRepository;

import java.util.Optional;
@Service
public class PanicServiceImpl implements PanicService {
    private final PanicRepository panicRepository;
    private final WebSocketService webSocketService;

    @Autowired
    public PanicServiceImpl(PanicRepository panicRepository, WebSocketService webSocketService){
        this.panicRepository = panicRepository;
        this.webSocketService = webSocketService;
    }

    @Override
    public Panic insert(Panic panic){
        Panic createdPanic = panicRepository.save(panic);
        webSocketService.notifyAdminsAboutPanic(createdPanic.getId());
        return createdPanic;
    }

    @Override
    public Page<Panic> getAll(Pageable page) {
        return panicRepository.findAllPanics(page);
    }

    @Override
    public Panic get(Integer id) {
        Optional<Panic> panicOptional = panicRepository.findById(id);
        if (panicOptional.isPresent()){
            return panicOptional.get();
        }
        throw new NotFoundException("Panic not found!");
    }
}
