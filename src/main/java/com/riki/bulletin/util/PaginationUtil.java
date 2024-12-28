package com.riki.bulletin.util;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.riki.bulletin.model.pagination.Paging;

public class PaginationUtil {

	public static <T> Paging<T> createResultPagingDTO(List<T> posts, Long totalElement, Integer pages, Integer page) {
		Paging<T> result = new Paging<T>();
		result.setPages(pages);
		result.setPage(page);
		result.setElements(totalElement);
		result.setResults(posts);
		return result;
	}
	
	public static Sort.Direction getSortBy(String sortBy) {
		if (sortBy.equalsIgnoreCase("asc")) {
			return Sort.Direction.ASC;
		} else {
			return Sort.Direction.DESC;
		}
	}
	
}
