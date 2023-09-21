package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Playlist;
import com.example.demo.domain.PlaylistService;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<List<Playlist>> playlists() {
        List<Playlist> playlists = playlistService.getAllPlaylists();
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> playlist(@RequestBody Playlist playlist) {
        playlistService.savePlaylist(playlist);
        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> playlist(@PathVariable("id") Long id, @RequestBody Playlist playlist) {
        playlistService.updatePlaylist(id, playlist);
        return ResponseEntity.status(200).body("Updated");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchPlaylist(@PathVariable("id") Long id, @RequestBody Playlist playlist) {
        playlistService.patchPlaylist(id, playlist);
        return ResponseEntity.status(200).body("Updated partially");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable("id") Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.status(200).body("Deleted");
    }

}
