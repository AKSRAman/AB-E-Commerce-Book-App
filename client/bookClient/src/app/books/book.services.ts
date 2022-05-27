import {
  HttpClient, HttpHeaders
} from "@angular/common/http";
// import { Http, Headers, Response, RequestOptions } from '@angular/http';
import {
  Injectable
} from "@angular/core";
import { Observable } from "rxjs";
import { Book } from "../book.model";
import { AllBookResponse, Comman, SingleBookResponse } from "../response.model";
@Injectable({
  providedIn: 'root'
})

export class BookServices {
  baseurl = "http://localhost:8080/books"
  constructor(private http: HttpClient) {
  }
  getAllBooks() {
    return this.http.get<AllBookResponse>(this.baseurl);
  }

  deleteBook(id: string | null) {
    let token: any = localStorage.getItem("jwtToken");
    token = JSON.parse(token);
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': "I am coming from frontend",
        'Authorization': `Bearer ${token}`
      })
    };
    return this.http.delete<Comman>(this.baseurl + "/" + id,httpOptions);
  }

  addNewBook(newBook: Book) {
    return this.http.post<SingleBookResponse>(this.baseurl, newBook).subscribe(res => { console.log("Book Added Successfully") });;
  }
  updateBook(book: Book) {
    return this.http.put<SingleBookResponse>(this.baseurl + book.id, book);
  }
  getSingleBook(id:any) {
    console.log(id);
  return this.http.get<SingleBookResponse>(this.baseurl+"/"+id);
}
  getUser(): Observable<any> {
    let token: any = localStorage.getItem("jwtToken");
    token = JSON.parse(token);
    const httpOptions = {
      headers: new HttpHeaders({
        'x-api-key': "I am coming from frontend",
        'Authorization': `Bearer ${token}`
      })
    };
    return this.http.get("http://localhost:8080/user/getUser", httpOptions);
  }
  getCookies() {
    return this.http.get("http://localhost:8080/user/getCookies");
  }

}


// const headers = new HttpHeaders().set('Authorization', 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFua3VtYXJAZ21haWwuY29tIiwibmFtZSI6IkFtYW4gS3VtYXIiLCJleHAiOjE2NTM1NzA4ODYsInVzZXJJZCI6IjYyOGQyNDE3MDMxNGIyNzZhMTI5YzFmMyIsImlhdCI6MTY1MzU1Mjg4Nn0.NZ3BEKp6eLK5zsKn3DV2g14FgslJrw_orSvR5EEb0HsTklH9GM_5FFVTJjIP8LBQ6C7fYGGllKmtzutHqHrJ7g');
// return this.http.get("http://localhost:8080/user/getUser", { headers });