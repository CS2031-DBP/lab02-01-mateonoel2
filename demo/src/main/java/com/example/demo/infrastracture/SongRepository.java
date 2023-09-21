package com.example.demo.infrastracture;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long> {}