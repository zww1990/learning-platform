import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-page2',
  templateUrl: './page2.component.html',
  styleUrls: ['./page2.component.less']
})
export class Page2Component implements OnInit {
  validateForm: FormGroup;

  marks = { 0: 'A', 25: 'B', 50: 'C', 75: 'D', 100: 'E' };
  select_single = [
    { label: '中国', value: 'China' },
    { label: '美国', value: 'U.S.A' }
  ];
  select_multiple = [
    { label: '红色', value: 'Red' },
    { label: '绿色', value: 'Green' },
    { label: '蓝色', value: 'Blue' }
  ];
  radio_group = [
    { label: '选项1', value: 1 },
    { label: '选项2', value: 2 },
    { label: '选项3', value: 3 }
  ];
  radio_button = [
    { label: '选项1', value: 1 },
    { label: '选项2', value: 2 },
    { label: '选项3', value: 3 }
  ];
  check_group = [
    { label: '苹果', value: 'Apple', checked: false },
    { label: '梨子', value: 'Pear', checked: true },
    { label: '橙子', value: 'Orange', checked: false }
  ];
  now = new Date();

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.validateForm = this.fb.group({
      select: ['China'],
      select_multiple: [['Red']],
      datepicker: [this.now],
      timepicker: [this.now],
      input_number: [4],
      switch: [true],
      slider: [0],
      radio_group: [1],
      radio_button: [1],
      check_group: []
    });
  }

  submitForm() {
    console.log(this.validateForm.value);
  }
}
