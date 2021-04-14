import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8080/assessments';

@Injectable({
  providedIn: 'root'
})
export class AssessmentService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(baseUrl);
  }

  // @ts-ignore
  get(id): Observable<any> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  // @ts-ignore
  create(data): Observable<any> {
    return this.http.post(baseUrl, data);
  }
  // @ts-ignore
  update(id, data): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  // @ts-ignore
  delete(id): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  // @ts-ignore
  findByTitle(title): Observable<any> {
    return this.http.get(`${baseUrl}?title=${title}`);
  }
}
