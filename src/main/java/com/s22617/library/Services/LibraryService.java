package com.s22617.library.Services;

import com.s22617.library.Models.Book;
import com.s22617.library.Models.Cover;
import com.s22617.library.Models.Genre;
import com.s22617.library.Models.Library;
import com.s22617.library.Repositores.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Library getExampleLibrary() {
        Book book1 = new Book(null, "Romeo and Juliet", "William Shakespeare",
                Genre.TRAGEDY,false, "9782123456803", Cover.HARDCOVER);
        Book book2 = new Book(null, "The Insiders", "Paul Knight",
                Genre.HORROR,false, "9782123456745", Cover.DUST_JACKET);
        List<Book> catalogue = new ArrayList<>();
        catalogue.add(book1);
        catalogue.add(book2);
        Library oxfordLibrary = new Library(null, "Bodleian Libraries",
                "Broad St, Oxford OX1 3BG, Great Britain",
                97, catalogue);

        return libraryRepository.save(oxfordLibrary);
    }

    public Library findById(Integer id) {
        Optional<Library> libraryById = libraryRepository.findById(id);

        return libraryById.orElse(null);
    }

    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    public boolean existsById(int id) {
        return libraryRepository.existsById(id);
    }

    public void deleteById(int id) {
        libraryRepository.deleteById(id);
    }

    public Library provideNewLibrary(String name) {
        return libraryRepository.save(new Library(null, name));
    }

    public List<Library> selectByRating() {
        return libraryRepository.selectByRating();
    }

    public Book updateIsAvailable(Integer libId, Integer bId) {
        if (existsById(libId)) {
            for (Book book : findById(libId).getBooks()) {
                if (book.getId().equals(bId)) {
                    book.setAvailable(true);
                    return book;
                }
            }
        }
        return null;
    }

    public Library updateLibraryName(Library library, String name) {
        if (library.getName() != null) {
            library.setName(name);
        }
        return library;
    }

    public boolean isFromUK(Library library) {
        if (library.getLocation().contains("Great Britain")) {
            return true;
        }
        return false;
    }

    public boolean isTitleTwoPart(Book book) {
        if (book.getTitle().contains(" ")) {
            return true;
        }
        return false;
    }

    public List<Library> getLibrariesByBooksCount(List<Library> library, int count) {
        List<Library> fetchedLibraries = new ArrayList<>();
        for (Library lib : library) {
            if (lib.getBooks().size() <= count) {
                fetchedLibraries.add(lib);
            }
        }
        return fetchedLibraries;
    }

    public Book addSuffixToTitle(Book book) {
        if (book.getTitle() != null) {
            book.setTitle(book.getTitle() + "_PL");
        }
        return book;
    }

    public Library getEmptyLibraryForName(String name) {
        return new Library(null, name, "Wales, Banglor", 87, null);
    }
}
