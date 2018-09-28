package com.example.shoprecommendertest.domain;

import java.util.List;

import org.springframework.data.domain.PageImpl;

public class StringPage extends PageImpl<String> {

	private static final long serialVersionUID = 3248189030448292002L;
	
    public StringPage(List<String> content) {
        super(content);
    }
}
