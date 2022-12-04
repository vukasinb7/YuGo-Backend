package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note save(Note note);

    List<Note> getAll();

    Optional<Note> get(Integer id);

    Page<Note> getUserNotes(Integer userId, Pageable page);
}
