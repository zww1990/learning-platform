import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.less']
})
export class IndexComponent implements OnInit {
  constructor(private element: ElementRef, private http: HttpClient) {}

  ngOnInit() {
    const container = this.element.nativeElement.querySelector('div#globalArea');
    const controller = new window['GIO'].Controller(container);
    this.http
      .get('/assets/data/sampleData.json')
      .toPromise()
      .then(resp => {
        controller.addData(resp);
        controller.init();
      });
  }
}
