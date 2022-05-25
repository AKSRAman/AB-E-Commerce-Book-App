import { Component, OnInit } from '@angular/core';
import { Book } from '../book.model';

import { BookServices } from './book.services';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})

export class BooksComponent implements OnInit {
  imgSrc:string="	https://rukminim2.flixcart.com/flap/3600/3600/image/b3fe381767050079.jpg?q=80"
  images:string[] = ["https://rukminim2.flixcart.com/flap/3600/3600/image/b3fe381767050079.jpg?q=80",
  "https://rukminim2.flixcart.com/flap/3600/3600/image/539e571efc670481.jpg?q=80", 
  "https://rukminim2.flixcart.com/flap/3600/3600/image/b6bc3ed0ef0b6fef.jpg?q=80", 
  "https://rukminim2.flixcart.com/flap/3600/3600/image/fe161b55d4ef38c2.jpg?q=80", 
  "https://rukminim2.flixcart.com/flap/3600/3600/image/0f727f5598c4b21a.jpg?q=80", 
  "https://rukminim2.flixcart.com/flap/3600/3600/image/b463578cd69b58e4.png?q=80",
  "https://rukminim2.flixcart.com/flap/3600/3600/image/7f5e1a6d7cf3e1eb.jpg?q=80",
 ]
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
  
  constructor(  private bookService: BookServices) { }

  ngOnInit(): void {
    this.getBooksData();
    this.startSlideshow();
  }

  getBooksData() {
    this.bookService.getAllBooks().subscribe((res) => {
      console.log(res.bookList);
      this.allBooks=res.bookList;

    });

    startSlideshow() {
      this.imgSrc = this.images[5];
      let counter = 0;
      setInterval(() => {
          if (counter == this.images.length) {
              counter = 0;
          }
          this.imgSrc = this.images[counter]
          counter++;
      }, 2000)
  }
  



  }

  

