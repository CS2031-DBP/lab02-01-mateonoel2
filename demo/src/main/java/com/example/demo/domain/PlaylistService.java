package com.example.demo.domain;

import com.example.demo.infrastracture.PlaylistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist savePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Optional<Playlist> updatePlaylist(Long id, Playlist playlist) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()) {
            Playlist existingPlaylist = optionalPlaylist.get();
            existingPlaylist.setTitle(playlist.getTitle());
            existingPlaylist.setSongs(playlist.getSongs());
            existingPlaylist.setCoverImage(playlist.getCoverImage());
            playlistRepository.save(existingPlaylist);
        }
        return optionalPlaylist;
    }

    public Optional<Playlist> patchPlaylist(Long id, Playlist playlist) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()) {
            Playlist existingPlaylist = optionalPlaylist.get();
            if (playlist.getTitle() != null) {
                existingPlaylist.setTitle(playlist.getTitle());
            }
            if (playlist.getSongs() != null) {
                existingPlaylist.setSongs(playlist.getSongs());
            }
            if (playlist.getCoverImage() != null) {
                existingPlaylist.setCoverImage(playlist.getCoverImage());
            }
            playlistRepository.save(existingPlaylist);
        }
        return optionalPlaylist;
    }

    public Optional<Playlist> deletePlaylist(Long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isPresent()) {
            playlistRepository.deleteById(id);
        }
        return optionalPlaylist;
    }
}
