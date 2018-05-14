import { HttpRequest } from '@angular/common/http';
import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class LoadingService {

  constructor() { }

  onLoadingChanged: EventEmitter<boolean> = new EventEmitter<boolean>();

  private requests: HttpRequest<any>[] = [];

  onStarted(req: HttpRequest<any>): void {
    this.notify(true);
  }

  onFinished(req: HttpRequest<any>): void {
    this.notify(false);
  }

  private notify(status: boolean): void {
    this.onLoadingChanged.emit(status);
  }
}
