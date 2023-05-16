package com.ocean.freemaker;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("freemarker")
public class BooksController {

    @GetMapping("/book")
    public String books(HttpServletRequest request) {
        request.setAttribute("flag", true);
        request.setAttribute("createDate", new Date());
        request.setAttribute("age",18); // 数值型
        request.setAttribute("salary",10000); // 数值型
        request.setAttribute("avg",0.545); // 浮点型

        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "三国演义", "罗贯中"));
        books.add(new Book(2, "红楼梦", "曹雪芹"));
        request.setAttribute("books", books);


        HashMap<String, String> map = new HashMap<>();
        map.put("first", "1");
        map.put("second", "2");
        request.setAttribute("map", map);
        return "books";
    }
}
