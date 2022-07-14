import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddressFormComponent } from './address-form/address-form.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DragDropComponent } from './drag-drop/drag-drop.component';
import { NavigationComponent } from './navigation/navigation.component';
import { TableComponent } from './table/table.component';
import { TreeComponent } from './tree/tree.component';
import { MaterialModule } from './material/material.module';

const routes: Routes = [
  {
    path: '',
    component: NavigationComponent,
    children: [
      { path: 'address', component: AddressFormComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'drag', component: DragDropComponent },
      { path: 'table', component: TableComponent },
      { path: 'tree', component: TreeComponent }
    ]
  },
];

@NgModule({
  declarations: [
    AddressFormComponent,
    NavigationComponent,
    TableComponent,
    DashboardComponent,
    TreeComponent,
    DragDropComponent
  ],
  imports: [RouterModule.forRoot(routes), MaterialModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
