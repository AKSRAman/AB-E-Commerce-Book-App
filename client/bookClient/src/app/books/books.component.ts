import { Component, OnInit } from '@angular/core';
import { Book } from '../book.model';

import { BookServices } from './book.services';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})

export class BooksComponent implements OnInit {
  allBooks: Book[] = [{
    addedOn: "",
    authors: "",
    category: "",
    description: "",
    id: "",
    imageUrl: "",
    pages: 0,
    price: 0,
    publishDate: "",
    rating: 0,
    title: "",
    updatedOn: "",
  },];
  constructor(  private bookService: BookServices) { }

  ngOnInit(): void {
    this.getBooksData();
  }

  getBooksData() {
    this.bookService.getAllBooks().subscribe((res) => {
      console.log(res.bookList);
      this.allBooks=res.bookList;

    });

  }
}
