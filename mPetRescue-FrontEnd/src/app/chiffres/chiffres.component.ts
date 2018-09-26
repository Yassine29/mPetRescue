import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '../_models';
import { UserService, CasService } from '../_services';

@Component({
			selector: 'chiffres',
			templateUrl: 'chiffres.component.html'
			})
export class ChiffresComponent implements OnInit {
	usersCount: number;
	casCount: number;
	casTraitesCount: number;
	
    constructor(private userService: UserService,
				private casService: CasService) {
			this.userService.countUsers().subscribe(
				data => {
					this.usersCount = data;
				}
			);
			this.casService.countCas().subscribe(
				data => {
					this.casCount = data;
				}
			);
			this.casService.countCasTraites().subscribe(
				data => {
					this.casTraitesCount = data;
				}
			);
    }

    ngOnInit() {
    }
}