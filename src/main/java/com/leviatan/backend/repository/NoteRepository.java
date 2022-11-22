package com.leviatan.backend.repository;

import com.leviatan.backend.model.Note;
import com.leviatan.backend.model.Organization;
import com.leviatan.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, String> {

    Page<Note> findAll(Pageable pageable);
    Page<Note> getAllByContentContaining(String content, Pageable pageable);
    List<Note> findByOrganization_IdAndMatch_Id(String organizationId, String id);
}
