package com.java.restAPIDemo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.java.restAPIDemo.bean.Item;
import com.java.restAPIDemo.repository.RestAPIRepository;

@Service
public class ServiceDemoImpl implements ServiceDemo {

	@Autowired
	private RestAPIRepository repository;

	@Override
	public List<Item> findAll() {
		try {
			List<Item> items = new ArrayList<Item>();
			repository.findAll().forEach(items::add);
			return items;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Item findById(Long id) {
		Item tutorial = repository.findById(id);
		return tutorial;
	}

	@Override
	public Integer save(Item item) {
		return repository.save(new Item(item.getTitle(), item.getDescription(), false));
	}

	@Override
	public Integer update(Item item) {
		Item _item = repository.findById(item.getId());
		if (_item != null) {
			_item.setId(item.getId());
			_item.setTitle(item.getTitle());
			_item.setDescription(item.getDescription());
			_item.setPublished(item.isPublished());
			return repository.update(_item);
		} else {
			return null;
		}
	}

	@Override
	public int deleteById(Long id) {
		return repository.deleteById(id);
	}

	@Override
	public int deleteAll() {
		return repository.deleteAll();
	}

}