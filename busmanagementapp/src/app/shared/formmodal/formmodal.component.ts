import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { faX } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-formmodal',
  templateUrl: './formmodal.component.html',
  styleUrls: ['./formmodal.component.scss'],
})
export class FormmodalComponent implements OnInit {
  @Input() modalTitle!: string;
  @Output() handleCloseModal = new EventEmitter<boolean>();
  @Input() labels!: string[];

  faX = faX;

  constructor() {}

  ngOnInit(): void {}

  closeModal() {
    this.handleCloseModal.emit(true);
  }
}
