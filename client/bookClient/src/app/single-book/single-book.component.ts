import { BookServices } from './../books/book.services';
import { Component, OnInit } from '@angular/core';
import { Book } from '../book.model';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-single-book',
  templateUrl: './single-book.component.html',
  styleUrls: ['./single-book.component.css'],
})
export class SingleBookComponent implements OnInit {
  book: Book = {
    addedOn: "",
    author: "",
    category: "",
    description: "",
    id: "",
    imageUrl: "",
    pages: 1111,
    price: 1111,
    publishDate: "",
    rating: 0,
    title: '',
    updatedOn: '',
  };


  id: string | null = "";
  constructor(private activatedRoute: ActivatedRoute, private router: Router, private bookData: BookServices, private http: HttpClient) { }

  reviews = [ {
    id: null,
    bookId: null,
    userId: null,
    reviewedBy: null,
    review: null,
    rating: 1,
    reviewedAt: null,
    updatedOn: null
}]


  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.id = params.get('id');
      this.getbook(this.id);
      this.fetchAllReviews();
    });
  }

  getbook(id: string | null) {
    this.bookData.getSingleBook(id).subscribe(res => {
      console.log(res)
      this.book = res.book
    })
  }

  addCart() {
    if (this.book) {
      this.addInCart(this.book)
    }
    else return
  }

  addInCart(book: Book) {
    let token: any = localStorage.getItem('jwtToken');
    token = JSON.parse(token);
    if (!token) {
      Swal.fire("Please login to add this item in your cart", "", "error");
      return console.log("token is not present checked by books for add to cart")
    }
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': "I am coming from frontend",
        'Authorization': `Bearer ${token}`
      })
    };
    this.http.post('http://localhost:8080/user/addToCart', book, httpOptions)
      .subscribe({
        next: (response) => { console.log(response); this.simpleAlert(book.title) },
        error: (error) => console.log(error),
      });
  }

  simpleAlert(message: any) {
    Swal.fire({
      title: `${message} Added to Your Cart`,
      text: 'Thankyou',
      timer: 1000,
      position: 'top-end',
      icon: 'success',
    });
  }

  myAlert() {
    Swal.fire({
      title: `Your review submitted successfully`,
      text: 'We appriciate your effort',
      timer: 1000,
      icon: 'success',
    });
  }

  postReview(review: NgForm) {
    let token: any = localStorage.getItem('jwtToken');
    token = JSON.parse(token);
    if (!token) {
      Swal.fire("Please login to add this item in your cart", "", "error");
      return console.log("token is not present checked by books for add to cart")
    }
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': 'I am coming from frontend',
        Authorization: `Bearer ${token}`,
      }),
    };
    this.http
      .post(`http://localhost:8080/review/saveReview/${this.id}`, review.value, httpOptions)
      .subscribe({
        next: (response) => { console.log(response); this.myAlert() , this.fetchAllReviews(),review.reset()},
        error: (error) => console.log(error),
      });
  }

  fetchAllReviews() {
    return this.http.get(`http://localhost:8080/review/getReviews/${this.id}`).subscribe((res: any) => {
      this.assignValue(res);
    });;
  };

  assignValue(res: any) {
    console.log(res)
    this.reviews = res.reviewList
    this.reviews.reverse()
    console.log(this.reviews,"hh")
  }

}