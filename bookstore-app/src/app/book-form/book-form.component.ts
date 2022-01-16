import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book, SaveBook } from '../book/book';
import { BookService } from '../book/book.service';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {

  book: SaveBook;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookService: BookService
  ) {
    this.book = new SaveBook();
  }

  ngOnInit(): void{
    console.log("GO SUBMIT YOUR BOOK!!!!!")
  }

  onSubmit(): void {
    this.bookService.save(this.book)
    .subscribe((result: SaveBook) => {
      this.gotoBookList()
    })
  }

  gotoBookList(){
    this.router.navigate(['/books']);
  }
}
