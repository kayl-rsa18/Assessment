package com.assessment.library.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.assessment.library.exception.ResourceNotFoundException;
import com.assessment.library.model.Book;
import com.assessment.library.model.ErrorDetails;
import com.assessment.library.service.LibraryBookService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Library Book API")
public class LibraryBookController {

	@Autowired
	private LibraryBookService libraryService;

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful Operation"), 
			@ApiResponse(responseCode = "204", description = "No books found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
	})
	@Operation(description = "Get All Books", tags = "Books - Get ALL")
	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAllLibraryBooks() {

		List<Book> list = libraryService.findAllBooks();

		if (list.isEmpty() || list.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful Operation"), 
			@ApiResponse(responseCode = "404", description = "No books found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
	})
	@Operation(description = "Get Book by ID", tags = "Books - Get by ID")
	@GetMapping("/{id}")
	public ResponseEntity<Book> getLibraryBook(@PathVariable Long id) throws ResourceNotFoundException {

		Book book = libraryService.findBookById(id);
		return new ResponseEntity<>(book, HttpStatus.OK);

	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Book created successfully"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
	})
	@Operation(description = "Create a new Book", tags = "Books - Create Book")
	@PostMapping(name = "/")
	public Book createBook(@Valid @RequestBody Book bookDetail) {

		return libraryService.createBook(bookDetail);

	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Book updated successfully"), 
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))), 
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
	})
	@Operation(description = "Update Book by ID", tags = "Books - Update Book")
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long id, @Valid @RequestBody Book bookDetail) throws ResourceNotFoundException {

		Book updatedBook = libraryService.updateBook(id, bookDetail);
		return ResponseEntity.ok(updatedBook);

	}


	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Book deleted successfully", content = @Content), 
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@Operation(description = "Delete Book by ID", tags = "Books - Delete Book")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBook(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {

		libraryService.deleteBookById(id);
		return ResponseEntity.noContent().build();

	}



}
