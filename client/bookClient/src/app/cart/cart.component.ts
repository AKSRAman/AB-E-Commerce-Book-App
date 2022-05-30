import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Book } from '../book.model';
import { BookServices } from '../books/book.services';
import { HomeServices } from '../home/home.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})

export class CartComponent implements OnInit {

  constructor(private bookService: BookServices, private homeService: HomeServices, private http: HttpClient, private router: Router) { }

  totalPrice: number = 0

  allBooks: Book[] = [];

  date: String = new Date().toLocaleDateString();

  singleBook: Book = {
    addedOn: '',
    author: 'bobby',
    category: 'history',
    description: 'my book',
    id: '',
    imageUrl: 'fwbgrehntjmyfhghdtnfj',
    pages: 1011,
    price: 11110,
    publishDate: '',
    rating: 0,
    title: 'booby boiograpy',
    updatedOn: 'fvsghdntj',
  };

  userId: string = '';

  ngOnInit(): void {
    this.fetchUserData();
  }

  getBooksData() {
    this.bookService.getAllBooks().subscribe((res) => {
      console.log(res.bookList);
      this.allBooks = res.bookList;
    });
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
      this.assignValue(res);
    });
  }

  assignValue(res: any) {
    console.log(res, 'cart');
    this.allBooks = res.user.booksCart;
    this.userId = res.user.id;
    this.totalCalculator(this.allBooks);
  }

  removeItem(index: number) {
    let token: any = localStorage.getItem('jwtToken');
    token = JSON.parse(token);
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': 'I am coming from frontend',
        Authorization: `Bearer ${token}`,
      }),
    };
    this.http
      .delete(`http://localhost:8080/user/removeFromCart/${index}`, httpOptions)
      .subscribe({
        next: (response) => {
          console.log(response), this.fetchUserData();
        },
        error: (error) => {
          console.log(error);
          Swal.fire("There is some error item could not be removed", "We are very sorry for the inconvinience", "error");
        },
      });
  }

  totalCalculator(arr: any) {
    let total = 0;
    for (let i = 0; i < arr.length; i++) {
      total += arr[i].price;
    }
    this.totalPrice = total;
  }

  simpleAlert() {
    this.isCheckOut = false
    this.allBooks = []
    this.totalPrice = 0
    Swal.fire({
      title: `Your order has been placed successfully`,
      text: 'Thankyou',
      timer: 1000,
      icon: 'success',
    });
  }

  isCheckOut = false

  checkOutDone() {
    this.isCheckOut ? this.isCheckOut = false : this.isCheckOut = true;
  }

}
