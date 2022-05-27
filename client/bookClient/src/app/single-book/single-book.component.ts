import { Component, OnInit } from '@angular/core';
import { Book } from '../book.model';

@Component({
  selector: 'app-single-book',
  templateUrl: './single-book.component.html',
  styleUrls: ['./single-book.component.css']
})
export class SingleBookComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  
  book: Book = {
    title: "Chanakya in You",
    description: "This book is for whom who looking for an inspirational story through an Indian prospective. To be honest I personally like this book and it was the first book that I was red.",
    author: "Pillai Radhakrishnan",
    category: "Business & Economics",
    imageUrl: "https://rukminim2.flixcart.com/image/832/832/kit6hzk0-0/book/l/4/l/chanakya-in-you-original-imafygxgvgwzquq3.jpeg?q=70",
    pages: 256,
    price: 178,
    publishDate: "25/05/2022",
    addedOn: null,
    id: null,
    rating: 0,
    updatedOn: null
  }

}
