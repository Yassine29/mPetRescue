import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '../_models';
import { UserService, CasService } from '../_services';

@Component({
			selector: 'topmembres',
			templateUrl: 'topmembres.component.html'
			})
export class TopMembersComponent implements OnInit {
	topUsers: User[];
	
    constructor(private userService: UserService) {
			this.userService.getTopUsers().subscribe(
				data => {
					this.topUsers = data;
				}
			);
    }

    ngOnInit() {
    }
}