package com.msm.project.model;

import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification<T> {

	   public Specification<T> idEqual(Integer id) {

	       return id == null ? null : (root, query, builder) -> {
	           return builder.equal(root.get("id"), id);
	       };
	   }
}
