import { Component, OnInit } from '@angular/core';
import { AssessmentService } from 'src/app/services/assessment.service';


@Component({
  selector: 'app-assessment-list',
  templateUrl: './assessment-list.component.html',
  styleUrls: ['./assessment-list.component.css']
})
export class AssessmentListComponent implements OnInit {

  assessments: any;
  currentAssessment = null;
  currentIndex = -1;
  title = '';

  constructor(private assessmentService: AssessmentService) { }

  ngOnInit(): void {
    this.retrieveAssessments();
  }

  retrieveAssessments(): void {
    this.assessmentService.getAll()
      .subscribe(
        data => {
          this.assessments = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  // refreshList(): void {
  //   this.retrieveAssessments();
  //   this.currentAssessment = null;
  //   this.currentIndex = -1;
  // }

  // @ts-ignore
  setActiveAssessment(assessment, index): void {
    this.currentAssessment = assessment;
    this.currentIndex = index;
  }

  removeAllAssessments(): void {
    this.assessmentService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.retrieveAssessments();
        },
        error => {
          console.log(error);
        });
  }

  searchTitle(): void {
    this.assessmentService.findByTitle(this.title)
      .subscribe(
        data => {
          this.assessments = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

}
