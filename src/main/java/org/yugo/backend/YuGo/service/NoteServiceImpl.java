package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.Note;
import org.yugo.backend.YuGo.repository.NoteRepository;

import java.util.List;
import java.util.Optional;
@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
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
    public Optional<Note> get(Integer id) {
        return noteRepository.findById(id);
    }

    @Override
    public Page<Note> getUserNotes(Integer userId, Pageable page){
        return noteRepository.findNotesByUser(userId, page);
    }
}
