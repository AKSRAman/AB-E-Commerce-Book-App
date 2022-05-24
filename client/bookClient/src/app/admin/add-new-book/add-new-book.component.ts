import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BookServices } from 'src/app/books/book.services';
import { Book } from '../../book.model';

@Component({
  selector: 'app-add-new-book',
  templateUrl: './add-new-book.component.html',
  styleUrls: ['./add-new-book.component.css']
})
export class AddNewBookComponent implements OnInit {

  constructor(private bookServices:BookServices) { }

  ngOnInit(): void {
  }
  tempBook:Book={
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
  }

  addNewBook(form:NgForm){
    this.tempBook=form.value;
  
 this.bookServices.addNewBook(this.tempBook);
  }
}
