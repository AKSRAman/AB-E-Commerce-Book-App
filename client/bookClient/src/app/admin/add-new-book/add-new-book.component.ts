import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { BookServices } from 'src/app/books/book.services';
import { Book } from '../../book.model';

@Component({
  selector: 'app-add-new-book',
  templateUrl: './add-new-book.component.html',
  styleUrls: ['./add-new-book.component.css'],
})
export class AddNewBookComponent implements OnInit {
  constructor(private bookServices: BookServices, private router: Router) {}

  ngOnInit(): void {}
  tempBook: Book = {
    addedOn: '',
    author: '',
    category: '',
    description: '',
    id:null,
    imageUrl: '',
    pages: 0,
    price: 0,
    publishDate: '',
    rating: 0,
    title: '',
    updatedOn: '',
  };

  addNewBook() {
    this.bookServices.addNewBook(this.tempBook).subscribe((res) => {
      console.log(res), this.bookServices.getBookDataPageWise(1);
    });
    this.router.navigate(['/admin/books']);
    window.location.reload();
  }
}
