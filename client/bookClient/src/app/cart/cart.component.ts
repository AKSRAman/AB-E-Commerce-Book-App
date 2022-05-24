import { Component, OnInit } from '@angular/core';
import { Book } from '../book.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  singleBook:Book={
    addedOn: "",
      authors: "bobby",
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

}
