import { AfterViewInit, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.less']
})
export class IndexComponent implements OnInit, AfterViewInit {
  constructor() {
    console.log('%c constructor', 'color:red');
  }

  ngOnInit() {
    console.log('%c ngOnInit', 'color:red');
  }

  ngAfterViewInit() {
    console.log('%c ngAfterViewInit', 'color:red');
  }
}
