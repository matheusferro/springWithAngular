import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ListBookComponent } from './list-book/list-book.component';
import { BookFormComponent } from './book-form/book-form.component';

const routes: Routes = [
  { path: 'books', component: ListBookComponent },
  { path: 'addbook', component: BookFormComponent }

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

