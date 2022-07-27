package com.assessment.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.library.exception.ResourceNotFoundException;
import com.assessment.library.model.Book;
import com.assessment.library.repository.BookRepository;

@Service
public class LibraryBookService {

	@Autowired
	BookRepository bookRepo;

	public List<Book> findAllBooks() {
		List<Book> list = bookRepo.findAll();
		return list;
	}

	public Book findBookById(Long id) throws ResourceNotFoundException {
		Book book = bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found for ID: " + id));
		return book;

	}

	public Book createBook(Book book) {
		Book savedBook = bookRepo.save(book);
		return savedBook;
	}

	public Book updateBook(Long id, Book bookDetails) throws ResourceNotFoundException {

		Book book = bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found for ID: " + id));

		book.setAuthor(bookDetails.getAuthor());
		book.setGenre(bookDetails.getGenre());
		book.setIsbn(bookDetails.getIsbn());
		book.setPublishDate(bookDetails.getPublishDate());
		book.setTitle(bookDetails.getTitle());

		Book updatedBook = bookRepo.save(book);
		return updatedBook;

	}

	public Boolean deleteBookById(Long id) throws ResourceNotFoundException {

		bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found for ID: " + id));
		bookRepo.deleteById(id);
		return true;

	}

}
