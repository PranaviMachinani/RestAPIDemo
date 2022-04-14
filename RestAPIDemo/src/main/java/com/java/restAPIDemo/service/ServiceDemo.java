package com.java.restAPIDemo.service;

import java.util.List;
import com.java.restAPIDemo.bean.Item;

public interface ServiceDemo {
	public Integer save(Item book);

	public Integer update(Item book);

	public Item findById(Long id);

	public int deleteById(Long id);

	public List<Item> findAll();

	public int deleteAll();
	
}
