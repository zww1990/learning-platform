export class ResponseBody<T> {
  status: number;
  code: number;
  message?: string;
  data?: T;
}
