package com.riki.bulletin.service;

import com.riki.bulletin.form.PostForm;
import com.riki.bulletin.model.Post;
import com.riki.bulletin.model.pagination.Paging;

public interface PostService {

	Paging<Post> findPostAll(Integer page, Integer limit, String sortBy, String direction, String search);

	Post createNewPost(PostForm postForm);

	PostForm findPost(Long id);

	void updatePost(Long id, PostForm postForm);

	Post detailsPost(Long id);

	void deletePost(Long id, PostForm postForm);

}
