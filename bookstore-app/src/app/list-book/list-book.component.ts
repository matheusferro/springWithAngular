import { Component, OnInit } from '@angular/core';
import { Book } from '../book/book';
import { BookService } from '../book/book.service'

@Component({
  selector: 'list-book',
  templateUrl: './list-book.component.html',
  styleUrls: ['./list-book.component.css']
})
export class ListBookComponent implements OnInit {

  books: Book[] | undefined;

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.bookService.findAll().subscribe((data: Book[]) => {
      this.books = data;
    })
  }

}
