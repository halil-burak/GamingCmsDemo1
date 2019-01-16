package com.hburak.cms.cmsdemo1;

import com.hburak.cms.cmsdemo1.controller.CategoryController;
import com.hburak.cms.cmsdemo1.controller.GameController;
import com.hburak.cms.cmsdemo1.entity.Category;
import com.hburak.cms.cmsdemo1.entity.Game;
import com.hburak.cms.cmsdemo1.repo.CategoryRepository;
import com.hburak.cms.cmsdemo1.repo.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Cmsdemo1ApplicationTests {

	@InjectMocks
	GameController gc;

	@InjectMocks
	CategoryController cc;

	@Mock
	private GameRepository gr;

	@Mock
	private CategoryRepository cr;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetGameById() {
		Game game1 = new Game();
		game1.setId(123L);
		when(gr.getOne(123L)).thenReturn(game1);
	}

	@Test
	public void testGetGames() {
		List<Game> games = null;
		games = gr.findAll();
		when(games).thenReturn(games);
	}

	@Test
	public void testGetCategoryById() {
		Category category1 = new Category();
		category1.setId(1L);
		when(cr.getOne(1L)).thenReturn(category1);
	}

	@Test
	public void testGetCategories() {
		List<Category> categories = null;
		categories = cr.findAll();
		when(categories).thenReturn(categories);
	}
}

