import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first, map } from 'rxjs/operators';

import { AlertService, UserService, DataSharingService } from '../_services';

@Component({
	selector: 'register',
	templateUrl: 'register.component.html'
	})
export class RegisterComponent implements OnInit {
    registerForm: FormGroup;
    submitted = false;
	isUserLoggedIn: boolean;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private userService: UserService,
        private alertService: AlertService,
		private dataSharingService: DataSharingService) {
			// Subscribe here, this will automatically update 
			// "isUserLoggedIn" whenever a change to the subject is made.
			this.dataSharingService.isUserLoggedIn.subscribe( value => {
				this.isUserLoggedIn = value;
			});
			
		}

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            firstName: ['', [Validators.required, Validators.minLength(2)]],
            lastName: ['', [Validators.required, Validators.minLength(2)]],
            username: ['', [Validators.required, Validators.minLength(3)]],
			email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(6)]]
        });
    }

    // convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.registerForm.invalid) {
            return;
        }

        this.userService.register(this.registerForm.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.alertService.success('Inscription réussie !', true);
					localStorage.setItem('token', data);
					// After the user has logged in, emit the behavior subject changes.
					this.dataSharingService.isUserLoggedIn.next(true);
                },
                error => {
                    this.alertService.error(error);
                });
    }
}