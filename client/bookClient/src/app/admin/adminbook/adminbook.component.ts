import {
  Component,
  OnInit
} from '@angular/core';
import {
  BookServices
} from 'src/app/books/book.services';
import { Book } from '../../book.model';
@Component({
  selector: 'app-adminbook',
  templateUrl: './adminbook.component.html',
  styleUrls: ['./adminbook.component.css'],
})
export class AdminbookComponent implements OnInit {
  allBooks: Book[] = [{
    addedOn: "",
    authors: "",
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
  editMode=false;
  constructor(private bookServices: BookServices) {}
tempBook:Book={
  addedOn: "",
    authors: "",
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
}
  ngOnInit(): void {
    this.getBooksData();
  }

  getBooksData() {
    this.bookServices.getAllBooks().subscribe((res) => {
      console.log(res.bookList);
      this.allBooks=res.bookList;

    });
    }

    setEditMode(mode:boolean,i:number){
      this.editMode=mode;
      if (mode == true) {
        this.tempBook = Object.assign({}, this.allBooks[i]);
      }

}

updateBook(){
  this.bookServices.updateBook(this.tempBook)
}
deteteBook(id:string|null){
  this.bookServices.deleteBook(id).subscribe(res=>{this.getBooksData()});
}
}


