import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookServices } from 'src/app/books/book.services';
import { HomeServices } from 'src/app/home/home.service';
import Swal from 'sweetalert2';
import { Book } from '../../book.model';
@Component({
  selector: 'app-adminbook',
  templateUrl: './adminbook.component.html',
  styleUrls: ['./adminbook.component.css'],
})
export class AdminbookComponent implements OnInit {
  editMode = false;
  isAdmin = false;
  currentPage: number = 0;
  lastPage: number = 0;
  allBooks: Book[] = [
    {
      addedOn: '',
      author: '',
      category: '',
      description: '',
      id: '',
      imageUrl: '',
      pages: 0,
      price: 0,
      publishDate: '',
      rating: 0,
      title: '',
      updatedOn: '',
    },
  ];

  tempBook: Book = {
    addedOn: '',
    author: '',
    category: '',
    description: '',
    id: '',
    imageUrl: '',
    pages: 0,
    price: 0,
    publishDate: '',
    rating: 0,
    title: '',
    updatedOn: '',
  };

  constructor(private bookServices: BookServices) { }

  ngOnInit(): void {
    this.getBookDataPageWise();
  }

  getBookDataPageWise() {
    this.bookServices.getBookDataPageWise(this.currentPage).subscribe((res) => {
      console.log(res);
      this.allBooks = res.bookList;
      //this.currentPage = res.currentPage;
      this.lastPage=res.totalPages;
    });
  }

  onNext(){
    this.currentPage++;
    if(this.currentPage<this.lastPage){
    this.getBookDataPageWise();
    }
  }
  onPrev(){
    this.currentPage--;
    this.getBookDataPageWise();
  }
  setEditMode(mode: boolean, i: number) {
    this.editMode = mode;
    if (mode == true) {
      this.tempBook = Object.assign({}, this.allBooks[i]);
    }
  }

  updateBook() {
    Swal.fire({
      title: 'Do you want to save the changes?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: 'Save',
      denyButtonText: `Don't save`,
    }).then((result) => {
      if (result.isConfirmed) {
        this.bookServices.updateBook(this.tempBook).subscribe((res) => {
          this.getBookDataPageWise();
          console.log(res, "updatebook")
          this.editMode = false
          Swal.fire('Saved!', '', 'success')
        });;
      } else if (result.isDenied) {
        Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }

  deteteBook(id: string | null) {
    Swal.fire({
      title: 'Are you sure to delete this book ?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.bookServices.deleteBook(id).subscribe((res) => {
          this.getBookDataPageWise();
          console.log(res)
          Swal.fire(
            'Deleted!',
            'This book is deleted successfully',
            'success'
          )
        });
      }
    })
  }
}
