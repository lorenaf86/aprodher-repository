/**
 * Copyright 2014 FPTI-PY [pti] ** ( SGPTI  )
 *                www.pti.org.py | noc@pti.org.py
 *                
 * as you wish... at your service ;-P
 * 
 * ptiDB.com.py
 *            
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aprodher.list;

import com.aprodher.bean.CategoriaFacade;
import com.aprodher.entity.Categoria;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CategoriaListProducer 
{
    @Inject
    CategoriaFacade service;
    
    private List<Categoria> categorias;

    @Produces
    @Named
    public List<Categoria> getCategorias()
    {
        if(categorias == null) {
            return service.findAll();
        }
        return categorias;
    }

    @Produces 
    @Named
    @ComboBoxActiveCategorias
    public List<Categoria> getComboBoxActiveCategorias()
    {
        return service.findAllActives();
    }
    
 

}
