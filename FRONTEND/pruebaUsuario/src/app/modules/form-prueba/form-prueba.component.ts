import { Component, OnInit } from '@angular/core';
import {Validators,FormGroup,FormBuilder} from '@angular/forms';
import { DatePipe } from '@angular/common';
import { PruebaService } from '../../services/prueba.service';
import { ActivatedRoute } from "@angular/router";
import { Prueba } from '../../models/prueba.model';
import {MessageService, ConfirmationService} from 'primeng/api';
import { Router } from '@angular/router';
import {MenuItem} from 'primeng/api';
import { routes } from '../../app.routes';


@Component({
  selector: 'app-form-prueba',
  templateUrl: './form-prueba.component.html',
  styleUrls: ['./form-prueba.component.css']
})
export class FormPruebaComponent implements OnInit {
  localPrueba: Prueba = {};
  userform: FormGroup;
  es: any;
  id: any;
  constructor(private pruebaservices: PruebaService,private fb: FormBuilder,private router: Router,
              private route: ActivatedRoute,private _messageService: MessageService) { 

  }

  ngOnInit() {
    this.localPrueba =JSON.parse(localStorage.getItem('prueba'));
    console.log('f',this.localPrueba);
  
    

    this.userform = this.fb.group({
      //id:['', Validators.required],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      cedula: ['', [Validators.required,Validators.pattern('^[0-9]+')]],
      correo: ['', [Validators.required,Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$')]],
      telefono: ['',[Validators.required,Validators.pattern('^[0-9]+')]],

    })

    if(this.localPrueba !==null){
      this.userform.patchValue({
        id:this.localPrueba.id,
        nombre:this.localPrueba.nombre,
        apellido:this.localPrueba.apellido,
        cedula:this.localPrueba.cedula,
        correo:this.localPrueba.correo,
        telefono:this.localPrueba.telefono,
      })
    }
  }

 /*  get pr_id() {
    return this.userform.get('id').invalid && this.userform.get('id').touched
  } */
  get nombre() {
    return this.userform.get('nombre').invalid && this.userform.get('nombre').touched
  }
  get apellido() {
    return this.userform.get('apellido').invalid && this.userform.get('apellido').touched
  }
  get cedula() {
    return this.userform.get('cedula').hasError('required') && this.userform.get('cedula').touched
  }
  get cedula2() {
    return this.userform.get('cedula').hasError('pattern') && this.userform.get('cedula').touched
  }
  get correo() {
    return this.userform.get('correo').hasError('required') && this.userform.get('correo').touched
  }
  get correo2() {
    return this.userform.get('correo').hasError('pattern') && this.userform.get('correo').touched
  }
  get telefono() {
    return this.userform.get('telefono').hasError('required') && this.userform.get('telefono').touched
  }
  get telefono2() {
    return this.userform.get('telefono').hasError('pattern') && this.userform.get('telefono').touched
  }


 onSubmit(){
   
      if(this.localPrueba !== null){
        console.log("voy a actualizar");
        this.id = this.localPrueba.id;  
        console.log(this.id);
        console.log(this.userform.value);
        
        this.pruebaservices.updatePrueba(this.userform.value,this.id)
        .subscribe((data: any) =>{
          console.log(data);
          if (data.status == "200") {
            this._messageService.add({severity: 'success',summary: 'Exitoso',detail:'estado:'+data.status +' '+ data.mensaje, life: 3000})
            this.userform.reset();
          this.router.navigate(["/main/listarPrueba"]);
          }else{
            this._messageService.add({severity: 'warn',summary: 'Verifique',detail:'estado:'+data.status +' '+ data.mensaje, life: 6000})
            //this.userform.reset();
            //this.router.navigate(["/main/listarPrueba"]);
          }
        })
      }else{
        console.log("voy a crear");
        this.pruebaservices.createPrueba(this.userform.value)
        .subscribe((data=>{
          console.log(data.mensaje);

          if (data.status == "200") {
            this._messageService.add({severity: 'success',summary: 'Exitoso',detail:'estado:'+data.status +' '+ data.mensaje, life: 3000})
            this.userform.reset();
          this.router.navigate(["/main/listarPrueba"]);
          }else{
            this._messageService.add({severity: 'warn',summary: 'Verifique',detail:'estado:'+data.status +' '+ data.mensaje, life: 6000})
            //this.userform.reset();
            //this.router.navigate(["/main/listarPrueba"]);
          }


          
        }))
      }
      
    
  }

}
