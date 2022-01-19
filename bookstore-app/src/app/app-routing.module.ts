import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ListBookComponent } from './list-book/list-book.component';
import { BookFormComponent } from './book-form/book-form.component';
import { DetailedBookComponent } from './detailed-book/detailed-book.component';

const routes: Routes = [
  { path: 'books', component: ListBookComponent },
  { path: 'addbook', component: BookFormComponent },
  { path: 'findBook/:bookId', component: DetailedBookComponent }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

