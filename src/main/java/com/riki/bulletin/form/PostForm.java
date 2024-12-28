package com.riki.bulletin.form;

import com.riki.bulletin.form.annotation.OnCreate;
import com.riki.bulletin.form.annotation.OnUpdate;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PostForm {
	
	private Long id;
	
	@NotBlank(message = "title must not blank", groups = {OnCreate.class, OnUpdate.class})
	@Size(max = 100, message = "name size too long, max 100 letters", groups = {OnCreate.class, OnUpdate.class})
	private String title;

	@NotBlank(message = "author must not blank", groups = {OnCreate.class, OnUpdate.class})
	@Size(max = 10, message = "name size too long, max 10 letters", groups = {OnCreate.class, OnUpdate.class})
	private String author;

	@NotBlank(message = "password must not blank", groups = {OnCreate.class, OnUpdate.class})
	@Size(min = 6, message = "password size too short, min 6 letters", groups = {OnCreate.class, OnUpdate.class})
	private String password;

	@NotBlank(message = "content must not blank", groups = {OnCreate.class, OnUpdate.class})
	@Size(min = 2, message = "content size too short, min 2 letters", groups = {OnCreate.class, OnUpdate.class})
	@Size(max = 200, message = "content size too long, max 200 letters", groups = {OnCreate.class, OnUpdate.class})
	private String content;

}
