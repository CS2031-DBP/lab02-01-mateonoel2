package com.example.demo.domain;

import com.example.demo.infrastracture.SongRepository;
import com.example.demo.infrastracture.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    public Optional<Song> updateSong(Long id, Song song) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            Song existingSong = optionalSong.get();
            existingSong.setTitle(song.getTitle());
            existingSong.setArtist(song.getArtist());
            existingSong.setAlbum(song.getAlbum());
            existingSong.setReleaseDate(song.getReleaseDate());
            existingSong.setGenre(song.getGenre());
            existingSong.setDuration(song.getDuration());
            existingSong.setCoverImage(song.getCoverImage());
            existingSong.setSpotifyUrl(song.getSpotifyUrl());
            songRepository.save(existingSong);
        }
        return optionalSong;
    }

    public Optional<Song> patchSong(Long id, Song song) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            Song existingSong = optionalSong.get();
            if (song.getTitle() != null) {
                existingSong.setTitle(song.getTitle());
            }
            if (song.getArtist() != null) {
                existingSong.setArtist(song.getArtist());
            }
            if (song.getAlbum() != null) {
                existingSong.setAlbum(song.getAlbum());
            }
            if (song.getReleaseDate() != null) {
                existingSong.setReleaseDate(song.getReleaseDate());
            }
            if (song.getGenre() != null) {
                existingSong.setGenre(song.getGenre());
            }
            if (song.getDuration() != null) {
                existingSong.setDuration(song.getDuration());
            }
            if (song.getCoverImage() != null) {
                existingSong.setCoverImage(song.getCoverImage());
            }
            if (song.getSpotifyUrl() != null) {
                existingSong.setSpotifyUrl(song.getSpotifyUrl());
            }
            songRepository.save(existingSong);
        }
        return optionalSong;
    }

    public Optional<Song> deleteSong(Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if (optionalSong.isPresent()) {
            Song existingSong = optionalSong.get();
            List<Playlist> playlistsContainingSong = playlistRepository.findBySongsContaining(existingSong);
            for (Playlist playlist : playlistsContainingSong) {
                playlist.getSongs().remove(existingSong);
                playlistRepository.save(playlist);
            }
            songRepository.delete(existingSong);
        }
        return optionalSong;
    }
}
