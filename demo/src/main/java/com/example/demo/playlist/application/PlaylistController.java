package com.example.demo.playlist.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.playlist.domain.Playlist;
import com.example.demo.playlist.domain.PlaylistRepository;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistRepository playlistRepository;

    @GetMapping
    public ResponseEntity<List<Playlist>> playlists() {
        List<Playlist> playlists = playlistRepository.findAll();
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> playlist(@RequestBody Playlist playlist) {
        playlistRepository.save(playlist);
        return ResponseEntity.status(201).body("Created");
    }
}