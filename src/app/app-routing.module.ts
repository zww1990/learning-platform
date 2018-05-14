import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LayoutModule } from '@angular/cdk/layout';

// import { LayoutComponent } from './layout/layout.component';
import { MaterialModule } from './material/material.module';
import { MyNavComponent } from './my-nav/my-nav.component';
import { MyDashboardComponent } from './my-dashboard/my-dashboard.component';
import { MyTableComponent } from './my-table/my-table.component';

const routes: Routes = [
  { path: '', component: MyNavComponent },
  { path: 'dashboard', component: MyDashboardComponent },
  { path: 'table', component: MyTableComponent },
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
  declarations: [MyNavComponent, MyDashboardComponent, MyTableComponent]
})
export class AppRoutingModule { }
