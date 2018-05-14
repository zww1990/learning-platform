import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LayoutModule } from '@angular/cdk/layout';

// import { LayoutComponent } from './layout/layout.component';
import { MaterialModule } from './material/material.module';
import { MyNavComponent } from './my-nav/my-nav.component';

const routes: Routes = [
  { path: '', component: MyNavComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    LayoutModule
  ],
  exports: [RouterModule],
  declarations: [MyNavComponent]
})
export class AppRoutingModule { }
