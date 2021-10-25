import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Prueba } from '../models/prueba.model';
@Injectable({
  providedIn: 'root'
})
export class PruebaService {

  Url1p  ='http://localhost:8090/Usuario/listar';
  Url2p = 'http://localhost:8090/Usuario/agregar';
  Url3p = 'http://localhost:8090/Usuario/editar';
  Url4p = 'http://localhost:8090/Usuario/eliminar';


  constructor(private http: HttpClient) {
  }

  getPrueba(){
    return this.http.get(this.Url1p);
  }

  deletePrueba(prueba: Prueba){
    return this.http.delete<Prueba>(this.Url4p+"/"+prueba.id);
  }

  createPrueba(prueba: Prueba){
    return this.http.post<Prueba>(this.Url2p,prueba);
  }

  updatePrueba(prueba: Prueba,id: any){
    console.log("id update",prueba.id);
    
    return this.http.put<Prueba>(this.Url3p + "/" +id,prueba);
  }
}
