package com.choirool.mysqlcrud.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.choirool.mysqlcrud.dao.LibraryDao;
import com.choirool.mysqlcrud.model.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryDao libraryDao;

    @GetMapping("/index")
    public ResponseEntity<Object> index() {
        try {
            Iterable<Library> libraries = libraryDao.findAll();
            return ResponseEntity.ok().body(Map.of("status", true, "message", "Ok", "data", libraries));
        } catch (Exception e) {
            return ResponseEntity.ok().body(Map.of("status", false, "message", e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody List<Library> libraries) {
        try {
            libraryDao.saveAll(libraries);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("status", true, "message", "Ok", "data", libraries));
        } catch (Exception e) {
            return ResponseEntity.ok().body(Map.of("status", false, "message", e.getMessage()));
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        Optional<Library> library = libraryDao.findById(id);

        if (library.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", true, "message", "Ok", "data", library));
        }

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", false, "message", "No data found"));
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Library libraryRequest) {
        try {
            Library library = libraryDao.findById(id).get();

            if (library != null) {
                library.setName(libraryRequest.getName());
                libraryDao.save(library);

                return ResponseEntity.status(HttpStatus.OK)
                        .body(Map.of("status", true, "message", "Ok", "data", library));
            }

            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", false, "message", e.getMessage()));
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            libraryDao.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", false, "message", e.getMessage()));
        }
    }
}
