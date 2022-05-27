import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookServices } from 'src/app/books/book.services';
import { HomeServices } from 'src/app/home/home.service';
import { Book } from '../../book.model';
@Component({
  selector: 'app-adminbook',
  templateUrl: './adminbook.component.html',
  styleUrls: ['./adminbook.component.css'],
})
export class AdminbookComponent implements OnInit {
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
  editMode = false;
  isAdmin = false;
  constructor(private bookServices: BookServices, private homeService: HomeServices, private router: Router) { }
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
  ngOnInit(): void {
    this.getBooksData();
    this.fetchUserData()
  }

  getBooksData() {
    this.bookServices.getAllBooks().subscribe((res) => {
      console.log(res.bookList);
      this.allBooks = res.bookList;
    });
  }

  setEditMode(mode: boolean, i: number) {
    this.editMode = mode;
    if (mode == true) {
      this.tempBook = Object.assign({}, this.allBooks[i]);
    }
  }

  updateBook() {
    console.log(this.tempBook, "ss")
    this.bookServices.updateBook(this.tempBook).subscribe((res) => {
      this.getBooksData();
      console.log(res, "updatebook")
      this.editMode = false
      alert(`The Book ${this.tempBook.title} with id ${this.tempBook.id} updated successfuly`);
    });;
  }

  deteteBook(id: string | null) {
    this.bookServices.deleteBook(id).subscribe((res) => {
      this.getBooksData();
      console.log(res)
    });
  }

  fetchUserData() {
    this.homeService.fetchUser().subscribe((res) => {
      console.log(res, "admin"), this.validateAdmin(res)
    });
  }

  validateAdmin(input: any) {
    if (input.user.role == "admin") {
      this.isAdmin = true
      alert(`Welcome Admin ${input.user.fullName}, currently you are at admin page`)
    } else {
      this.router.navigateByUrl('/books')
      alert(`Hii ${input.user.fullName} You are not admin`)
    }
  }
}
