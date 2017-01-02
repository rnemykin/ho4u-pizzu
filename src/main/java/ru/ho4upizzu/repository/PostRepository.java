package ru.ho4upizzu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ho4upizzu.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer>{
}
