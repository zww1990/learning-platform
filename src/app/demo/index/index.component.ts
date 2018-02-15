import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';

import { CanComponentDeactivate } from '../../auth/auth-guard.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.less']
})
export class IndexComponent implements OnInit/* , CanComponentDeactivate */ {
  constructor() {}

  ngOnInit() {}

  // canDeactivate() {
  //   const confirmation = window.confirm('你确定要离开首页?');
  //   return Observable.of(confirmation);
  // }
}
