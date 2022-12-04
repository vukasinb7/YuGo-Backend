package org.yugo.backend.YuGo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.Note;
import org.yugo.backend.YuGo.model.User;

public interface NoteRepository extends JpaRepository<Note,Integer> {
    @Query(value = "SELECT * FROM NOTES WHERE user_id = :userId", nativeQuery = true)
    public Page<Note> findUserNotes(@Param("userId") Integer userId, Pageable page);
}
