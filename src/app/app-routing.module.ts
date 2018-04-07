import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
// import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatRadioModule
} from '@angular/material';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [
  { path: '', component: LayoutComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    // FormsModule,
    // ReactiveFormsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatRadioModule
  ],
  exports: [RouterModule],
  declarations: [LayoutComponent]
})
export class AppRoutingModule {}
