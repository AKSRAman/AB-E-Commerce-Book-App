import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Book } from '../book.model';
import { BookServices } from '../books/book.services';
import { HomeServices } from '../home/home.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  searchBook: Book[] = [];
  timerId: any;
  value = "";
  constructor(private homeService: HomeServices, private bookService: BookServices, private router: Router) { }

  isLogin: boolean = false

  ngOnInit(): void {
    this.fetchUserData()
  }

  fetchUserData() {
    let token: any = localStorage.getItem("jwtToken");
    token = JSON.parse(token);
    if (!token) {
      return console.log("token is not present checked by navbar")
    }
    this.homeService.fetchUser().subscribe((res: any) => {
      this.isLogin = true
      console.log(res, 'i am coming via decoded jwt');
    });
  }

  logOut() {
    localStorage.removeItem("jwtToken");
    window.location.reload()
  }
  loginStatus: boolean = this.homeService.loginStatus

  getSearchBook() {

    this.bookService.getSearchBooks(this.value).subscribe((res) => {
      console.log(res)
      this.searchBook = res
    });

  }


  dedounceSearchedBook(event: Event, delay: number) {
    if (this.timerId) {
      clearTimeout(this.timerId);
    }
    this.timerId = setTimeout(() => {
      this.main(event);
    }, delay);
  }

  main(event: Event): boolean | void {
    this.value = (<HTMLInputElement>event.target).value;
    if (this.value.length < 3) {
      this.searchBook = [];
      return false;
    }
    this.getSearchBook();
  }


  gotoSinglebook(id: string | null) {
    this.searchBook = [];
    this.router.navigate(['single', { "id": id }])

  }


}
