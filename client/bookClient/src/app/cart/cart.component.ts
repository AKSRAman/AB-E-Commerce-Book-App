import { Component, OnInit } from '@angular/core';
import { Book } from '../book.model';
import { BookServices } from '../books/book.services';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(  private bookService: BookServices) { }

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

  singleBook:Book={
    addedOn: "",
      author: "bobby",
      category: "history",
      description: "my book",
      id: "",
      imageUrl: "fwbgrehntjmyfhghdtnfj",
      pages: 1011,
      price:11110,
      publishDate: "",
      rating: 0,
      title: "booby boiograpy",
      updatedOn: "fvsghdntj",
  };

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
