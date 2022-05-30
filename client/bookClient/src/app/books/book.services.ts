import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
import { Book } from '../book.model';
import { AllBookResponse, BookByPage, Comman, SingleBookResponse } from '../response.model';
@Injectable({
  providedIn: 'root',
})
export class BookServices {

  baseurl = "http://localhost:8080/books"

  constructor(private http: HttpClient) {
  }
  getBookDataPageWise(page: number) {
    return this.http.get<BookByPage>(this.baseurl + "/" + "page" + "?pageno=" + page);
  }
  getSearchBooks(val: string) {
    return this.http.get<Book[]>(this.baseurl + "/search?val=" + val);

  }

  getAllBooks() {
    return this.http.get<AllBookResponse>(this.baseurl);
  }

  deleteBook(id: string | null) {
    let token: any = localStorage.getItem('jwtToken');
    token = JSON.parse(token);
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': 'I am coming from frontend',
        Authorization: `Bearer ${token}`,
      }),
    };
    return this.http.delete<Comman>(this.baseurl + "/" + id, httpOptions);
  }

  addNewBook(newBook: Book) {
    let token: any = localStorage.getItem('jwtToken');
    token = JSON.parse(token);
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': 'I am coming from frontend',
        Authorization: `Bearer ${token}`,
      }),
    };
    return this.http
      .post<SingleBookResponse>(this.baseurl, newBook, httpOptions)
      .subscribe((res) => {
        console.log(res); this.simpleAlert()
      });
  }

  simpleAlert() {
    Swal.fire({
      title: `Book saved successfully `,
      text: 'Thankyou',
      timer: 1000,
      icon: 'success',
    });
  }


  updateBook(book: Book) {
    let token: any = localStorage.getItem('jwtToken');
    token = JSON.parse(token);
    console.log(token);
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': 'I am coming from frontend',
        Authorization: `Bearer ${token}`,
      }),
    };
    return this.http.put<SingleBookResponse>(
      this.baseurl + '/' + book.id,
      book,
      httpOptions
    );
  }
  getSingleBook(id: any) {
    console.log(id);
    return this.http.get<SingleBookResponse>(this.baseurl + '/' + id);
  }
  getUser(): Observable<any> {
    let token: any = localStorage.getItem('jwtToken');
    token = JSON.parse(token);
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': 'I am coming from frontend',
        Authorization: `Bearer ${token}`,
      }),
    };
    return this.http.get('http://localhost:8080/user/getUser', httpOptions);
  }
  getCookies() {
    return this.http.get('http://localhost:8080/user/getCookies');
  }

}
