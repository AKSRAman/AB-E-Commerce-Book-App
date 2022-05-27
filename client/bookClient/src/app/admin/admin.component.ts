import { Component, OnInit } from '@angular/core';
import { Book } from '../book.model';
import { BookServices } from '../books/book.services';
//import {CookieService} from 'angular2-cookie/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  
  constructor(private bookService: BookServices) { }
  toggle:boolean=true;
  allBooks: Book[] = [{
    addedOn: "",
    author: "",
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

  onClick(toggle:boolean){
    this.toggle=toggle

  }
  ngOnInit(): void {
    this.getBooksData
  }

  getBooksData() {
    this.bookService.getAllBooks().subscribe((res) => {
      console.log(res.bookList);
      this.allBooks = res.bookList;
    });

  }

  handleEdit(book: Book) {
    console.log(book, "edit")
  }

  deleteBook(book: Book) {
    console.log(book, "delete")
  }
}
