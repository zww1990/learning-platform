import { Directive, HostListener } from '@angular/core';
import * as screenfull from 'screenfull';

@Directive({
  selector: '[appToggleFullScreen]'
})
export class ToggleFullScreenDirective {
  constructor() {}

  @HostListener('click')
  onclick() {
    if (screenfull.enabled) {
      screenfull.toggle();
    }
  }
}
