import { Component, OnInit } from '@angular/core';
import {AssessmentService} from '../../services/assessment.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-assessment-details',
  templateUrl: './assessment-details.component.html',
  styleUrls: ['./assessment-details.component.css']
})
export class AssessmentDetailsComponent implements OnInit {

  currentAssessment = null;
  message = '';

  constructor(
    private assessmentService: AssessmentService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.message = '';
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

  // @ts-ignore
  updatePublished(status): void {
    // @ts-ignore
    const data = {
      // @ts-ignore
      title: this.currentAssessment.title,
      // @ts-ignore
      description: this.currentAssessment.description,
      // @ts-ignore
      experience: this.currentAssessment.experience
    };

    // @ts-ignore
    this.assessmentService.update(this.currentAssessment.id, data)
      .subscribe(
        response => {
          // @ts-ignore
          this.currentAssessment.published = status;
          console.log(response);
        },
        error => {
          console.log(error);
        });
  }

  updateAssessment(): void {
    // @ts-ignore
    this.assessmentService.update(this.currentAssessment.id, this.currentAssessment)
      .subscribe(
        response => {
          console.log(response);
          this.message = 'The assessment was updated successfully!';
        },
        error => {
          console.log(error);
        });
  }

  deleteAssessment(): void {
    // @ts-ignore
    this.assessmentService.delete(this.currentAssessment.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/assessments']);
        },
        error => {
          console.log(error);
        });
  }


}
