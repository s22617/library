package com.s22617.library;

import com.s22617.library.Models.Book;
import com.s22617.library.Models.Library;
import com.s22617.library.Repositores.LibraryRepository;
import com.s22617.library.Services.LibraryService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class LibraryServiceIT {

    @Autowired
    private LibraryService libraryService;

    @MockBean
    private LibraryRepository libraryRepository;

        @Test
    void shouldUpdateLibraryName() {
        String name = "Library of Canterbury";
        Library library = new Library(1, "Library of Burmingham",
                "Centenary Sq, Broad St, Birmingham B1 2EA, Great Britain", 70, List.of());
        libraryService.updateLibraryName(library, name);
        assertThat(library.getName()).isEqualTo(name);
    }

    @Test
    void shouldBeFromUK() {
        Library library = new Library(1, "Library of Burmingham",
                "Centenary Sq, Broad St, Birmingham B1 2EA, Great Britain", 70, List.of());
        assertThat(libraryService.isFromUK(library)).isEqualTo(true);
    }

    @Test
    void shouldTitleBeTwoPart() {
        Book book = new Book(0, "Mark kraM", "Lorum Ip.");
        libraryService.isTitleTwoPart(book);
        assertThat(libraryService.isTitleTwoPart(book)).isEqualTo(true);
    }

    @Test
    void shouldLibrariesWithLessBooksThanBeEqualTo() {
        Book book1 = new Book(0, "Mark kraM", "Lorum Ip.");
        Book book2 = new Book(1, "Mark kraM", "Lorum Ip.");
        Book book3 = new Book(2, "Mark kraM", "Lorum Ip.");

        Library library1 = new Library(1, "Library of Gdansk",
                "Gdansk, Poland", 70, List.of(book1, book2));
        Library library2 = new Library(1, "Library of Gdynia",
                "Gdynia, Poland", 70, List.of(book1));
        Library library3 = new Library(1, "Library of Sopot",
                "Sopot, Poland", 70, List.of(book1, book2, book3));

        List<Library> libraries = List.of(library1, library2, library3);

        assertThat(libraryService.getLibrariesByBooksCount(libraries, 2)).hasSize(2);
    }

    @Test
    void shouldAddSuffixToTitle() {
        Book book = new Book(0, "Kram Kran", "Wojtysława Patrymoniusz");
        libraryService.addSuffixToTitle(book);
        assertThat(book.getTitle()).isEqualTo("Kram Kran_PL");
    }

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
