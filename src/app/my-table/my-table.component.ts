import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import { MyTableDataSource } from './my-table-datasource';

@Component({
  selector: 'app-table',
  templateUrl: './my-table.component.html',
  styleUrls: ['./my-table.component.css']
})
export class MyTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  dataSource: MyTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name'];

  ngOnInit() {
    this.paginator._intl.firstPageLabel = '第一页';
    this.paginator._intl.lastPageLabel = '最后一页';
    this.paginator._intl.nextPageLabel = '下一页';
    this.paginator._intl.previousPageLabel = '上一页';
    this.paginator._intl.itemsPerPageLabel = '每页数量';
    this.paginator._intl.getRangeLabel = (page: number, pageSize: number, length: number) => `共 ${length} 条`;
    this.dataSource = new MyTableDataSource(this.paginator, this.sort);
  }
}
