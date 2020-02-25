package com.example.asset.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.asset.management.model.Notes;


@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {

}
