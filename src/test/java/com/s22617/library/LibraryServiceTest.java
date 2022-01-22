package com.s22617.library;

import com.s22617.library.Models.Library;
import com.s22617.library.Repositores.LibraryRepository;
import com.s22617.library.Services.LibraryService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryService libraryService;

    @Test
    void shouldFindById() {
        when(libraryRepository.findById(any())).thenReturn(Optional.of(new Library()));
        Library libraryById = libraryService.findById(7);
        assertThat(libraryById).isNotNull();
    }

    @Test
    void shouldNotFindById() {
        when(libraryRepository.findById(any())).thenReturn(Optional.empty());
        Library libraryById = libraryService.findById(2);
        assertThat(libraryById).isNull();
    }

    @Test
    void shouldExistById() {
        when(libraryRepository.existsById(any())).thenReturn(true);
        boolean libraryExists = libraryService.existsById(1);
        assertThat(libraryExists).isEqualTo(true);
    }

    @Test
    void shouldNotExistById() {
        when(libraryRepository.existsById(any())).thenReturn(false);
        boolean libraryExists = libraryService.existsById(5);
        assertThat(libraryExists).isFalse();
    }

    @Test
    @Disabled
    void shouldDeleteByIds() {
        libraryService.deleteById(54);
        libraryService.deleteById(55);
        verify(libraryRepository, times(2)).deleteById(any());
    }

    @Test
    void shouldDeleteById() {
        libraryService.deleteById(54);
        verify(libraryRepository).deleteById(any());
    }

    @Test
    void shouldGetAll() {
        when(libraryRepository.findAll()).thenReturn(List.of(new Library(), new Library(), new Library()));
        List<Library> fetchedLibraries = libraryService.getAllLibraries();
        assertThat(fetchedLibraries.size()).isEqualTo(3);
    }

    @Test
    void shouldNotGetAll() {
        when(libraryRepository.findAll()).thenReturn(List.of());
        List<Library> fetchedLibraries = libraryService.getAllLibraries();
        assertThat(fetchedLibraries.size()).isEqualTo(0);
    }
}
