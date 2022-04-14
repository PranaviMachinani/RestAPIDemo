package com.java.restAPIDemo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.java.restAPIDemo.bean.Item;

@Repository
public class RestAPIRepositoryImpl implements RestAPIRepository{
	@Autowired
	  private JdbcTemplate jdbcTemplate;
	
	  @Override
	  public int save(Item Item) {
	    return jdbcTemplate.update("INSERT INTO Items (title, description, published) VALUES(?,?,?)",
	        new Object[] { Item.getTitle(), Item.getDescription(), Item.isPublished() });
	  }

	  @Override
	  public int update(Item Item) {
	    return jdbcTemplate.update("UPDATE Items SET title=?, description=?, published=? WHERE id=?",
	        new Object[] { Item.getTitle(), Item.getDescription(), Item.isPublished(), Item.getId() });
	  }
	  
	  @Override
	  public Item findById(Long id) {
	    try {
	      Item Item = jdbcTemplate.queryForObject("SELECT * FROM Items WHERE id=?",
	          BeanPropertyRowMapper.newInstance(Item.class), id);
	      return Item;
	    } catch (IncorrectResultSizeDataAccessException e) {
	      return null;
	    }
	  }
	  
	  @Override
	  public int deleteById(Long id) {
	    return jdbcTemplate.update("DELETE FROM Items WHERE id=?", id);
	  }
	  
	  @Override
	  public List<Item> findAll() {
	    return jdbcTemplate.query("SELECT * from Items", BeanPropertyRowMapper.newInstance(Item.class));
	  }
	  
	  @Override
	  public List<Item> findByPublished(boolean published) {
	    return jdbcTemplate.query("SELECT * from Items WHERE published=?",
	        BeanPropertyRowMapper.newInstance(Item.class), published);
	  }
	  
	  @Override
	  public List<Item> findByTitleContaining(String title) {
	    String q = "SELECT * from Items WHERE title LIKE '%" + title + "%'";
	    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Item.class));
	  }
	  
	  @Override
	  public int deleteAll() {
	    return jdbcTemplate.update("DELETE from Items");
	  }
	
}
