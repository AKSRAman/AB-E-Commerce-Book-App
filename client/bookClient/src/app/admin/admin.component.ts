import { Component, OnInit } from '@angular/core';
import { Book } from '../book.model';
import { BookServices } from '../books/book.services';
//import {CookieService} from 'angular2-cookie/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private bookService: BookServices) { }

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


  ngOnInit(): void {
    this.getBooksData
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
    // let myApp = angular.module('myApp', ['ngCookies'])
    console.log(book, "delete")
  }

//   let options = new RequestOptions({ headers: headers, withCredentials: true });
// this.http.post(this.url, body , options);

// this.httpclient.get(myUrl, {withCredentials:true})
  // angular.module('myApp', ['ngCookies']);
  //   app.controller('MyController',['$scope','$cookies','$cookieStore', 
  //                      function($scope,$cookies,$cookieStore) {
  //     var someSessionObj = { 'innerObj' : 'somesessioncookievalue'};

  //   $cookies.dotobject = someSessionObj;
  //   $scope.usingCookies = { 'cookies.dotobject' : $cookies.dotobject, "cookieStore.get" : $cookieStore.get('dotobject') };

  //   $cookieStore.put('obj', someSessionObj);
  //   $scope.usingCookieStore = { "cookieStore.get" : $cookieStore.get('obj'), 'cookies.dotobject' : $cookies.obj, };
  //   }
}
