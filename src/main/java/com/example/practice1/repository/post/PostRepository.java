package com.example.practice1.repository.post;

import com.example.practice1.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepository {

    private static final Map<Long, PostEntity> postStore = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public PostEntity save(PostEntity entity) {
        if (entity.getId() == null) {
            entity.assignId(++sequence);
        }

        postStore.put(entity.getId(), entity);
        return entity;
    }

    public List<PostEntity> findAll() {
        return new ArrayList<>(postStore.values());
    }

    public Optional<PostEntity> findById(Long id) {
        return Optional.ofNullable(postStore.get(id));
    }

    public void delete(PostEntity post) {
        postStore.remove(post.getId());
    }

    public void deleteById(Long id) {
        postStore.remove(id);
    }
}