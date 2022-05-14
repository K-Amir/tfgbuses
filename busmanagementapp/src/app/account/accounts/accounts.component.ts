import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.scss'],
})
export class AccountsComponent implements OnInit {
  showAddModal: boolean = false;
  faPlus = faPlus;

  labels: string[] = ['Name', 'Surname', 'Email', 'Password'];
  admins!: any;

  formGroup: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    surname: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(private accountService: AccountService) {}

  ngOnInit(): void {
    this.loadAllAdministrators();
  }

  handleCloseModal() {
    this.showAddModal = false;
  }

  loadAllAdministrators() {
    this.accountService.getAllAdmins().subscribe({
      next: (v) => {
        this.admins = v;
      },
    });
  }

  handleFormSubmit() {
    this.formGroup.markAllAsTouched();
    if (this.formGroup.invalid) return;

    this.accountService
      .addNewAdmin({
        ...this.formGroup.value,
        name: this.capitalizeFirstLetter(this.formGroup.get('name')?.value),
        surname: this.capitalizeFirstLetter(
          this.formGroup.get('surname')?.value
        ),
      })
      .subscribe({
        complete: () => {
          this.loadAllAdministrators();
          this.showAddModal = false;
        },
      });
  }

  showErrorBorder(control: string) {
    return this.formGroup.get(control)?.errors &&
      this.formGroup.get(control)?.touched
      ? 'error'
      : '';
  }

  handleDelete(email: string) {
    console.log(email);
    this.accountService.deleteByEmail(email).subscribe({
      complete: () => {
        this.loadAllAdministrators();
      },
    });
  }

  private capitalizeFirstLetter(text: string) {
    text = text.toLocaleLowerCase();
    return text.charAt(0).toUpperCase() + text.slice(1);
  }
}
