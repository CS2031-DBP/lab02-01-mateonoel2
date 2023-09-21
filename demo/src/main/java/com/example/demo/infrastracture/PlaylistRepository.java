package com.example.demo.infrastracture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Playlist;
import com.example.demo.domain.Song;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findBySongsContaining(Song song);
}