import { Directive, ElementRef, AfterViewInit } from '@angular/core';

@Directive({
  selector: '[appInputAutoFocus]'
})
export class InputAutoFocusDirective implements AfterViewInit {
  constructor(private el: ElementRef) {}
  /**
   * 在Angular完成组件视图初始化之后立即调用的回调方法。
   * 在实例化视图时仅调用一次。
   */
  ngAfterViewInit() {
    this.el.nativeElement.focus();
  }
}
