package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exceptions.NotFoundException;
import org.yugo.backend.YuGo.model.Note;
import org.yugo.backend.YuGo.repository.NoteRepository;

import java.util.List;
import java.util.Optional;
@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final UserService userService;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, UserService userService){
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    @Override
    public Note insert(Note note){
        return noteRepository.save(note);
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note get(Integer id) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if (noteOptional.isPresent()){
            return noteOptional.get();
        }
        throw new NotFoundException("Note not found!");
    }

    @Override
    public Page<Note> getUserNotes(Integer userId, Pageable page){
        userService.getUser(userId);
        return noteRepository.findNotesByUser(userId, page);
    }
}
