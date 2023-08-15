import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-page4',
  templateUrl: './page4.component.html',
  styleUrls: ['./page4.component.less']
})
export class Page4Component implements OnInit {
  validateForm: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.validateForm = this.fb.group({
      text: [null, [Validators.required]],
      language: ['zh', [Validators.required]]
    });
  }

  submitForm() {
    if (!this.validateForm.valid) {
      return;
    }
    if (document.getElementById('media')) {
      document
        .getElementById('validateForm')
        .removeChild(document.getElementById('media'));
    }
    const text = this.validateForm.get('text').value;
    const language = this.validateForm.get('language').value;
    const media = document.createElement('audio');
    media.controls = true;
    media.autoplay = true;
    media.src = `https://tts.baidu.com/text2audio?cuid=baiduid&lan=${language}&ctp=1&pdt=311&tex=${text}`;
    media.id = 'media';
    document.getElementById('validateForm').appendChild(media);
  }
}
