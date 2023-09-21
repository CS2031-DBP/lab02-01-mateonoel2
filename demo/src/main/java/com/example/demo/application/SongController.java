package com.example.demo.application;

import com.example.demo.domain.Song;
import com.example.demo.domain.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping
    public ResponseEntity<List<Song>> songs() {
        List<Song> songs = songService.getAllSongs();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> song(@RequestBody Song song) {
        songService.saveSong(song);
        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSong(@PathVariable Long id, @RequestBody Song song) {
        Optional<Song> updatedSong = songService.updateSong(id, song);
        return updatedSong.isPresent() ? ResponseEntity.status(200).body("Updated") : ResponseEntity.status(404).body("Not Found");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchSong(@PathVariable Long id, @RequestBody Song song) {
        Optional<Song> patchedSong = songService.patchSong(id, song);
        return patchedSong.isPresent() ? ResponseEntity.status(200).body("Updated partially") : ResponseEntity.status(404).body("Not Found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        Optional<Song> deletedSong = songService.deleteSong(id);
        return deletedSong.isPresent() ? ResponseEntity.status(200).body("Deleted") : ResponseEntity.status(404).body("Not Found");
    }
}
