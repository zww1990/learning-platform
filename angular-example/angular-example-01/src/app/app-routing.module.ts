import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {LayoutComponent} from './pages/layout/layout.component';
import {SharedModule} from './shared/shared.module';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {path: '', pathMatch: 'full', redirectTo: '/welcome'},
      {
        path: 'welcome',
        loadChildren: () => import('./pages/welcome/welcome.module').then(m => m.WelcomeModule)
      }
    ]
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true}),
    SharedModule
  ],
  exports: [RouterModule],
  declarations: [LayoutComponent]
})
export class AppRoutingModule {
}
