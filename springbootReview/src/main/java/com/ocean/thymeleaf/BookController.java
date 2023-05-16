package com.ocean.thymeleaf;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("thymeleaf")
public class BookController {

    @GetMapping("/books")
    public String books(Model model) {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book(1, "三国演义", "罗贯中");
        Book book2 = new Book(2, "红楼梦", "曹雪芹");
        books.add(book1);
        books.add(book2);
        model.addAttribute("books", books);
        System.out.println("-----------------------------");
        return "book";
    }

}
