package com.s22617.library.Repositores;

import com.s22617.library.Models.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

    @Query("SELECT l FROM Library l WHERE l.rating > 60")
    List<Library> selectByRating();
}
