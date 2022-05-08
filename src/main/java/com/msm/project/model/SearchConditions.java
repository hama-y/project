package com.msm.project.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SearchConditions {

    @Autowired
    private ProjectRepository Repository;

    
    public List<SearchEntity> getSearchReservations(SearchModel target) {
  	
    	SearchSpecification<SearchEntity> spec = new SearchSpecification<>();
//複数検索の項目を羅列する
//数値の項目はInteger型にキャスト(空白で検索された時にnullとするため)
        return Repository.findAll(
        		Specification.where(spec.idEqual((Integer)target.getId())));
    }

}
