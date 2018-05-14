import { Component } from '@angular/core';

@Component({
  selector: 'my-dashboard',
  templateUrl: './my-dashboard.component.html',
  styleUrls: ['./my-dashboard.component.css']
})
export class MyDashboardComponent {
  cards = [
    { title: '卡片 1', cols: 2, rows: 1 },
    { title: '卡片 2', cols: 1, rows: 1 },
    { title: '卡片 3', cols: 1, rows: 2 },
    { title: '卡片 4', cols: 1, rows: 1 }
  ];
}
