package com.example.asset.management.controller;

import com.example.asset.management.exception.ResourceNotFoundException;
import com.example.asset.management.model.Notes;
import com.example.asset.management.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class NotesController {
	
	@Autowired
	NoteRepository noteRepository;
	
	@GetMapping("/notes")
	public List<Notes> getAllNotes(){
		return noteRepository.findAll();
	}
	
	@PostMapping("notes")
	public Notes createNotes(@Valid @RequestBody Notes note) {
		return noteRepository.save(note);
	}
	
	@GetMapping("/notes/{id}")
	public Notes getNotesById(@PathVariable(value="id") Long noteId) {
		return noteRepository.findById(noteId).orElseThrow(()-> new ResourceNotFoundException("Note", "id", noteId));
	}
	
	@PutMapping("/notes/{id}")
	public Notes updateNote(@PathVariable(value="id") Long noteId , @Valid @RequestBody Notes noteDetails) {
		//find if it exists or throw exception
		
		Notes note = noteRepository.findById(noteId).orElseThrow(()-> new ResourceNotFoundException("Notes", "id", noteId));
		
		// update the variable
		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());
		
		Notes updatedNote = noteRepository.save(note);
		return updatedNote;
	}
	
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value="id") Long noteID){
		// Find that the note exists
		Notes note = noteRepository.findById(noteID).orElseThrow(()->new ResourceNotFoundException("Notes", "id", noteID));
		
		noteRepository.delete(note);
		return ResponseEntity.ok().build();
	}
	

}
