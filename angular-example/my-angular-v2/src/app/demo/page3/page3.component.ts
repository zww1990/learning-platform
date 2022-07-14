import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-page3',
  templateUrl: './page3.component.html',
  styleUrls: ['./page3.component.less']
})
export class Page3Component implements OnInit {
  i = 1;
  editCache = {};
  allChecked = false;
  indeterminate = false;
  disabledButton = true;
  dataSet = [
    {
      key: 0,
      name: '我是格鲁特 0',
      age: 32,
      address: '国土战略防御攻击与后勤保障局 0',
      checked: false
    },
    {
      key: 1,
      name: '我是格鲁特 1',
      age: 32,
      address: '国土战略防御攻击与后勤保障局 1',
      checked: false
    }
  ];

  constructor() {}

  ngOnInit() {
    this.updateEditCache();
  }

  updateEditCache(): void {
    this.dataSet.forEach(item => {
      if (!this.editCache[item.key]) {
        this.editCache[item.key] = {
          edit: false,
          name: item.name
        };
      }
    });
  }

  finishEdit(key: number): void {
    this.editCache[key].edit = false;
    this.dataSet.find(item => item.key === key).name = this.editCache[key].name;
  }

  startEdit(key: number): void {
    this.editCache[key].edit = true;
  }

  deleteRow(i: number): void {
    this.dataSet = this.dataSet.filter(d => d.key !== i);
  }

  delAllRow(): void {
    this.dataSet = this.dataSet.filter(x => !x.checked);
    this.allChecked = false;
    this.indeterminate = false;
    this.disabledButton = !this.dataSet.some(value => value.checked);
  }

  addRow(): void {
    this.i++;
    this.dataSet = [
      ...this.dataSet,
      {
        key: this.i,
        name: `我是格鲁特 ${this.i}`,
        age: 32 + this.i,
        address: `国土战略防御攻击与后勤保障局 ${this.i}`,
        checked: false
      }
    ];
    this.updateEditCache();
  }

  checkAll(value: boolean): void {
    this.dataSet.forEach(data => (data.checked = value));
    this.refreshStatus();
  }

  refreshStatus(): void {
    this.allChecked = this.dataSet.every(value => value.checked);
    const allUnChecked = this.dataSet.every(value => !value.checked);
    this.disabledButton = !this.dataSet.some(value => value.checked);
    this.indeterminate = !this.allChecked && !allUnChecked;
  }
}
