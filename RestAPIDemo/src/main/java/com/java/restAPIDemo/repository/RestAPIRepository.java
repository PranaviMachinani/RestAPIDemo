package com.java.restAPIDemo.repository;

import java.util.List;

import com.java.restAPIDemo.bean.Item;

public interface RestAPIRepository {
	int save(Item book);

	int update(Item book);

	Item findById(Long id);

	int deleteById(Long id);

	List<Item> findAll();

	List<Item> findByPublished(boolean published);

	List<Item> findByTitleContaining(String title);

	int deleteAll();
}
