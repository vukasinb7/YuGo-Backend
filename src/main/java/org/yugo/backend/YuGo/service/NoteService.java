package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note save(Note note);

    List<Note> getAll();

    Optional<Note> get(Integer id);
}
