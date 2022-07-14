import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'defaultValue'
})
export class DefaultValuePipe implements PipeTransform {

  transform(value: string | any, ...args: any[]): any {
    return value === null || value.length === 0 ? '无' : value;
  }

}
