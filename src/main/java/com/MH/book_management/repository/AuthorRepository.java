package com.MH.book_management.repository;

import com.MH.book_management.model.Author;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AuthorRepository {
    private final Map<Integer, Author> store = new LinkedHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(0);

    public List<Author> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Author> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    public Author save(Author author) {
        if (author.getId() == null) {
            author.setId(seq.incrementAndGet());
        }
        store.put(author.getId(), author);

        return author;
    }

    public Author update(Integer id, Author updateAuthor){
        if(!store.containsKey(id)) {
         throw new NoSuchElementException(id +"의 저자가 없습니다");
        }

        updateAuthor.setId(id);
        store.put(id, updateAuthor);

        return updateAuthor;
    }

    public void delete(Integer id) {
        store.remove(id);
    }
}