import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Book } from '../book.model';
import { BookServices } from './book.services';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css'],
})
export class BooksComponent implements OnInit {
  currentPage: number = 0;
  lastPage: number = 0;
  imgSrc: string = "	https://rukminim2.flixcart.com/flap/3600/3600/image/b3fe381767050079.jpg?q=80"
  images: string[] = ["https://rukminim2.flixcart.com/flap/3600/3600/image/b3fe381767050079.jpg?q=80",
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
    imageUrl: "https://rukminim2.flixcart.com/image/612/612/klmmrgw0/regionalbooks/f/3/g/the-power-of-your-subconscious-mind-original-imagypuj7qxyepyq.jpeg?q=70",
    pages: 1111,
    price: 1111,
    publishDate: "",
    rating: 0,
    title: "PSYCOLOGY OF MIND",
    updatedOn: "",
  },];

  constructor(private bookService: BookServices, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    //this.getBooksData();
    this.getBookDataPageWise();
    this.startSlideshow();
    // this.getUserData();
  }
  getBookDataPageWise() {
    this.bookService.getBookDataPageWise(this.currentPage).subscribe((res) => {
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
  startSlideshow() {
    this.imgSrc = this.images[5];
    let counter = 0;
    setInterval(() => {
      if (counter == this.images.length) {
        counter = 0;
      }
      this.imgSrc = this.images[counter];
      counter++;
    }, 2000);
  }

  gotoSinglebook(id: string | null) {
    this.router.navigate(['single', { "id": id }])
  }

  // getUserData() {
  //   let token: any = localStorage.getItem("jwtToken");
  //   token = JSON.parse(token);
  //   if (!token) {
  //     return
  //   }
  //   this.bookService.getUser().subscribe((res) => {
  //     console.log(res, "Yess i am coming from protected route");
  //   });
  // }

  onEdit() {
    window.scrollTo(0, 0);
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
        'x-api-key': 'I am coming from frontend',
        Authorization: `Bearer ${token}`,
      }),
    };
    this.http
      .post('http://localhost:8080/user/addToCart', book, httpOptions)
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
}
