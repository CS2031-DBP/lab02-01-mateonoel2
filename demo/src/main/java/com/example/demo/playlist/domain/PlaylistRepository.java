package com.example.demo.playlist.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.song.domain.Song;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findBySongsContaining(Song song);
}