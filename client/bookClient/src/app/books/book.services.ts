import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Book } from "../book.model";

@Injectable({providedIn: 'root'})
export class BookServices{
  constructor(private http:HttpClient) {

  } 
  getAllBooks():Observable<Book>{
      return this.http.get<Book>("http://localhost:8080/books");
  }
}
