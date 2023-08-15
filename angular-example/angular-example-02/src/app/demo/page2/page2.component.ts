import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
    { label: '梨子', value: 'Pear', checked: false },
    { label: '橙子', value: 'Orange', checked: false }
  ];
  auto_item = ['79878798', '46554446', '13213131'];
  now = new Date();

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.validateForm = this.fb.group({
      select: [null, [Validators.required]],
      select_multiple: [null, [Validators.required]],
      datepicker: [null, [Validators.required]],
      timepicker: [null, [Validators.required]],
      input_number: [null, [Validators.required]],
      switch: [null, [Validators.required]],
      slider: [null, [Validators.required]],
      radio_group: [null, [Validators.required]],
      radio_button: [null, [Validators.required]],
      check_group: [null, [Validators.required]],
      auto_text: [null, [Validators.required]]
    });
  }

  submitForm(event: Event) {
    event.preventDefault();
    for (const key in this.validateForm.controls) {
      this.validateForm.controls[key].markAsDirty();
      this.validateForm.controls[key].updateValueAndValidity();
    }
    console.log(this.validateForm.value);
  }

  resetForm(event: Event) {
    event.preventDefault();
    this.validateForm.reset();
    for (const key in this.validateForm.controls) {
      this.validateForm.controls[key].markAsPristine();
      this.validateForm.controls[key].updateValueAndValidity();
    }
  }
}
