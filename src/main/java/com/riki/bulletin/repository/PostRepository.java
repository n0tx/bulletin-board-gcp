package com.riki.bulletin.repository;

import com.riki.bulletin.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	@Query("SELECT p FROM Post p WHERE (p.active = true) and ((:title is null or p.title LIKE :title) or (:author is null or p.author LIKE :author))")
	public Page<Post> search(@Param("title") String title, @Param("author") String author, Pageable pageable);

	public Optional<Post> findByIdAndActiveTrue(Long id);

}
