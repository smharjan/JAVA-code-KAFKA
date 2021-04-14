import { Component, OnInit } from '@angular/core';
import {AssessmentService} from '../../services/assessment.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-assessment-view',
  templateUrl: './assessment-view.component.html',
  styleUrls: ['./assessment-view.component.css']
})
export class AssessmentViewComponent implements OnInit {
  currentAssessment = null;
  constructor(
    private assessmentService: AssessmentService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.getAssessment(this.route.snapshot.paramMap.get('id'));
  }

  // @ts-ignore
  getAssessment(id): void {
    this.assessmentService.get(id)
      .subscribe(
        data => {
          this.currentAssessment = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }


}
