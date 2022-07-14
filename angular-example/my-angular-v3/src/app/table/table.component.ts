import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { TableDataSource, TableItem } from './table-datasource';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<TableItem>;
  dataSource: TableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name'];

  ngOnInit() {
    this.dataSource = new TableDataSource();
  }
  
  ngAfterViewInit() {
    this.paginator._intl.firstPageLabel = '第一页';
    this.paginator._intl.lastPageLabel = '最后一页';
    this.paginator._intl.previousPageLabel = '上一页';
    this.paginator._intl.nextPageLabel = '下一页';
    this.paginator._intl.itemsPerPageLabel = '每页数量';
    this.paginator._intl.getRangeLabel = (page: number, pageSize: number, length: number) => {
      let to = pageSize * (page + 1);
      let from = to - pageSize + 1;
      to = to > length ? length : to;
      return `${from} - ${to} 共 ${length} 条`;
    };
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }
}
