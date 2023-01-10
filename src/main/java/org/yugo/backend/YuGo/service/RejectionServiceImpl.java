package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exceptions.NotFoundException;
import org.yugo.backend.YuGo.model.Rejection;
import org.yugo.backend.YuGo.repository.RejectionRepository;

import java.util.List;
import java.util.Optional;
@Service
public class RejectionServiceImpl implements RejectionService {
    private final RejectionRepository rejectionRepository;

    @Autowired
    public RejectionServiceImpl(RejectionRepository rejectionRepository){
        this.rejectionRepository = rejectionRepository;
    }

    @Override
    public Rejection insert(Rejection rejection){
        return rejectionRepository.save(rejection);
    }

    @Override
    public List<Rejection> getAll() {
        return rejectionRepository.findAll();
    }

    @Override
    public Rejection get(Integer id) {
        Optional<Rejection> rejectionOptional = rejectionRepository.findById(id);
        if (rejectionOptional.isPresent()){
            return rejectionOptional.get();
        }
        throw new NotFoundException("Reject not found!");
    }
}
