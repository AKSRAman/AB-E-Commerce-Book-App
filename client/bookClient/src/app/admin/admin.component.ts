import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Book } from '../book.model';
import { BookServices } from '../books/book.services';
import { HomeServices } from '../home/home.service';
//import {CookieService} from 'angular2-cookie/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  isAdmin: boolean = false

  constructor(private bookService: BookServices, private homeService: HomeServices, private router: Router) { }
  toggle: boolean = true;
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

  onClick(toggle: boolean) {
    this.toggle = toggle

  }
  ngOnInit(): void {
    this.getBooksData
    this.fetchUserData()
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

  fetchUserData() {
    let token: any = localStorage.getItem("jwtToken");
    token = JSON.parse(token);
    if (!token) {
      Swal.fire("Please login to proceed", "We are very sorry for the inconvinience", "error");
      this.router.navigateByUrl('/auth')
      return console.log("token is not present checked by cart")
    }
    this.homeService.fetchUser().subscribe((res: any) => {
      console.log(res, "admin"), this.validateAdmin(res)
    });
  }

  validateAdmin(input: any) {
    if (input.user.role == "admin") {
      this.isAdmin = true
      Swal.fire({
        icon: 'success',
        title: `Welcome to Admin dashboard ${input.user.fullName}`,
        timer: 1500
      })
    } else {
      this.router.navigateByUrl('/books')
      Swal.fire(`Hii ${input.user.fullName} You are not admin`, "", "error");
    }
  }
}
