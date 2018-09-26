import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AlertService, AuthenticationService, UserService, DataSharingService } from '../_services';
import { User } from '../_models';

@Component({
	selector: 'login',
	templateUrl: 'login.component.html'
	})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    submitted = false;
    isUserLoggedIn: boolean;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService,
		private userService: UserService, 
		private dataSharingService: DataSharingService) {
			// Subscribe here, this will automatically update 
			// "isUserLoggedIn" whenever a change to the subject is made.
			this.dataSharingService.isUserLoggedIn.subscribe( value => {
				this.isUserLoggedIn = value;
			});
			
		}

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        // reset login status
        //this.authenticationService.logout();

    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        this.authenticationService.login(this.loginForm.value)
            .pipe(first())
            .subscribe(
                data => {
                     localStorage.setItem('token', data);
					// After the user has logged in, emit the behavior subject changes.
					this.dataSharingService.isUserLoggedIn.next(true);
                },
                error => {
                    this.alertService.error(error);
                });
    }
}