import { HttpClient } from '@angular/common/http';
import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild
} from '@angular/core';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.less']
})
export class IndexComponent implements OnInit, AfterViewInit {
  @ViewChild('globalArea')
  globalArea: ElementRef;

  constructor(private http: HttpClient) {}

  ngOnInit() {}

  ngAfterViewInit() {
    // const controller = new window['GIO'].Controller(
    //   this.globalArea.nativeElement
    // );
    // this.http
    //   .get('/assets/data/sampleData.json')
    //   .toPromise()
    //   .then(resp => {
    //     controller.addData(resp);
    //     controller.init();
    //   });
  }
}
