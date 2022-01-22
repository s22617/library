package com.s22617.library.Controllers;

import com.s22617.library.Models.Book;
import com.s22617.library.Models.Library;
import com.s22617.library.Services.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryRestController {

    private final LibraryService libraryService;

    public LibraryRestController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/oxfordLibrary")
    public ResponseEntity<Library> getOxfordLibrary() {
        return ResponseEntity.ok(libraryService.getExampleLibrary());
    }

    @GetMapping("/provideNewLibrary")
    public ResponseEntity<Library> provideNewLibrary(@RequestParam String name) {
        return ResponseEntity.ok(libraryService.provideNewLibrary(name));
    }

    @GetMapping("/getAllLibraries")
    public ResponseEntity<List<Library>> getAllLibraries() {
        return ResponseEntity.ok(libraryService.getAllLibraries());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Library> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(libraryService.findById(id));
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable Integer id) {
        return ResponseEntity.ok(libraryService.existsById(id));
    }

    @GetMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Integer id) {
        libraryService.deleteById(id);
    }

    @GetMapping("/emptyLibraryWithName")
    public ResponseEntity<Library> getEmptyLibraryForName(@RequestParam String name) {
        return ResponseEntity.ok(libraryService.getEmptyLibraryForName(name));
    }

    @GetMapping("/selectByRating")
    public ResponseEntity<List<Library>> selectByRating() {
        return ResponseEntity.ok(libraryService.selectByRating());
    }

    @GetMapping("/updateIsAvailable")
    public ResponseEntity<Book> updateIsAvailable(@RequestParam Integer libId, @RequestParam Integer bId) {
        return ResponseEntity.ok(libraryService.updateIsAvailable(libId, bId));
    }

    @GetMapping("/ee")
    public String index() {
        return "Greetings m'lord";
    }
}
