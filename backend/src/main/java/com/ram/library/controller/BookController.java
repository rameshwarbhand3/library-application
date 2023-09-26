package com.ram.library.controller;

import com.ram.library.entity.Book;
import com.ram.library.service.BookService;
import com.ram.library.utils.ExtractJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;


    @GetMapping("/secure/currentLoans/count")
    public int currentLoanCount(@RequestHeader(value = "Authorization") String token) {
        String userEmail = ExtractJwt.payLoadJwtExtraction(token, "\"sub\"");
        return bookService.currentLoanCount(userEmail);
    }

    @GetMapping("/secure/checkout")
    public Boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId) {
        String userEmail = ExtractJwt.payLoadJwtExtraction(token, "\"sub\"");
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @PutMapping("/secure/checkout")
    public Book checkout(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJwt.payLoadJwtExtraction(token, "\"sub\"");
        return bookService.checkoutBook(userEmail, bookId);
    }


}