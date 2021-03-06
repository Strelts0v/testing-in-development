import { Injectable } from '@angular/core';
import { Http, Response }       from '@angular/http';

import { Observable }     from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { Hero }           from './hero';

@Injectable()
export class HeroSearchService {

  private heroesUrl = 'http://localhost:8080/api';  // URL to web api
    
  constructor(private http: Http) {}

  search(term: string): Observable<Hero[]> {
      const url = this.heroesUrl + '/heroes/search/findByName?name=' + term;
    return this.http
               .get(url)
          .map((r: Response) => r.json() as Hero[]);
  }
}
