package com.java.restAPIDemo.service;

import static org.hamcrest.CoreMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.java.restAPIDemo.bean.Item;
import com.java.restAPIDemo.repository.RestAPIRepository;

public class RestAPIDemoTest {

	private ServiceDemoImpl service;

	@Mock
	private RestAPIRepository repository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		service = new ServiceDemoImpl();
		ReflectionTestUtils.setField(service, "repository", repository);
	}

	@Test
	public void testCreate() {
		Mockito.when(repository.save(Mockito.any())).thenReturn(1);
		Integer i = service.save(new Item("Title 1", "Description 1", true));
		Assert.assertNotNull(i);
		Assert.assertEquals(Integer.valueOf(1), i);
	}
	
	@Test
	public void testCreateFailed() {
		Mockito.when(repository.save(Mockito.any())).thenReturn(0);
		Integer i = service.save(new Item("Title 1", "Description 1", true));
		Assert.assertNotNull(i);
		Assert.assertEquals(Integer.valueOf(0), i);
	}

	@Test
	public void testFindAll() {
		Mockito.when(repository.findAll()).thenReturn(getItemsList());
		List<Item> i = service.findAll();
		Assert.assertNotNull(i);
		Assert.assertEquals(3, i.size());
	}
	
	@Test
	public void testFindbyId() {
		Mockito.when(repository.findById(1l)).thenReturn(getItemsList().get(0));
		Item i = service.findById(1l);
		Assert.assertNotNull(i);
	}
	
	@Test
	public void testUpdate() {
		Mockito.when(repository.update(Mockito.any())).thenReturn(1);
		Mockito.when(repository.findById(1l)).thenReturn(getItemsList().get(0));
		Integer i = service.update(new Item(1l,"Title 1", "Description 1", true));
		Assert.assertNotNull(i);
		Assert.assertEquals(Integer.valueOf(1), i);
	}

	@Test
	public void testDeleteById() {
		Mockito.when(repository.deleteById(1l)).thenReturn(1);
		Integer i = service.deleteById(1l);
		Assert.assertNotNull(i);
		Assert.assertEquals(Integer.valueOf(1), i);
	}

	@Test
	public void testDeleteuAll() {
		Mockito.when(repository.deleteAll()).thenReturn(1);
		Integer i = service.deleteAll();
		Assert.assertNotNull(i);
		Assert.assertEquals(Integer.valueOf(1), i);
	}

	private List<Item> getItemsList() {
		List<Item> items = new ArrayList<>();
		Item item1 = new Item("test1","test",true);
		Item item2 = new Item("test2","test",true);
		Item item3 = new Item("test3","test",true);
		items.add(item1);
		items.add(item2);
		items.add(item3);
		return items;
	}
	
	

}
