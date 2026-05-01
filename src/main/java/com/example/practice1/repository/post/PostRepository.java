package com.example.practice1.repository.post;

import com.example.practice1.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {

    public PostEntity save(PostEntity entity);
    public List<PostEntity> findAll();
    public Optional<PostEntity> findById(Long id);
    public void deleteById(Long id);

}
