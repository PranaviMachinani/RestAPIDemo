package com.java.restAPIDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.restAPIDemo.bean.Item;
import com.java.restAPIDemo.service.ServiceDemo;

import io.swagger.annotations.Api;

@Api(tags = "RestAPI demo APIs")
@RestController
@RequestMapping(value = "/demo")
public class RestAPIController {
	@Autowired
	ServiceDemo service;

	@GetMapping("/findAll")
	public ResponseEntity<List<Item>> getAllItems() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/Items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable("id") long id) {
		Item Item = service.findById(id);
		if (Item != null) {
			return new ResponseEntity<>(Item, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Items")
	public ResponseEntity<String> createItem(@RequestBody Item Item) {
		try {
			service.save(new Item(Item.getTitle(), Item.getDescription(), false));
			return new ResponseEntity<>("Item was created successfully.", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Items/{id}")
	public ResponseEntity<String> updateItem(@PathVariable("id") long id, @RequestBody Item Item) {
		Integer _Item = service.update(Item);
		if (_Item != null) {
			return new ResponseEntity<>("Item was updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Cannot find Item with id=" + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Items/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable("id") long id) {
		try {
			int result = service.deleteById(id);
			if (result == 0) {
				return new ResponseEntity<>("Cannot find Item with id=" + id, HttpStatus.OK);
			}
			return new ResponseEntity<>("Item was deleted successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot delete Item.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Items")
	public ResponseEntity<String> deleteAllItems() {
		try {
			int numRows = service.deleteAll();
			return new ResponseEntity<>("Deleted " + numRows + " Item(s) successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot delete Items.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
