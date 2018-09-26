import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AlertService, AuthenticationService, UserService, DataSharingService, MediaService } from '../_services';
import { User } from '../_models';
import { Observable } from 'rxjs';

@Component({
	selector: 'usercard',
	templateUrl: 'usercard.component.html'
	})
export class UserCardComponent implements OnInit {
    loginForm: FormGroup;
	CurrentUser: User;
    isUserLoggedIn: boolean;
	Avatar: any;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
		private userService: UserService,
		private mediaService: MediaService,
		private dataSharingService: DataSharingService) {
			// Subscribe here, this will automatically update 
			// "isUserLoggedIn" whenever a change to the subject is made.
			this.dataSharingService.isUserLoggedIn.subscribe( value => {
				this.isUserLoggedIn = value;
				if(this.isUserLoggedIn){
					this.userService.getCurrentUser().subscribe(
					data => {
						this.CurrentUser = data;
						this.mediaService.getFile(this.CurrentUser.photo).subscribe(
							data => {
								console.log(data);
								this.Avatar = data;
							}
						);
					}
					);
				}
			});
			
		}

    ngOnInit() {}


	signOut() {
		this.dataSharingService.isUserLoggedIn.next(false);
		localStorage.removeItem("token");
	}
}