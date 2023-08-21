package com.jjb.myapp.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select * " +
            "from post " +
            "order by no asc", nativeQuery = true)
    List<Post> findPostSortByNo();
    @Query(value = "select * " +
            "from post " +
            "where no = :no", nativeQuery = true)
    Optional<Post> findPostByNo(Long no);
}
