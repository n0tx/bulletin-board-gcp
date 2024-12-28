package com.riki.bulletin.controller;

import com.riki.bulletin.form.PostForm;
import com.riki.bulletin.form.annotation.OnCreate;
import com.riki.bulletin.form.annotation.OnUpdate;
import com.riki.bulletin.model.Post;
import com.riki.bulletin.model.pagination.Paging;
import com.riki.bulletin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Validated
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/")
	public String findPosts(
			@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
			@RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(value = "sortBy", required = true, defaultValue = "id") String sortBy,
			@RequestParam(value = "direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(value = "search", required = false) String search,
			Model model) {
		Paging<Post> posts = postService.findPostAll(page, limit, sortBy, direction, search);
		model.addAttribute("posts", posts);
		return "post/post-list";
	}

	@GetMapping("/post/new")
	public String loadPostForm(Model model) {
		model.addAttribute("postForm", new PostForm());
		return "post/post-create";
	}

	@PostMapping("/post/new")
	public String addNewPost(@ModelAttribute("postForm") @Validated(OnCreate.class) PostForm postForm,
								BindingResult bindingResult,
								Errors errors,
								Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("postForm", postForm);
			return "post/post-create";
		}
		postService.createNewPost(postForm);
		return "redirect:/";
	}

	@GetMapping("/post/update/{id}")
	public String loadUpdatePostForm(@PathVariable("id") Long id, Model model) {
		PostForm postForm = postService.findPost(id);
		postForm.setPassword("");
		model.addAttribute("postForm", postForm);
		return "post/post-update";
	}

	@PutMapping("/post/update/{id}")
	public String updatePost(@PathVariable("id") Long id,
								@ModelAttribute("postForm") @Validated(OnUpdate.class) PostForm postForm,
								BindingResult bindingResult,
								Errors errors,
								Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("postForm", postForm);
			return "post/post-update";
		}

		try {
			postService.updatePost(id, postForm);
			return "redirect:/";
		} catch (RuntimeException ex) {
			model.addAttribute("postForm", postForm);
			model.addAttribute("incorrectPassword", ex.getMessage());
			return "post/post-update";
		}
	}

	@PatchMapping("/post/detail/{id}")
	public String detailsPost(@PathVariable("id") Long id, Model model) {
		Post detailsPost = postService.detailsPost(id);
		model.addAttribute("detailsPost", detailsPost);
		return "post/post-detail";
	}

	@GetMapping("/post/delete/{id}")
	public String loadDeletePostForm(@PathVariable("id") Long id, Model model) {
		PostForm postForm = postService.findPost(id);
		postForm.setPassword("");
		model.addAttribute("postForm", postForm);
		return "post/post-delete";
	}

	@DeleteMapping("/post/delete/{id}")
	public String deletePost(@PathVariable("id") Long id,
			@ModelAttribute("postForm") PostForm postForm, Model model) {
		try {
			postService.deletePost(id, postForm);
			return "redirect:/";
		} catch (RuntimeException ex) {
			model.addAttribute("postForm", postForm);
			model.addAttribute("incorrectPassword", ex.getMessage());
			return "post/post-delete";
		}
	}
}


		
		
		
		
		