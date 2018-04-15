import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { LayoutComponent } from './layout/layout.component';
import { MaterialModule } from './material/material.module';

const routes: Routes = [
  { path: '', component: LayoutComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    MaterialModule
  ],
  exports: [RouterModule],
  declarations: [LayoutComponent]
})
export class AppRoutingModule {}
