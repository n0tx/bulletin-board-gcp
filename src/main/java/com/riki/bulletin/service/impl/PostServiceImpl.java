package com.riki.bulletin.service.impl;

import com.riki.bulletin.form.PostForm;
import com.riki.bulletin.model.Post;
import com.riki.bulletin.model.pagination.Paging;
import com.riki.bulletin.repository.PostRepository;
import com.riki.bulletin.service.PostService;
import com.riki.bulletin.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Paging<Post> findPostAll(Integer page, Integer limit, String sortBy,
										  String direction, String search) {


		String byTitle = ObjectUtils.isEmpty(search) ? "%%" : "%" + search + "%";
		String byAuthor = ObjectUtils.isEmpty(search) ? "%%" : "%" + search + "%";

		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(page, limit, sort);

		Page<Post>  pageResult = postRepository.search(byTitle, byAuthor, pageable);

		List<Post > posts = pageResult.stream().map((p)-> {
			Post post =  new Post();
			post.setId(p.getId());
			post.setTitle(p.getTitle());
			post.setContent(p.getContent());
			post.setAuthor(p.getAuthor());
			post.setViewers(p.getViewers());
			post.setCreatedAt(p.getCreatedAt());
			post.setUpdatedAt(p.getUpdatedAt());
			return post;
		}).collect(Collectors.toList());

		return PaginationUtil.createResultPagingDTO(posts, pageResult.getTotalElements(), pageResult.getTotalPages(), page);
	}

	@Override
	public Post createNewPost(PostForm postForm) {
		Post post = new Post();
		post.setTitle(postForm.getTitle());
		post.setAuthor(postForm.getAuthor());
		post.setPassword(passwordEncoder.encode(postForm.getPassword()));
		post.setContent(postForm.getContent());
		post.setActive(true);
		post.setCreatedAt(now());
		post.setUpdatedAt(now());
		return postRepository.save(post);
	}

	@Override
	public PostForm findPost(Long id) {
		PostForm postForm = new PostForm();
		Post post = postRepository.findByIdAndActiveTrue(id).orElseThrow(()->new IllegalArgumentException("Invalid Post Id: " + id));
		postForm.setId(id);
		postForm.setTitle(post.getTitle());
		postForm.setAuthor(post.getAuthor());
		postForm.setContent(post.getContent());
		return postForm;
	}

	@Override
	public void updatePost(Long id, PostForm postForm) {
		Post post = postRepository.findByIdAndActiveTrue(id).orElseThrow(()->new IllegalArgumentException("Invalid Post Id: " + id));
		if (isAuthenticated(postForm, post)) {
			post.setTitle(postForm.getTitle());
			post.setAuthor(postForm.getAuthor());
			post.setContent(postForm.getContent());
			post.setUpdatedAt(now());
			post.setViewers(post.getViewers() + 1);
			postRepository.save(post);
		} else {
			throw new RuntimeException("Incorrect password");
		}
	}

	@Override
	public Post detailsPost(Long id) {
		Post detailsPost = postRepository.findByIdAndActiveTrue(id).orElseThrow(()->new IllegalArgumentException("Invalid Post Id: " + id));
		detailsPost.setViewers(detailsPost.getViewers() + 1);
		return postRepository.save(detailsPost);
	}

	@Override
	public void deletePost(Long id, PostForm postForm) {
		Post post = postRepository.findByIdAndActiveTrue(id).orElseThrow(
				()->new IllegalArgumentException("Invalid Post Id: " + id));
		if (isAuthenticated(postForm, post)) {
			post.setActive(Boolean.FALSE);
			postRepository.save(post);
		} else {
			throw new RuntimeException("Incorrect password");
		}

	}

	public boolean isAuthenticated(PostForm postForm,Post post) {
		return passwordEncoder.matches(postForm.getPassword(), post.getPassword());
	}


}
