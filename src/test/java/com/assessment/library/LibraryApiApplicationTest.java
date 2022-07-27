package com.assessment.library;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.assessment.library.controller.LibraryBookController;
import com.assessment.library.model.Book;
import com.assessment.library.service.LibraryBookService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(value = LibraryBookController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class LibraryApiApplicationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private LibraryBookService service; 	

	@Test
	public void givenBooks_whenGetAllBooks_thenReturnJsonArray()
	  throws Exception {
	    
		Book book1 = new Book(1L, "NEW MOON", "STEPHENIE MEYER", "1-7965-9846-0", "FANTASY", new Date());
		Book book2 = new Book(2L, "TWILIGHT", "STEPHENIE MEYER", "1-7895-2346-5", "YOUNG ADULT", new Date());
		Book book3 = new Book(3L, "THE HOBBIT", "JRR TOLKIEN", "5-7895-4639-5", "FANTASY", new Date());
		
	    List<Book> allBooks = Arrays.asList(book1, book2, book3);

	    given(service.findAllBooks()).willReturn(allBooks);

	    mvc.perform(get("/api/v1/books/all")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$", hasSize(3)))
	      .andExpect(jsonPath("$[0].title", is(book1.getTitle())));
	}
	
	@Test
	public void givenBooks_whenGetBookByID_thenReturnJsonArray()
	  throws Exception {
	    
		Book book1 = new Book(1L, "NEW MOON", "STEPHENIE MEYER", "1-7965-9846-0", "FANTASY", new Date());
		Book book2 = new Book(2L, "TWILIGHT", "STEPHENIE MEYER", "1-7895-2346-5", "YOUNG ADULT", new Date());
		Book book3 = new Book(3L, "THE HOBBIT", "JRR TOLKIEN", "5-7895-4639-5", "FANTASY", new Date());
		
	    List<Book> allBooks = Arrays.asList(book1, book2, book3);

	    given(service.findBookById(2L)).willReturn(book2);

	    mvc.perform(get("/api/v1/books/2")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.title", is(book2.getTitle())));
	}
	
	@Test
	public void whenPostBook_thenCreateBook() throws Exception {
		Book book = new Book(1L, "NEW MOON", "STEPHENIE MEYER", "1-7965-9846-0", "FANTASY", new Date());

		given(service.createBook(Mockito.any())).willReturn(book);

		mvc.perform(post("/api/v1/books")
				.contentType(MediaType.APPLICATION_JSON).content(toJson(book)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.title", is(book.getTitle())));

	}
	
	@Test
	public void whenPutBook_thenUpdateBook() throws Exception {
		Book book = new Book(1L, "NEW MOON", "STEPHENIE MEYER", "1-7965-9846-0", "FANTASY", new Date());

		given(service.updateBook(Mockito.any(), Mockito.any())).willReturn(book);

		mvc.perform(put("/api/v1/books/1")
				.contentType(MediaType.APPLICATION_JSON).content(toJson(book)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.title", is(book.getTitle())));

	}
	
	@Test
	public void whenDeleteBook_thenDeleteBook() throws Exception {
		Book book = new Book(1L, "NEW MOON", "STEPHENIE MEYER", "1-7965-9846-0", "FANTASY", new Date());

		given(service.deleteBookById(Mockito.any())).willReturn(null);

		mvc.perform(delete("/api/v1/books/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}
	
    static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }

}

